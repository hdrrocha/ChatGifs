package com.example.helder.chatgifs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Random;

/**
 * Created by helder on 27/06/16.
 */
public class ActivityChat extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        // Array of mensagens
        String[] mobileArray = {"Sangue e destruiçao", "O pé de pedro é preto", "lalala", "mensagem proficional", "nao sei o que escrever", "teste", "The Darknes", "Lord das trevas"};
        private ListView listViewMensagesList;
        private EditText editTextMensage;
        private Button buttonSendMensage;
        private static Random random;
        String gifUrl;
        ListDisplay adapter;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initialize();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        }

@Override
public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
        } else {
        super.onBackPressed();
        }
        }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }

        return super.onOptionsItemSelected(item);
        }

        @Override
        protected void onPostCreate(@Nullable Bundle savedInstanceState) {
                super.onPostCreate(savedInstanceState);
        }

        @SuppressWarnings("StatementWithEmptyBody")
@Override
public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logar) {
//        Intent intent = new Intent(this, .class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
        } else if (id == R.id.nav_chat) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }

        private void initialize() {
                random = new Random();
                getComponentes();
                setListViewValues();
                setEvents();
                addItems();

        }
        private void getComponentes() {
                buttonSendMensage = (Button) findViewById(R.id.buttonSendMensage);
                editTextMensage = (EditText) findViewById(R.id.editTextMensage);
                listViewMensagesList = (ListView) findViewById(R.id.listViewMensagesList);
        }

        private void setEvents() {
                buttonSendMensage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                adapter.add(new OneComment(false, editTextMensage.getText().toString()));
                                editTextMensage.setText("");
                        }
                });
        }

        private void setListViewValues() {
                adapter = new ListDisplay(getApplicationContext(), R.layout.activity_listview);
                listViewMensagesList.setAdapter(adapter);

        }

        @Override
        public void onPanelClosed(int featureId, Menu menu) {
                super.onPanelClosed(featureId, menu);
        }

        private void addItems() {
                for (int i = 0; i < mobileArray.length; i++) {
                        boolean left = getRandomInteger(0, 1) == 0 ? true : false;
                        String words = mobileArray[i];
                        adapter.add(new OneComment(true, words));
                }
        }

        private static int getRandomInteger(int aStart, int aEnd) {
                if (aStart > aEnd) {
                        throw new IllegalArgumentException("Start cannot exceed End.");
                }
                long range = (long) aEnd - (long) aStart + 1;
                long fraction = (long) (range * random.nextDouble());
                int randomNumber = (int) (fraction + aStart);
                return randomNumber;
        }

        //Retorna a url do gif encontrado
        public void findRandomGif(String tag) {
                String url = "http://api.giphy.com/v1/gifs/random?api_key=dc6zaTOxFJmzC&";

                //Busca por c categoria caso tiver
                if (!tag.equals("")) {
                        url += "tag="+tag;
                }

                Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                                try {
                                        if(response != null && response.length() > 0) {
                                                JSONObject data = response.getJSONObject("data");

                                                //Pega url do gif de tamanho fixo e joga na variável global
                                                gifUrl = data.getString("fixed_width_downsampled_url");

                                                //Seta url do gif para aparecer no imageView
//                                                setGifIntoImage(gifUrl);

                                        } else { System.out.println("Nenhum gif foi encontrado."); }
                                } catch (Exception e) { System.out.println("ERRO: " + e.getMessage()); }
                        }
                };
                Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) { System.out.println("ERRO: " + error.getMessage()); }
                };
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, responseListener, responseErrorListener);

                RestRequestManager.getInstance(ActivityChat.this).addToRequestQueue(jsObjRequest);
        }


}
