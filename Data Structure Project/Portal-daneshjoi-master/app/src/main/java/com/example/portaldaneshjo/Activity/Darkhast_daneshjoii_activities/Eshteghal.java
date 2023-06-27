package com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.portaldaneshjo.R;

public class Eshteghal extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatEditText Etxt_jahateeraee , Etxt_elatedarkhast;
    private Button btn_submitreq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eshteghal);

        toolbar = (Toolbar) findViewById(R.id.toolbar_eshteghal_id);
        Etxt_elatedarkhast = (AppCompatEditText) findViewById(R.id.edittxt_elatedarkhast_id);
        Etxt_jahateeraee = (AppCompatEditText) findViewById(R.id.edittxt_jahateeraee_id);
        btn_submitreq = (Button) findViewById(R.id.btn_submitreq_id);

        toolbar.setTitle("گواهی اشتغال");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_submitreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Etxt_elatedarkhast.getText().length() != 0 && Etxt_jahateeraee.getText().length() != 0){
                    Snackbar snackbar = Snackbar.make(v,"درخواست با موفقیت ثبت شد !",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    Snackbar snackbar = Snackbar.make(v,"لطفا تمام اطلاعات را تکمیل کنید !",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }
}
