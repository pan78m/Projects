package com.example.portaldaneshjo.Activity.Omor_amozeshi_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.portaldaneshjo.Adapter.RecyclerView.RecyclerAdapter_ZamanBandi;
import com.example.portaldaneshjo.Model.RecyclerItem_ZamanBandi;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class ZamanBandi extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerItem_ZamanBandi> items = new ArrayList<>();
    private RecyclerAdapter_ZamanBandi zadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaman_bandi);

        toolbar = (Toolbar) findViewById(R.id.toolbar_zamanbandi_id);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_zamanbandi_id);

        toolbar.setTitle("زمان بندی ثبت نام");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        zadapter = new RecyclerAdapter_ZamanBandi(ZamanBandi.this,items);
        recyclerView.setLayoutManager(new LinearLayoutManager(ZamanBandi.this));
        showItems();
        recyclerView.setAdapter(zadapter);
    }

    public void showItems(){
        items.add(new RecyclerItem_ZamanBandi("98/04/09","98/04/15","83 تا 95","09:00 تا 24:00","خیر"));
    }
}
