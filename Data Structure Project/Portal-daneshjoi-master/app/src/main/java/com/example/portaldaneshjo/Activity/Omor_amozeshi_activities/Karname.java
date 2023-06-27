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

import com.example.portaldaneshjo.Adapter.RecyclerView.RecyclerAdapter_Karname;
import com.example.portaldaneshjo.Adapter.SpinnerAdapter_Nimsal;
import com.example.portaldaneshjo.Model.Nimsaltahsili;
import com.example.portaldaneshjo.Model.RecyclerItem_Karname;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class Karname extends AppCompatActivity {

    Toolbar toolbar;
    private Spinner spinner;
    private SpinnerAdapter_Nimsal sAdapter;
    private RecyclerView recyclerView;
    private RecyclerAdapter_Karname kadapter;
    private ArrayList<RecyclerItem_Karname> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karname);

        toolbar = (Toolbar) findViewById(R.id.toolbar_karname_id);
        spinner = (Spinner) findViewById(R.id.spn_nimsaltahsili_karname_id);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_karname_id);

        toolbar.setTitle("کارنامه");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //اسپینر برای انتخاب نیمسال تحصیلی

        final ArrayList<Nimsaltahsili> nimsaltahsili = new ArrayList<>();
        nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 97 - 98"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 97 - 98"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 98 - 99"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 98 - 99"));

        sAdapter = new SpinnerAdapter_Nimsal(Karname.this,nimsaltahsili);
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

        kadapter = new RecyclerAdapter_Karname(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showItemsData();
        recyclerView.setAdapter(kadapter);
    }

    public void showItemsData(){
        items.add(new RecyclerItem_Karname("عـادی","احسان زادخوش","مدار منطقی","2154",45,0,3,"15.00"));
        items.add(new RecyclerItem_Karname("حذف ماده 31","احسان زادخوش","معماری کامپیوتر","254877",24,0,3,"08.00"));
        items.add(new RecyclerItem_Karname("عـادی","محبوبه مقیمی","برنامه نویسی پیشرفته","171",60,0,3,"20.00"));
        items.add(new RecyclerItem_Karname("عـادی","مهرداد آزادی","معادلات دیفرانسیل","8585",36,0,3,"12.00"));
        items.add(new RecyclerItem_Karname("عـادی","پژمان شاه حسینی","مدار الکتریکی","421313",30,0,3,"10.00"));
        items.add(new RecyclerItem_Karname("عـادی","سروش مبشری","هوش مصنوعی","12120",57,0,3,"19.00"));
    }
}
