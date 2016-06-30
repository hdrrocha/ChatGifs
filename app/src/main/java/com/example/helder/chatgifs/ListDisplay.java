package com.example.helder.chatgifs;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListDisplay extends ArrayAdapter<OneComment> {

private TextView textViewItemMensage;
private List<OneComment> countries = new ArrayList<OneComment>();
private LinearLayout wrapperMensageItem;
private ImageView imageGif;
private Context context;

public ListDisplay(Context context, int textViewResourceId) {
    super(context, textViewResourceId);
    this.context = context;
}

public void add(OneComment object) {
    countries.add(object);
    super.add(object);
}

public int getCount() {
    return this.countries.size();
}

public OneComment getItem(int index) {
    return this.countries.get(index);
}

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_listview, parent, false);
        }

        wrapperMensageItem = (LinearLayout) row.findViewById(R.id.wrapperMensageItem);
        OneComment coment = getItem(position);

        String inicioMensagem = "";

        try {
            inicioMensagem = coment.comment.trim().substring(0,4);
        } catch (Exception e) {}

        System.out.println(inicioMensagem);
        if (inicioMensagem.equals("@gif")) {

            //Esconde textview fixo
            textViewItemMensage = (TextView) row.findViewById(R.id.textViewItemMensage);
            textViewItemMensage.setVisibility(View.GONE);
            textViewItemMensage.setText("");

            coment.comment = coment.comment.replace(inicioMensagem, "").trim();

            this.imageGif = new ImageView(context);
            this.imageGif = (ImageView) row.findViewById(R.id.imageGif);
//            imageGif.setBackgroundResource(coment.left ? R.drawable.buble2 : R.drawable.buble);

            findRandomGif(coment.comment);
            wrapperMensageItem.setGravity(coment.left ? Gravity.LEFT : Gravity.RIGHT);
        } else {
            textViewItemMensage = (TextView) row.findViewById(R.id.textViewItemMensage);
            textViewItemMensage.setText(coment.comment);
            textViewItemMensage.setBackgroundResource(coment.left ? R.drawable.buble2 : R.drawable.buble);
            wrapperMensageItem.setGravity(coment.left ? Gravity.LEFT : Gravity.RIGHT);
        }

        return row;
    }


    private void findRandomGif(String tag) {
        String url = "http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC&tag="+tag;

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response != null && response.length() > 0) {
                        JSONObject data = response.getJSONObject("data");

                        //Pega url do gif de tamanho fixo
                        String gifUrl = data.getString("fixed_width_downsampled_url");

                        setGifIntoImage(gifUrl);

                    } else { System.out.println("Nenhum gif foi encontrado."); }
                } catch (Exception e) { System.out.println("ERRO: " + e.getMessage()); }
            }
        };
        Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { System.out.println("ERRO: " + error.getMessage()); }
        };
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, responseListener, responseErrorListener);

        RestRequestManager.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    private void setGifIntoImage(String url) {
        Glide.with(context)
                .load(url)
                .asGif()
//                .error(R.drawable.) // show error drawable if the image is not a gif
                .into(this.imageGif);
    }
}