package com.example.portaldaneshjo.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.portaldaneshjo.Model.RecyclerItem_EntekhabDars;
import com.example.portaldaneshjo.R;

import java.util.List;

public class RecyclerAdapter_Entekhabdars extends RecyclerView.Adapter<RecyclerAdapter_Entekhabdars.ViewHolder> {

    Context context;
    List<RecyclerItem_EntekhabDars> model;

    public RecyclerAdapter_Entekhabdars(Context context, List<RecyclerItem_EntekhabDars> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_entekhab_dars_simple,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final RecyclerItem_EntekhabDars rv = model.get(i);
        viewHolder.lessoncode.setText(String.valueOf(rv.getMoshakhase()));
        viewHolder.zarfiat.setText(String.valueOf(rv.getZarfiat()));
        viewHolder.lessontime.setText(rv.getLessontime());
        viewHolder.examdate.setText(rv.getExamdate());
        viewHolder.teachername.setText(rv.getTechername());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView lessoncode , zarfiat , lessontime , examdate , teachername;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardview_entekhabdars_id);
            lessoncode = (TextView) itemView.findViewById(R.id.txt_lessonid_id);
            zarfiat = (TextView) itemView.findViewById(R.id.txt_capacity_id);
            lessontime = (TextView) itemView.findViewById(R.id.lessontime_id);
            examdate = (TextView) itemView.findViewById(R.id.examdate_id);
            teachername = (TextView) itemView.findViewById(R.id.teachername_id);
        }
    }
}
