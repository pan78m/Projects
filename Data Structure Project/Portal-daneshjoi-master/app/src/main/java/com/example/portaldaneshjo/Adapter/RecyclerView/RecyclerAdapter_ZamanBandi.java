package com.example.portaldaneshjo.Adapter.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.portaldaneshjo.Model.RecyclerItem_ZamanBandi;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class RecyclerAdapter_ZamanBandi extends RecyclerView.Adapter<RecyclerAdapter_ZamanBandi.viewHolder> {

    private Context context;
    private ArrayList<RecyclerItem_ZamanBandi> model;

    public RecyclerAdapter_ZamanBandi(Context context, ArrayList<RecyclerItem_ZamanBandi> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_zamanbandi_simple,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        RecyclerItem_ZamanBandi rz = model.get(i);
        viewHolder.txt_startdate.setText(rz.getStartdate());
        viewHolder.txt_enddate.setText(rz.getEnddate());
        viewHolder.txt_salvorod.setText(rz.getSalvorod());
        viewHolder.txt_time.setText(rz.getTime());
        viewHolder.txt_kontrolmoadel.setText(rz.getControlmoadel());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txt_startdate , txt_enddate , txt_salvorod , txt_time , txt_kontrolmoadel;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txt_startdate = (TextView) itemView.findViewById(R.id.txt_startdate_id);
            txt_enddate = (TextView) itemView.findViewById(R.id.txt_enddate_id);
            txt_salvorod = (TextView) itemView.findViewById(R.id.txt_salvorod_id);
            txt_time = (TextView) itemView.findViewById(R.id.txt_time_id);
            txt_kontrolmoadel = (TextView) itemView.findViewById(R.id.txt_kontrolmoadel_id);
        }
    }
}
