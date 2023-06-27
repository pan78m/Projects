package com.example.portaldaneshjo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.portaldaneshjo.Model.Nimsaltahsili;
import com.example.portaldaneshjo.R;

import java.util.ArrayList;

public class SpinnerAdapter_Nimsal extends ArrayAdapter<Nimsaltahsili> {

    private static final int CLOSE = 0;
    private static final int OPEN = 1;
    private View mView;
    private LayoutInflater layoutInflater;

    public SpinnerAdapter_Nimsal(FragmentActivity context, ArrayList<Nimsaltahsili> arrayList){
        super(context, R.layout.spinner_nimsaltahsili_open,arrayList);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,parent,CLOSE);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,parent,OPEN);
    }

    public View getCustomView(int position, ViewGroup viewGroup, int type){
        if (type == OPEN){
            mView = layoutInflater.inflate(R.layout.spinner_nimsaltahsili_open,viewGroup,false);
        }else if (type == CLOSE){
            mView = layoutInflater.inflate(R.layout.spinner_nimsaltahsili_close,viewGroup,false);
        }
        TextView txt_nimsal_open = mView.findViewById(R.id.txt_nimsaltahsili_id);
        txt_nimsal_open.setText(getItem(position).getNimsal());
        return mView;
    }
}
