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
import com.example.portaldaneshjo.Activity.Omor_amozeshi_activities.BarnameHaftegi;
import com.example.portaldaneshjo.Activity.Omor_amozeshi_activities.EterazNomre;
import com.example.portaldaneshjo.Activity.Omor_amozeshi_activities.Karname;
import com.example.portaldaneshjo.Activity.Omor_amozeshi_activities.KartEmtehan;
import com.example.portaldaneshjo.Activity.Omor_amozeshi_activities.ListDoros;
import com.example.portaldaneshjo.Activity.Omor_amozeshi_activities.ZamanBandi;
import com.example.portaldaneshjo.R;

public class GridViewAdapter_Amozeshi extends BaseAdapter {
    private Context context;
    private String[] nameitems;
    private Integer[] picitems;

    public GridViewAdapter_Amozeshi(Context context, String[] nameitems, Integer[] picitems) {
        this.context = context;
        this.nameitems = nameitems;
        this.picitems = picitems;
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

        holder.imageView.setImageResource(picitems[position]);
        holder.textView.setText(nameitems[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (nameitems[position]){
                    case "برنامه هفتگی":{
                        v.getContext().startActivity(new Intent(context, BarnameHaftegi.class));break;
                    }

                    case "اعتراض نمرات":{
                        v.getContext().startActivity(new Intent(context, EterazNomre.class));break;
                    }
                    case "کارنامه":{
                        v.getContext().startActivity(new Intent(context, Karname.class));break;
                    }
                    case "لیست دروس":{
                        v.getContext().startActivity(new Intent(context, ListDoros.class));break;
                    }
                    case "زمان بندی ثبت نام":{
                        v.getContext().startActivity(new Intent(context, ZamanBandi.class));break;
                    }
                    case "کارت امتحان":{
                        v.getContext().startActivity(new Intent(context, KartEmtehan.class));break;
                    }
                }
            }
        });
        return view;
    }
}
