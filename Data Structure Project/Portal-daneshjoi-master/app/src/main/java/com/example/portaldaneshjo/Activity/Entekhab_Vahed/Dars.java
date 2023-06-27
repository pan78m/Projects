package com.example.portaldaneshjo.Activity.Entekhab_Vahed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.portaldaneshjo.Adapter.RecyclerView.RecyclerAdapter_Entekhabdars;
import com.example.portaldaneshjo.Model.RecyclerItem_EntekhabDars;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class Dars extends AppCompatActivity {

    private Toolbar toolbar;
     RecyclerView recyclerView;
     ArrayList<RecyclerItem_EntekhabDars> items = new ArrayList<>();
     RecyclerAdapter_Entekhabdars adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dars);

        toolbar = (Toolbar) findViewById(R.id.toolbar_entekhabdars_id);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_dars_id);

        toolbar.setTitle(getIntent().getStringExtra("namedars"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new RecyclerAdapter_Entekhabdars(getBaseContext(),items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        showdata0();
        recyclerView.setAdapter(adapter);

    }

    public void showdata0(){
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
        items.add(new RecyclerItem_EntekhabDars(245,20,"دوشنبه 13:30 تا 15:15 307 فنی","98/10/25","محبوبه مقیمی"));
    }
}
