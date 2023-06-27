package com.example.portaldaneshjo.Activity.Omor_amozeshi_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.R;

public class ShowKartEmtehan extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kart_emtehan);

        toolbar = (Toolbar) findViewById(R.id.toolbar_showkart_id);

        toolbar.setTitle("کارت ورود به جلسه");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
