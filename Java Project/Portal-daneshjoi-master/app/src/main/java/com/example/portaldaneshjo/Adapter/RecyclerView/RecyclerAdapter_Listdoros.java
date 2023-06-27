package com.example.portaldaneshjo.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.portaldaneshjo.Model.RecyclerItem_Listdoros;
import com.example.portaldaneshjo.R;


import java.util.ArrayList;

public class RecyclerAdapter_Listdoros extends RecyclerView.Adapter<RecyclerAdapter_Listdoros.viewHolder> {

    private Context context;
    private ArrayList<RecyclerItem_Listdoros> model;

    public RecyclerAdapter_Listdoros(Context context, ArrayList<RecyclerItem_Listdoros> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_listdoros_simple,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        RecyclerItem_Listdoros rl = model.get(i);
        viewHolder.txt_lessonid.setText(String.valueOf(rl.getLessonid()));
        viewHolder.txt_lessonname.setText(rl.getLessonname());
        viewHolder.txt_vahed.setText(String.valueOf(rl.getVahed()));
        viewHolder.txt_classtime.setText(rl.getClasstime());
        viewHolder.txt_examdate.setText(rl.getExamdate());
        viewHolder.txt_teachername.setText(rl.getTeachername());

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txt_lessonname , txt_lessonid , txt_vahed , txt_classtime , txt_examdate , txt_teachername;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txt_lessonid = (TextView) itemView.findViewById(R.id.txt_lessonid_listdoros_id);
            txt_lessonname = (TextView) itemView.findViewById(R.id.txt_lessonname_listdoros_id);
            txt_vahed = (TextView) itemView.findViewById(R.id.txt_vahed_listdoros_id);
            txt_classtime = (TextView) itemView.findViewById(R.id.txt_classtime_listdoros_id);
            txt_examdate = (TextView) itemView.findViewById(R.id.txt_examdate_listdoros_id);
            txt_teachername = (TextView) itemView.findViewById(R.id.txt_teachername_listdoros_id);
        }
    }
}
