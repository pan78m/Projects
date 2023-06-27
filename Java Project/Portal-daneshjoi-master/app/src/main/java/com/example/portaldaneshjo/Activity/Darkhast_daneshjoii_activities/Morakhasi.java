package com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.R;

public class Morakhasi extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morakhasi);

        toolbar = (Toolbar) findViewById(R.id.toolbar_morakhasi_id);

        toolbar.setTitle("مرخصی تحصیلی");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
