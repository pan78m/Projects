package com.example.portaldaneshjo.Activity.Omor_amozeshi_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.R;

public class EterazNomre extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eteraz_nomre);

        toolbar = (Toolbar) findViewById(R.id.toolbar_eteraz_id);

        toolbar.setTitle("اعتراض نمرات");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
