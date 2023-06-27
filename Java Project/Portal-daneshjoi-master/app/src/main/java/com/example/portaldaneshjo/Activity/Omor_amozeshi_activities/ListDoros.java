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

import com.example.portaldaneshjo.Adapter.RecyclerView.RecyclerAdapter_Listdoros;
import com.example.portaldaneshjo.Adapter.SpinnerAdapter_Nimsal;
import com.example.portaldaneshjo.Model.Nimsaltahsili;
import com.example.portaldaneshjo.Model.RecyclerItem_Listdoros;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class ListDoros extends AppCompatActivity {

    Toolbar toolbar;
    private Spinner spinner;
    private SpinnerAdapter_Nimsal sAdapter;
    private ArrayList<Nimsaltahsili> nimsaltahsili = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArrayList<RecyclerItem_Listdoros> items = new ArrayList<>();
    private RecyclerAdapter_Listdoros radapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doros);

        toolbar = (Toolbar) findViewById(R.id.toolbar_listdoros_id);
        spinner = (Spinner) findViewById(R.id.spn_nimsaltahsili_listdoros_id);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_listdoros_id);

        toolbar.setTitle("لیست دروس");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ساخت اسپینر نیمسال تحصیلی
        showSpinnerItems();
        sAdapter = new SpinnerAdapter_Nimsal(ListDoros.this,nimsaltahsili);
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

        //ساخت ایتم های لیست

        radapter = new RecyclerAdapter_Listdoros(getApplicationContext(),items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showListItems();
        recyclerView.setAdapter(radapter);

    }

    public void showSpinnerItems(){
        nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 97 - 98"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 97 - 98"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 98 - 99"));
        nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 98 - 99"));
    }

    public void showListItems(){
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
        items.add(new RecyclerItem_Listdoros("برنامه نویسی پیشرفته","98/10/25","دوشنبه 15:15 تا 17:45 B307 فنی","محبوبه مقیمی",2487,3));
    }
}
