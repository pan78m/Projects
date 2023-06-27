package com.example.portaldaneshjo.Activity.Other_Activitys;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.portaldaneshjo.Fragments.Entekhabvahed.Sabt_list;
import com.example.portaldaneshjo.Fragments.Entekhabvahed.Sabt_moshakhase;
import com.example.portaldaneshjo.Fragments.Entekhabvahed.Moshahede_entekhabvahed;
import com.example.portaldaneshjo.R;
import com.example.portaldaneshjo.Util;

public class Entekhab_vahed extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    Sabt_moshakhase sabt_moshakhase = new Sabt_moshakhase();
    Sabt_list sabt_list = new Sabt_list();
    Moshahede_entekhabvahed moshahede_entekhabvahed = new Moshahede_entekhabvahed();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entekhab_vahed);

        viewPager = (ViewPager) findViewById(R.id.viewpager_entekhabvahed_id);
        tabLayout = (TabLayout) findViewById(R.id.tabs_entekhabvahed_id);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setTabsIcon();
    }

    private void setTabsIcon() {
//محمد امین خادمی علی رشادتی مبین عزتی
        int[] icons = {R.drawable.ic_moshakhase_entekhabvahed_24dp,R.drawable.ic_list_entekhabvahed_24dp,R.drawable.ic_moshahede_entekhabvahed_24dp};
        int selectedColor = Color.parseColor("#C70000");
        int unSelectedColor = Color.parseColor("#ffffff");
        Util.setupTabIcons(getApplicationContext(),tabLayout,icons,1,selectedColor,unSelectedColor);
    }

    private void setUpViewPager(ViewPager viewPager) {
        Util.ViewPagerAdapter adapter = new Util.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(sabt_moshakhase,"ثبت با مشخۀ");
        adapter.addFragment(sabt_list,"ثبت از لیست");
        adapter.addFragment(moshahede_entekhabvahed,"مشاهده انتخاب واحد");
        viewPager.setAdapter(adapter);
    }
}
