package com.example.portaldaneshjo.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.GridView;

import com.example.portaldaneshjo.Adapter.FragmentsGridViewAdapters.GridViewAdapter_Mali;
import com.example.portaldaneshjo.R;
import com.labo.kaji.fragmentanimations.CubeAnimation;

public class OmorMalli extends Fragment {

    private GridView gridView;
    private String[] nameitems = {"پرداخت الکترونیک","وضعیت مالی","محاسبه شهریه","پرداخت ناموفق"};
    private Integer[] picitems = {R.drawable.ic_pardakht_e,R.drawable.ic_vazeiate_mali,R.drawable.ic_mohasebeh_shahrieh,
            R.drawable.ic_payment_error};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_omor_malli,container,false);
        gridView = (GridView) view.findViewById(R.id.grid_mali_id);

        gridView.setAdapter(new GridViewAdapter_Mali(getActivity(),nameitems,picitems));

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return CubeAnimation.create(CubeAnimation.DOWN, enter, 2);
        } else {
            return CubeAnimation.create(CubeAnimation.UP, enter, 2);
        }
    }
}
