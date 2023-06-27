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
import com.example.portaldaneshjo.Adapter.FragmentsGridViewAdapters.GridViewAdapter_Amozeshi;
import com.example.portaldaneshjo.R;
import com.labo.kaji.fragmentanimations.CubeAnimation;

public class OmorAmozeshi extends Fragment {

    private GridView gridView;
    private String[] nameitems = {"برنامه هفتگی","اعتراض نمرات","کارنامه","لیست دروس",
            "زمان بندی ثبت نام","کارت امتحان"};
    private Integer[] picitems = {R.drawable.ic_barname_haftegi_24dp,R.drawable.ic_eteraz_24dp
            ,R.drawable.ic_karnameh,R.drawable.ic_list_doros_24dp,R.drawable.ic_zaman_bandi_24dp
            ,R.drawable.ic_examcard};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_omor_amozeshi,container,false);
        gridView = (GridView) view.findViewById(R.id.grid_amozeshi_id);

        gridView.setAdapter(new GridViewAdapter_Amozeshi(getActivity(),nameitems,picitems));

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
