package com.example.portaldaneshjo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.portaldaneshjo.Activity.Omor_amozeshi_activities.ZamanBandi;
import com.example.portaldaneshjo.Activity.Omor_mali_activities.Pardakht_electronic;
import com.example.portaldaneshjo.Activity.Entekhab_Vahed.Entekhab_vahed;
import com.example.portaldaneshjo.R;

public class EntekhabVahed extends Fragment {

    private Button btn_zamanbandi , btn_pardakht , btn_enter_entekhabvahed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_entekhab_vahed,container,false);

        btn_enter_entekhabvahed = (Button) view.findViewById(R.id.btn_enter_to_entekhabvahed_id);
        btn_pardakht = (Button) view.findViewById(R.id.btn_pardakht_link_id);
        btn_zamanbandi = (Button) view.findViewById(R.id.btn_zamanbandi_link_id);

        btn_pardakht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Pardakht_electronic.class));
            }
        });

        btn_zamanbandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ZamanBandi.class));
            }
        });

        btn_enter_entekhabvahed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Entekhab_vahed.class));
            }
        });

        return view;
    }
}