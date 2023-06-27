package com.example.portaldaneshjo.Adapter.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.portaldaneshjo.Model.ListItem_VaziateMali;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class ListViewAdapter_VaziateMali extends ArrayAdapter<ListItem_VaziateMali> {

    private Context context;
    private ArrayList<ListItem_VaziateMali> listVaziat;

    public ListViewAdapter_VaziateMali(Context context, ArrayList<ListItem_VaziateMali> listVaziat) {
        super(context, 0,listVaziat);
        this.context = context;
        this.listVaziat = listVaziat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem_VaziateMali ls = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_vaziat,parent,false);
        }

        TextView txt_itemmali = (TextView) convertView.findViewById(R.id.txt_itemmali_id);
        TextView txt_shomarefish = (TextView) convertView.findViewById(R.id.txt_shomarefish_id);
        TextView txt_tarikhfish = (TextView) convertView.findViewById(R.id.txt_tarikhfish_id);
        TextView txt_shhesabjari = (TextView) convertView.findViewById(R.id.txt_hesabejari_id);
        TextView txt_bedehkar = (TextView) convertView.findViewById(R.id.txt_bedehkar_id);
        TextView txt_bestankar = (TextView) convertView.findViewById(R.id.txt_bestankar_id);

        assert ls != null;
        txt_itemmali.setText(ls.getItemmali());
        txt_shomarefish.setText(String.valueOf(ls.getShomarefish()));
        txt_tarikhfish.setText(ls.getTarikhfish());
        txt_shhesabjari.setText(ls.getShomarehesabejari());
        txt_bedehkar.setText(String.valueOf(ls.getBedehkar()));
        txt_bestankar.setText(String.valueOf(ls.getBestankar()));

        return convertView;
    }
}
