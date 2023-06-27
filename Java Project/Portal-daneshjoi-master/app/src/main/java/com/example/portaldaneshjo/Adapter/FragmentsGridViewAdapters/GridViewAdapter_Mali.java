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

import com.example.portaldaneshjo.Activity.Omor_mali_activities.Formol_shahrie;
import com.example.portaldaneshjo.Activity.Omor_mali_activities.Pardakht_electronic;
import com.example.portaldaneshjo.Activity.Omor_mali_activities.Pardakht_namovafagh;
import com.example.portaldaneshjo.Activity.Omor_mali_activities.Vaziate_mali;
import com.example.portaldaneshjo.R;

public class GridViewAdapter_Mali extends BaseAdapter {
    private Context context;
    private String[] nameitems;
    private Integer[] picitems;

    public GridViewAdapter_Mali(Context context, String[] nameitems, Integer[] picitems) {
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final Activity a = (Activity) context;
        final View view;
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
                    case "پرداخت الکترونیک":{
                        v.getContext().startActivity(new Intent(context, Pardakht_electronic.class));break;
                    }
                    case "وضعیت مالی":{
                        v.getContext().startActivity(new Intent(context, Vaziate_mali.class));break;
                    }
                    case "محاسبه شهریه":{
                        v.getContext().startActivity(new Intent(context, Formol_shahrie.class));break;
                    }
                    case "پرداخت های ناموفق":{
                        v.getContext().startActivity(new Intent(context, Pardakht_namovafagh.class));break;
                    }
                }
            }
        });
        return view;
    }
}
