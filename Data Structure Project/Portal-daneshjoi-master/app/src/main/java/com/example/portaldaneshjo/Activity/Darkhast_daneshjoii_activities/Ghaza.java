package com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.R;

public class Ghaza extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghaza);

        toolbar = (Toolbar) findViewById(R.id.toolbar_ghaza_id);

        toolbar.setTitle("رزرو غذا");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
