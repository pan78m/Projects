package com.example.portaldaneshjo.Adapter.FragmentsGridViewAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities.Eshteghal;
import com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities.Ghaza;
import com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities.Khabgah;
import com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities.Morakhasi;
import com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities.Parking;
import com.example.portaldaneshjo.Activity.Darkhast_daneshjoii_activities.Vam;
import com.example.portaldaneshjo.R;

public class GridViewAdapter_Darkhast extends BaseAdapter {

    private Context context;
    private String[] nameitems;
    private Integer[] picname;

    public GridViewAdapter_Darkhast(Context context, String[] nameitems, Integer[] picname) {
        this.context = context;
        this.nameitems = nameitems;
        this.picname = picname;
    }

    @Override
    public int getCount() {
        return nameitems.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Holder{
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final Activity a = (Activity) context;
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.items_gridview_simple,null);
        holder.imageView = (ImageView) view.findViewById(R.id.img_items_id);
        holder.textView = (TextView) view.findViewById(R.id.txt_items_id);

        holder.imageView.setImageResource(picname[position]);
        holder.textView.setText(nameitems[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (nameitems[position]){
                    case "درخواست خوابگاه":{
                        v.getContext().startActivity(new Intent(context, Khabgah.class));break;
                    }
                    case "پارکینگ":{
                        v.getContext().startActivity(new Intent(context, Parking.class));break;
                    }
                    case "رزرو غذا":{
                        v.getContext().startActivity(new Intent(context, Ghaza.class));break;
                    }
                    case "درخواست وام":{
                        v.getContext().startActivity(new Intent(context, Vam.class));break;
                    }
                    case "گواهی اشتغال":{
                        v.getContext().startActivity(new Intent(context, Eshteghal.class));break;
                    }
                    case "مرخصی تحصیلی":{
                        v.getContext().startActivity(new Intent(context, Morakhasi.class));break;
                    }
                }
            }
        });
        return view;
    }
}
