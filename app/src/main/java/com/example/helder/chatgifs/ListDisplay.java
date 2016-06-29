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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ListDisplay extends ArrayAdapter<OneComment> {

private TextView textViewItemMensage;
private ImageView imageResponse;
private List<OneComment> countries = new ArrayList<OneComment>();
private LinearLayout wrapperMensageItem;


public ListDisplay(Context context, int textViewResourceId) {
    super(context, textViewResourceId);
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

//Seta gif da url dentro da imagem
public void setGifIntoImage(String url, Context context) {
    Glide.with(context) // replace with 'this' if it's in activity
            .load(url)
            .asGif()
            .into(this.imageResponse);

}

public View getView(int position, View convertView, ViewGroup parent) {
    View row = convertView;
    if (row == null) {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.activity_listview, parent, false);
    }

    wrapperMensageItem = (LinearLayout) row.findViewById(R.id.wrapperMensageItem);
    OneComment coment = getItem(position);

    textViewItemMensage = (TextView) row.findViewById(R.id.textViewItemMensage);
    textViewItemMensage.setText(coment.comment);
    textViewItemMensage.setBackgroundResource(coment.left ? R.drawable.buble2 : R.drawable.buble);
    wrapperMensageItem.setGravity(coment.left ? Gravity.LEFT : Gravity.RIGHT);
    return row;
}
}