package com.example.portaldaneshjo.Adapter.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.portaldaneshjo.Model.ListItem_PardakhtNamovafagh;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class ListViewAdapter_pardakhtnamovafagh extends ArrayAdapter<ListItem_PardakhtNamovafagh> {

    private Context context;
    private ArrayList<ListItem_PardakhtNamovafagh> listItemPardakhtNamovafagh;

    public ListViewAdapter_pardakhtnamovafagh(Context context, ArrayList<ListItem_PardakhtNamovafagh> listItemPardakhtNamovafagh) {
        super(context, 0, listItemPardakhtNamovafagh);
        this.context = context;
        this.listItemPardakhtNamovafagh = listItemPardakhtNamovafagh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem_PardakhtNamovafagh lp = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_namovafagh,parent,false);
        }

        TextView txt_shomaresefaresh = (TextView) convertView.findViewById(R.id.txt_shomaresefaresh_id);
        TextView txt_shenase = (TextView) convertView.findViewById(R.id.txt_shenase_id);
        TextView txt_mablaghvarizi = (TextView) convertView.findViewById(R.id.txt_mablaghevariz_id);
        TextView txt_tarikhvariz = (TextView) convertView.findViewById(R.id.txt_tarikhevariz_id);
        TextView txt_satvariz = (TextView) convertView.findViewById(R.id.txt_saatevariz_id);

        assert lp != null;
        txt_shomaresefaresh.setText(String.valueOf(lp.getShomaresefaresh()));
        txt_shenase.setText(String.valueOf(lp.getShenase()));
        txt_mablaghvarizi.setText(String.valueOf(lp.getMablaghevarizi()));
        txt_tarikhvariz.setText(lp.getTarikhvariz());
        txt_satvariz.setText(lp.getSaatvariz());

        return convertView;
    }
}
