package com.example.portaldaneshjo.Activity.Omor_amozeshi_activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portaldaneshjo.R;

public class KartEmtehan extends AppCompatActivity {

    Toolbar toolbar;
    TextView txt_aiin,txt_taghalobat,txt_takhalofat,txt_tanbihat,txt_tanbihattt;
    CheckBox checkBox;
    Button btn_showkart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kart_emtehan);

        toolbar = (Toolbar) findViewById(R.id.toolbar_kart_id);
        txt_aiin = (TextView) findViewById(R.id.txt_kart01_id);
        txt_taghalobat = (TextView) findViewById(R.id.txt_kart02_id);
        txt_takhalofat = (TextView) findViewById(R.id.txt_kart03_id);
        txt_tanbihat = (TextView) findViewById(R.id.txt_kart04_id);
        txt_tanbihattt = (TextView) findViewById(R.id.txt_kart05_id);
        checkBox = (CheckBox) findViewById(R.id.checkbox_kart_id);
        btn_showkart = (Button) findViewById(R.id.btn_showkart_id);

        Typeface textFont = Typeface.createFromAsset(getAssets(),"fonts/IRANSans.ttf");
        txt_aiin.setTypeface(textFont);
        txt_taghalobat.setTypeface(textFont);
        txt_takhalofat.setTypeface(textFont);
        txt_tanbihat.setTypeface(textFont);
        txt_tanbihattt.setTypeface(textFont);

        btn_showkart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    startActivity(new Intent(KartEmtehan.this,ShowKartEmtehan.class));
                }
                else {
                    Snackbar snackbar = Snackbar.make(v,"لطفا مطالب و قوانین فوق را تایید نمایید !",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

        toolbar.setTitle("کارت امتحان");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
