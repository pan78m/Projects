package com.example.portaldaneshjo.Activity.Omor_mali_activities;

import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.portaldaneshjo.R;

public class Pardakht_electronic extends AppCompatActivity {

    Toolbar toolbar;
    TextView txt_bank_names,txt_content,txt_mablagh,txt_tozih;
    EditText txt_mablaghe_pardakhti;
    ImageButton btn_melli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pardakht_electronic);

        toolbar =(Toolbar) findViewById(R.id.toolbar_pardakht_e_id);
        txt_bank_names = (TextView) findViewById(R.id.txt_bank_name_id);
        txt_content = (TextView) findViewById(R.id.txt_items_id);
        txt_mablagh = (TextView) findViewById(R.id.txt_mablagh_id);
        txt_tozih = (TextView) findViewById(R.id.txt_tozih_id);
        btn_melli = (ImageButton) findViewById(R.id.btn_dargah_pardakht_id);
        txt_mablaghe_pardakhti = (EditText) findViewById(R.id.mablaghe_pardakhti_id);

        Typeface txt = Typeface.createFromAsset(getAssets(),"fonts/IRANSans.ttf");
        txt_bank_names.setTypeface(txt);
        txt_content.setTypeface(txt);
        txt_mablagh.setTypeface(txt);
        txt_tozih.setTypeface(txt);

        toolbar.setTitle("پرداخت الکترونیک");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_melli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_mablaghe_pardakhti.getText().length()!=0){
                    Snackbar snackbar = Snackbar.make(v,"درگاه پرداخت ",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    Snackbar snackbar = Snackbar.make(v,"لطفا مبلغ را وارد کنید !",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }
}
