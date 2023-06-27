package com.example.portaldaneshjo.Activity.Omor_mali_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import com.example.portaldaneshjo.Adapter.ListView.ListViewAdapter_pardakhtnamovafagh;
import com.example.portaldaneshjo.Model.ListItem_PardakhtNamovafagh;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class Pardakht_namovafagh extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView list_namovafagh;
    private ArrayList<ListItem_PardakhtNamovafagh> lsModel;
    private ListViewAdapter_pardakhtnamovafagh lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pardakht_namovafagh);

        toolbar = (Toolbar) findViewById(R.id.toolbar_pardakht_error_id);
        list_namovafagh = (ListView) findViewById(R.id.list_pardakht_namovafagh_id);


        toolbar.setTitle("پرداخت های ناموفق");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lsModel = new ArrayList<ListItem_PardakhtNamovafagh>();
        lAdapter = new ListViewAdapter_pardakhtnamovafagh(this,lsModel);
        itemDetails();
        list_namovafagh.setAdapter(lAdapter);
    }

    public void itemDetails(){
        lsModel.add(new ListItem_PardakhtNamovafagh(8854754,0,501248,"98/01/15","20:45"));
        lsModel.add(new ListItem_PardakhtNamovafagh(4511247,0,652184,"98/11/25","20:45"));
        lsModel.add(new ListItem_PardakhtNamovafagh(8754213,0,102150,"98/10/11","20:45"));
        lsModel.add(new ListItem_PardakhtNamovafagh(5487541,0,321548,"98/09/18","20:45"));
        lsModel.add(new ListItem_PardakhtNamovafagh(3265487,0,325148,"98/07/05","20:45"));
        lsModel.add(new ListItem_PardakhtNamovafagh(6514287,0,698745,"98/08/16","20:45"));
        lsModel.add(new ListItem_PardakhtNamovafagh(1205487,0,101542,"98/08/10","20:45"));
    }
}
