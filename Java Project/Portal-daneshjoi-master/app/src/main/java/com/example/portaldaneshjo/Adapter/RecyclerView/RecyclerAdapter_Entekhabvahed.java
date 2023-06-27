package com.example.portaldaneshjo.Adapter.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.portaldaneshjo.Activity.Entekhab_Vahed.Dars;
import com.example.portaldaneshjo.Model.RecyclerItem_Entekhabvahed;
import com.example.portaldaneshjo.R;
import java.util.List;

public class RecyclerAdapter_Entekhabvahed extends RecyclerView.Adapter<RecyclerAdapter_Entekhabvahed.viewHolder> {

     Context context;
     List<RecyclerItem_Entekhabvahed> model;

    public RecyclerAdapter_Entekhabvahed(Context context, List<RecyclerItem_Entekhabvahed> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_entekhabvahed_simple,viewGroup , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {
        final RecyclerItem_Entekhabvahed rv = model.get(i);
        viewHolder.vahed.setText(String.valueOf(rv.getVahed()));
        viewHolder.name.setText(rv.getName());
        viewHolder.name.setTextColor(Color.BLACK);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Dars.class);
                intent.putExtra("namedars",rv.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name , vahed ;
        CardView cardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview_entekhabvahed_id);
            vahed = (TextView) itemView.findViewById(R.id.vahed_id);
            name = (TextView) itemView.findViewById(R.id.name_id);
        }
    }
}
