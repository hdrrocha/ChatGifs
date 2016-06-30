package com.example.helder.chatgifs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Random;


public class FragmentChat extends Fragment {
    private static Random random;
    // Array of mensagens
    String[] mobileArray = {"Sangue e destruiçao", "O pé de pedro é preto", "lalala", "mensagem proficional", "nao sei o que escrever", "teste", "The Darknes", "Lord das trevas"};
    ListDisplay adapter;
    private ListView listViewMensagesList;
    private EditText editTextMensage;
    private Button buttonSendMensage;

    public FragmentChat() {
    }

    public static FragmentChat newInstance() {
        return new FragmentChat();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        buttonSendMensage = (Button) view.findViewById(R.id.buttonSendMensage);
        editTextMensage = (EditText) view.findViewById(R.id.editTextMensage);


        listViewMensagesList = (ListView) view.findViewById(R.id.listViewMensagesList);
        initialize();

//
//        Fragment fragment = null;
//        fragment.getArguments().getString("nickName");
//
//
//        Bundle extras = getArguments();
//        if (fragment != null) {
//            editTextMensage.setFocusable(false);
//            editTextMensage.setClickable(false);
//        } else {
//            editTextMensage.setFocusable(true);
//            editTextMensage.setClickable(true);
//        }
        return view;
    }

    private void initialize() {
        random = new Random();
        setListViewValues();
        setEvents();
        addItems();

    }

    private void setEvents() {
        buttonSendMensage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Carol aqui envia a imagem que vc tem que por pro gif
                adapter.add(new OneComment(false, editTextMensage.getText().toString()));
                editTextMensage.setText("");
            }
        });
    }

    private void setListViewValues() {
        adapter = new ListDisplay(getContext(), R.layout.activity_listview);
        listViewMensagesList.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addItems() {
        for (int i = 0; i < mobileArray.length; i++) {
            boolean left = getRandomInteger(0, 1) == 0 ? true : false;
            String words = mobileArray[i];
            adapter.add(new OneComment(true, words));
        }
    }
}
