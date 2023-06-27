package com.example.portaldaneshjo.Activity.Omor_mali_activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.portaldaneshjo.Adapter.ListView.ListViewAdapter_VaziateMali;
import com.example.portaldaneshjo.Adapter.SpinnerAdapter_Nimsal;
import com.example.portaldaneshjo.Model.ListItem_VaziateMali;
import com.example.portaldaneshjo.Model.Nimsaltahsili;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class Vaziate_mali extends AppCompatActivity {

     private Toolbar toolbar;
     private Spinner spinner;
     private SpinnerAdapter_Nimsal sAdapter;
     private ListView list_vaziat;
     private ArrayList<ListItem_VaziateMali> listItemVaziateMalis;
     private ListViewAdapter_VaziateMali listAdapter;
     private TextView txt_sumbestankar,txt_sumbedehkar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaziate_mali);

        toolbar = (Toolbar) findViewById(R.id.toolbar_vaziat_id);
        spinner = (Spinner) findViewById(R.id.spn_nimsaltahsili_vaziat_id);
        list_vaziat = (ListView) findViewById(R.id.list_vaziat_id);
        txt_sumbedehkar = (TextView) findViewById(R.id.txt_sumofbedehkar_id);
        txt_sumbestankar = (TextView) findViewById(R.id.txt_sumofbestankar_id);

        toolbar.setTitle("وضعیت مالی");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<Nimsaltahsili> nimsaltahsili = new ArrayList<>();
         nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 97 - 98"));
         nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 97 - 98"));
         nimsaltahsili.add(new Nimsaltahsili("نیمسال اول 98 - 99"));
         nimsaltahsili.add(new Nimsaltahsili("نیمسال دوم 98 - 99"));

         sAdapter = new SpinnerAdapter_Nimsal(Vaziate_mali.this,nimsaltahsili);
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
         //اسپینر برای انتخاب نیمسال تحصیلی

         listItemVaziateMalis = new ArrayList<ListItem_VaziateMali>();
         listAdapter = new ListViewAdapter_VaziateMali(this,listItemVaziateMalis);
         itemDetails();
         list_vaziat.setAdapter(listAdapter);
    }

    private void itemDetails()
    {
        listItemVaziateMalis.add(new ListItem_VaziateMali("سفته مجدد",554487542,"97/02/24","0",5448754,657884));
        listItemVaziateMalis.add(new ListItem_VaziateMali("فیش بانکی",8457715,"97/08/24","0",5448754,657884));
        listItemVaziateMalis.add(new ListItem_VaziateMali("شهریه",944587,"97/12/24","0",5448754,657884));
        listItemVaziateMalis.add(new ListItem_VaziateMali("فیش بانکی",2331254,"98/02/24","0",5448754,657884));
        listItemVaziateMalis.add(new ListItem_VaziateMali("فیش بانکی",44516484,"98/04/24","0",5448754,657884));
    }
}
