package com.example.helder.chatgifs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentLogar extends Fragment {

    private Button buttonEntrar;
    private EditText editName;

    public FragmentLogar() {
    }

    public static FragmentLogar newInstance() {
        return new FragmentLogar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logar, container, false);
        buttonEntrar = (Button) view.findViewById(R.id.buttonEntrar);
        editName = (EditText) view.findViewById(R.id.editName);
        editName.setText("");
        initialize();
        return view;
    }

    public void initialize() {
        getEvents();
    }

    public void getEvents() {
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nickName = editName.getText().toString();

                if (nickName.isEmpty()) {
                    Context context = getContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Insira uma NickName para iniciar um chat", duration);
                    toast.show();

                }else{
                    String mess = getResources().getString(R.string.nick_name);
                    mess = nickName;
//                    Bundle data = new Bundle();
//                    data.putString("nickName", nickName);
//                    FragmentTransaction t = getActivity().getSupportFragmentManager()
//                            .beginTransaction();
//                    FragmentChat mFrag = new FragmentChat();
//                    mFrag.setArguments(data);
//                    t.replace(R.id.meuFragment, mFrag);
//                    t.commit();
                }


            }
        });
    }


}


