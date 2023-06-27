package com.example.portaldaneshjo.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.portaldaneshjo.Model.RecyclerItem_BarnameHaftegi;
import com.example.portaldaneshjo.R;

import java.util.List;

public class RecyclerAdapter_Barnamehaftegi extends RecyclerView.Adapter<RecyclerAdapter_Barnamehaftegi.viewHolder> {

    private Context context;
    private List<RecyclerItem_BarnameHaftegi> model;

    public RecyclerAdapter_Barnamehaftegi(Context context, List<RecyclerItem_BarnameHaftegi> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_barnamehaftegi,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        RecyclerItem_BarnameHaftegi rb = model.get(i);
        viewHolder.lessonname.setText(rb.getLessonname());
        viewHolder.vahed_nazari.setText(String.valueOf(rb.getVahed_N()));
        viewHolder.vahed_amali.setText(String.valueOf(rb.getVahed_A()));
        viewHolder.classtime.setText(rb.getClasstime());
        viewHolder.examdate.setText(rb.getExamdate());
        viewHolder.teachername.setText(rb.getTeachername());

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView lessonname , vahed_nazari , vahed_amali , classtime , examdate , teachername;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            lessonname = (TextView) itemView.findViewById(R.id.txt_lessonname_id);
            vahed_nazari = (TextView) itemView.findViewById(R.id.txt_vahed_nazari_id);
            vahed_amali = (TextView) itemView.findViewById(R.id.txt_vahed_amali_id);
            classtime = (TextView) itemView.findViewById(R.id.txt_classtime_id);
            examdate = (TextView) itemView.findViewById(R.id.txt_examdate_id);
            teachername = (TextView) itemView.findViewById(R.id.txt_teachername_id);
        }
    }
}
