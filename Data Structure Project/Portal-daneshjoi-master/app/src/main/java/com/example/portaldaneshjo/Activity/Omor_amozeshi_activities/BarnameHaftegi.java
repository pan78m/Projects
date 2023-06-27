package com.example.portaldaneshjo.Activity.Omor_amozeshi_activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.portaldaneshjo.Adapter.RecyclerView.RecyclerAdapter_Barnamehaftegi;
import com.example.portaldaneshjo.Adapter.SpinnerAdapter_Nimsal;
import com.example.portaldaneshjo.Model.RecyclerItem_BarnameHaftegi;
import com.example.portaldaneshjo.Model.Nimsaltahsili;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class BarnameHaftegi extends AppCompatActivity {

    Toolbar toolbar;
    private Spinner spinner;
    private SpinnerAdapter_Nimsal sAdapter;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerItem_BarnameHaftegi> items = new ArrayList<>();
    private RecyclerAdapter_Barnamehaftegi radapter;
    ArrayList<Nimsaltahsili> nimsaltahsili = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barname_haftegi);

        toolbar = (Toolbar) findViewById(R.id.toolbar_barnamehaftegi_id);
        spinner = (Spinner) findViewById(R.id.spn_nimsaltahsili_barnamehaftegi_id);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_barnamehaftegi_id);

        toolbar.setTitle("برنامه هفتگی");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //اسپینر برای انتخاب نیمسال تحصیلی

        showSpinnerItems();
        sAdapter = new SpinnerAdapter_Nimsal(BarnameHaftegi.this,nimsaltahsili);
        spinner.setAdapter(sAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Snackbar snackbar = Snackbar.make(view,nimsaltahsili.get(position).getNimsal(),Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //آیتم های برنامه هفتگی

        radapter = new RecyclerAdapter_Barnamehaftegi(getApplicationContext(),items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showListItems();
        recyclerView.setAdapter(radapter);

    }

    public void showListItems(){
        items.add(new RecyclerItem_BarnameHaftegi("احسان زادخوش","معماری کامپیوتر","سه شنبه 13:15 تا 15:30 B307 فنی", "98/10/24",3,0));
        items.add(new RecyclerItem_BarnameHaftegi("محبوبه مقیمی","ساختمان داده","شنبه 10:05 تا 12:20 B217 فنی", "98/10/20",3,0));
        items.add(new RecyclerItem_BarnameHaftegi("پژمان شاه حسینی","آزمایشگاه مدار های منطقی","پنج شنبه 13:15 تا 15:30 415 شیمی", "98/10/18",0,1));
        items.add(new RecyclerItem_BarnameHaftegi("شمس علی زارعیان","انقلاب اسلامی"," شنبه 17:15 تا 18:30 111 مدیریت", "98/10/17",1,0));
        items.add(new RecyclerItem_BarnameHaftegi("سروش مبشری","هوش مصنوعی","دو شنبه 13:15 تا 15:30 B309 فنی", "98/10/17",3,0));
        items.add(new RecyclerItem_BarnameHaftegi("مهرداد آزادی","ریاضیات گسسته","دو شنبه 15:15 تا 17:30 B115 فنی", "98/10/15",3,0));
    }

    public void showSpinnerItems(){
        nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 97 - 98"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 97 - 98"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 98 - 99"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 98 - 99"));
    }
}
