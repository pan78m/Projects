package com.example.portaldaneshjo.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.portaldaneshjo.Model.RecyclerItem_Karname;
import com.example.portaldaneshjo.R;
import java.util.ArrayList;

public class RecyclerAdapter_Karname extends RecyclerView.Adapter<RecyclerAdapter_Karname.viewHolder> {

     private Context context;
     private ArrayList<RecyclerItem_Karname> model;

    public RecyclerAdapter_Karname(Context context, ArrayList<RecyclerItem_Karname> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_karname_simple,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        RecyclerItem_Karname rk = model.get(i);

        viewHolder.vaziat.setText(rk.getVaziat());
        viewHolder.nomre.setText(rk.getNomre());
        viewHolder.teacher.setText(rk.getTeacher());
        viewHolder.lessonname.setText(rk.getLessonname());
        viewHolder.lessoncode.setText(rk.getLessoncode());
        viewHolder.score.setText(String.valueOf(rk.getScore()));
        viewHolder.amali.setText(String.valueOf(rk.getAmali()));
        viewHolder.nazari.setText(String.valueOf(rk.getNazari()));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView score , amali , nazari , vaziat , nomre , teacher , lessonname , lessoncode;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            score = (TextView) itemView.findViewById(R.id.txt_score_id);
            amali = (TextView) itemView.findViewById(R.id.txt_amali_id);
            nazari = (TextView) itemView.findViewById(R.id.txt_nazari_id);
            vaziat = (TextView) itemView.findViewById(R.id.txt_vaziat_id);
            nomre = (TextView) itemView.findViewById(R.id.txt_nomre_id);
            teacher = (TextView) itemView.findViewById(R.id.txt_teacher_id);
            lessonname = (TextView) itemView.findViewById(R.id.txt_lesson_id);
            lessoncode = (TextView) itemView.findViewById(R.id.txt_lessoncode_id);
        }
    }
}
