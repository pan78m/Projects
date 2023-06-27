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

import com.example.portaldaneshjo.Adapter.FragmentsGridViewAdapters.GridViewAdapter_Darkhast;
import com.example.portaldaneshjo.R;
import com.labo.kaji.fragmentanimations.CubeAnimation;

public class DarkhasteDaneshjoii extends Fragment {

    private GridView gridView;
    private final String[] nameitems = {"خوابگاه","پارکینگ","رزرو غذا","درخواست وام","گواهی اشتغال","مرخصی تحصیلی"};
    private final Integer[] picitems = {R.drawable.ic_khabgah_24dp,R.drawable.ic_parking,R.drawable.ic_ghaza_24dp,
            R.drawable.ic_vam,R.drawable.ic_eshteqal,R.drawable.ic_morakhasi_24dp};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_darkhaste_daneshjoii,container,false);
        gridView = (GridView) view.findViewById(R.id.grid_darkhast_id);

        gridView.setAdapter(new GridViewAdapter_Darkhast(getActivity(),nameitems,picitems));

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
