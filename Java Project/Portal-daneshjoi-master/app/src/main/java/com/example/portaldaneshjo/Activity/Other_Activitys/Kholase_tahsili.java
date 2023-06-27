
package com.example.portaldaneshjo.Activity.Other_Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.R;

public class Kholase_tahsili extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kholase_tahsili);

        toolbar = (Toolbar) findViewById(R.id.toolbar_kholase_tahsili_id);

        toolbar.setTitle("خلاصه وضعیت تحصیلی");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
