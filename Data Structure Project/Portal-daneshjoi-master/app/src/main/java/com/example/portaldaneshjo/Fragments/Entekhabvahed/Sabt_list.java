package com.example.portaldaneshjo.Fragments.Entekhabvahed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.portaldaneshjo.Adapter.RecyclerView.RecyclerAdapter_Entekhabvahed;
import com.example.portaldaneshjo.Model.RecyclerItem_Entekhabvahed;
import com.example.portaldaneshjo.R;
import java.util.ArrayList;

public class Sabt_list extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<RecyclerItem_Entekhabvahed> items = new ArrayList<>();
    private RecyclerAdapter_Entekhabvahed adapter;

    public Sabt_list() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sabt_list,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_entekhabvahed_id);

        adapter = new RecyclerAdapter_Entekhabvahed(getContext(),items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        showdata();

        return view;
    }

    public void showdata(){

        items.add(new RecyclerItem_Entekhabvahed("ریاضی مهندسی",3));
        items.add(new RecyclerItem_Entekhabvahed("انقلاب اسلامی",2));
        items.add(new RecyclerItem_Entekhabvahed("معماری کامپیوتر",3));
        items.add(new RecyclerItem_Entekhabvahed("فیزیک 1",3));
        items.add(new RecyclerItem_Entekhabvahed("مدار الکتریکی",3));
        items.add(new RecyclerItem_Entekhabvahed("ریاضیات گسسته",3));
        items.add(new RecyclerItem_Entekhabvahed("ریاضی 1",3));
        items.add(new RecyclerItem_Entekhabvahed("ریاضی 2",3));
        items.add(new RecyclerItem_Entekhabvahed("آمار و احتمال مهندسی",3));
        items.add(new RecyclerItem_Entekhabvahed("زبان تخصصی",2));
        items.add(new RecyclerItem_Entekhabvahed("وضیت حضرت امام",1));
        items.add(new RecyclerItem_Entekhabvahed("اندیشه 1",1));
        items.add(new RecyclerItem_Entekhabvahed("آزمایشگاه مدار الکتریکی",2));
        items.add(new RecyclerItem_Entekhabvahed("آزمایشگاه فیزیک",2));
        items.add(new RecyclerItem_Entekhabvahed("معادلات دیفرانسیل",3));
        items.add(new RecyclerItem_Entekhabvahed("پایگاه داده",3));
        items.add(new RecyclerItem_Entekhabvahed("گرافیک کامپیوتری",3));
        items.add(new RecyclerItem_Entekhabvahed("هوش مصنوعی",3));
        items.add(new RecyclerItem_Entekhabvahed("ساختمان داده",3));
        items.add(new RecyclerItem_Entekhabvahed("ادبیات فارسی",3));
        items.add(new RecyclerItem_Entekhabvahed("برنامه نویسی پیشرفته",3));
        items.add(new RecyclerItem_Entekhabvahed("برنامه نویسی پیشرفته",3));
        items.add(new RecyclerItem_Entekhabvahed("برنامه نویسی پیشرفته",3));
        items.add(new RecyclerItem_Entekhabvahed("برنامه نویسی پیشرفته",3));
        items.add(new RecyclerItem_Entekhabvahed("برنامه نویسی پیشرفته",3));
        items.add(new RecyclerItem_Entekhabvahed("برنامه نویسی پیشرفته",3));
        items.add(new RecyclerItem_Entekhabvahed("برنامه نویسی پیشرفته",3));
    }
}
