package com.example.portaldaneshjo.Activity.Splash;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.portaldaneshjo.Activity.Other_Activitys.LoginActivity;
import com.example.portaldaneshjo.R;

public class Splash extends AppCompatActivity {

    private Handler handler;
    private TextView txtwelcome,tnbclub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtwelcome = (TextView) findViewById(R.id.txt_welcome_id);
        tnbclub = (TextView) findViewById(R.id.txt_bottom_id);

        Typeface txt_tnb_font = Typeface.createFromAsset(getAssets(),"fonts/font1.TTF");
        tnbclub.setTypeface(txt_tnb_font);

        Typeface txt_welcome_font = Typeface.createFromAsset(getAssets(),"fonts/4.ttf");
        txtwelcome.setTypeface(txt_welcome_font);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
