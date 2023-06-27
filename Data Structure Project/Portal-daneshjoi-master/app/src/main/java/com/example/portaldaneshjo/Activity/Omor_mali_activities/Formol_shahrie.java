package com.example.portaldaneshjo.Activity.Omor_mali_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.R;

public class Formol_shahrie extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formol_shahrie);

        toolbar = (Toolbar) findViewById(R.id.toolbar_formol_id);

        toolbar.setTitle("فرمول محاسبه ی شهریه");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
