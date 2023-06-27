package com.example.portaldaneshjo.Fragments.Entekhabvahed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.portaldaneshjo.R;

public class Sabt_moshakhase extends Fragment {

    private TextInputLayout inputtxt;
    private Button btn_sabt_dars;
    private AppCompatEditText txt_shenase;

    public Sabt_moshakhase() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sabt_moshakhase,container,false);

        btn_sabt_dars = (Button) view.findViewById(R.id.btn_sabt_moshakhase_id);
        txt_shenase = (AppCompatEditText) view.findViewById(R.id.Etxt_moshakhase_dars_id);
        inputtxt = (TextInputLayout) view.findViewById(R.id.inputlayout_moshakhase_id);

        btn_sabt_dars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"مشخصۀ "+txt_shenase.getText()+" با موفقیت ثبت شد !",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        return view;
    }
}
