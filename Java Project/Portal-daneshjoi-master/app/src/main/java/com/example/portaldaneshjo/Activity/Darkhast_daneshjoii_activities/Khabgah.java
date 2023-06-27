package com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.R;

public class Khabgah extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khabgah);

        toolbar = (Toolbar) findViewById(R.id.toolbar_khabgah_id);

        toolbar.setTitle("درخواست خوابگاه");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
