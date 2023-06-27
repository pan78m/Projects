package com.example.portaldaneshjo.Activity.Other_Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.portaldaneshjo.Fragments.DarkhasteDaneshjoii;
import com.example.portaldaneshjo.Fragments.EntekhabVahed;
import com.example.portaldaneshjo.Fragments.OmorAmozeshi;
import com.example.portaldaneshjo.Fragments.OmorMalli;
import com.example.portaldaneshjo.R;
import com.example.portaldaneshjo.Util;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    //Components
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imgProfile;
    TextView txtName , txtCode;

    //Strings
    public static String name;
    public static String code;
    public static String nationalcode;

     Boolean showONCE = true;

    //Fragments
    OmorMalli malli = new OmorMalli();
    OmorAmozeshi amozeshi = new OmorAmozeshi();
    DarkhasteDaneshjoii darkhast = new DarkhasteDaneshjoii();
    EntekhabVahed vahed = new EntekhabVahed();

    @Override
    protected void onResume() {
        super.onResume();

        //Navigation Listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.person_info_id: {
                        Toast.makeText(MainActivity.this, "اطلاعات شخصی", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.mail_id: {
                        Toast.makeText(MainActivity.this, "پیام ها", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.parvande_id:{
                        Toast.makeText(MainActivity.this, "پرونده دیجیتال", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.kholase_vaze_tahsili_id:{
                        startActivity(new Intent(getApplicationContext(),Kholase_tahsili.class));
                        drawerLayout.closeDrawers();break;

                    }
                    case R.id.map_id:{
                        Toast.makeText(MainActivity.this, "نقشه", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.phone_id:{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCustomTitle(getLayoutInflater().inflate(R.layout.title_view_submitmobile , null));
                        final View customLayout_mobile = getLayoutInflater().inflate(R.layout.alert_changemobile_view,null);
                        builder.setView(customLayout_mobile);
                        final EditText newMob = (EditText) customLayout_mobile.findViewById(R.id.mobile_jadid_idnew);
                        builder.setPositiveButton("ثبت ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mobileSubmitPost(nationalcode , newMob);
                            }
                        });
                        builder.setCancelable(false);
                        builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialogMobile = builder.create();
                        dialogMobile.show();
                        drawerLayout.closeDrawers();break;
                    }
                    case R.id.exit_id:{
                        onDestroy();break;
                    }
                }
                return true;
            }
        });

        //Navigation Toggle
        final ActionBar actionBar = getSupportActionBar();
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open_navigation,R.string.close_navigation);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (showONCE){
            Toast.makeText(this, name + " خوش آمدید !", Toast.LENGTH_SHORT).show();
            showONCE = false;
        }else {
            //
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_id);

        //Navigation View
        View ss = navigationView.getHeaderView(0);
        txtName = (TextView) ss.findViewById(R.id.user_name_id);
        txtCode = (TextView) ss.findViewById(R.id.student_num_id);
        imgProfile = (ImageView) ss.findViewById(R.id.profile_id);

        //SharedPreferences
        SharedPreferences saver = this.getSharedPreferences("login" , Context.MODE_PRIVATE);
        name = saver.getString("FullName" , null);
        txtName.setText(name);
        code = saver.getString("StudentCode" , null);
        txtCode.setText(code);
        nationalcode = saver.getString("NationalCode" , null);
        String image = saver.getString("ProfilePic" , null);
        Picasso.get().load(image).into(imgProfile);

        //Toolbar
        toolbar.setTitle("تهران شمال");
        setSupportActionBar(toolbar);

        //Tab layout
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setTabIcons();
    }

    //Mobile Change Number Method
    private void mobileSubmitPost(String national , final EditText jadid_mobile) {
        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String URL = "http://se7enf98.ddns.net/webservice/p/ChangeMobile.php";

        final ProgressDialog dialog ;
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("لطفا صبر کنید ..");
        dialog.setCancelable(false);
        dialog.show();

        Hashtable<String , String> params = new Hashtable<>();
        params.put("NationalCode" , national);
        params.put("PhoneNumber" , jadid_mobile.getText().toString());

        JSONObject object = new JSONObject(params);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String result = response.getString("status");
                    if (result.equals("successful")){
                        Toast.makeText(MainActivity.this, jadid_mobile.getText() + " با موفقیت ثبت شد !", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "خطا", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        queue.add(request);
    }

    //Create Menu Method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    //Menu Listener Method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.change_pass_id:{
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCustomTitle(getLayoutInflater().inflate(R.layout.title_view_changepassword , null));
                final View customView_ChangePass = getLayoutInflater().inflate(R.layout.alert_changepassword_view,null);
                builder.setView(customView_ChangePass);
                final EditText nowpass = (EditText) customView_ChangePass.findViewById(R.id.nowpass_idnew);
                final EditText newpass = (EditText) customView_ChangePass.findViewById(R.id.newpass_idnew);
                final EditText acceptnewpass = (EditText) customView_ChangePass.findViewById(R.id.acceptnewpass_idnew);
                builder.setPositiveButton("تغییر رمز عبور", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (newpass.getText().toString().equals(acceptnewpass.getText().toString()) && nowpass.getText().length()>=0){
                            changePassPost(nowpass , newpass);

                        }else {
                            Toast.makeText(MainActivity.this, "تکرار رمز عبور جدید را اشتباه وارد کردید !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setCancelable(false);
                builder.setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialogPass = builder.create();
                dialogPass.show();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    //Change password method
    private void changePassPost(final EditText nowpass , final EditText newpass) {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String URL = "http://se7enf98.ddns.net/webservice/p/ChangeStudentPassword.php";

        final ProgressDialog dialog ;
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("لطفا صبر کنید ..");
        dialog.setCancelable(false);
        dialog.show();

        Hashtable<String , String> params = new Hashtable<>();
        params.put("StudentCode" , code);
        params.put("OldPassword" , nowpass.getText().toString());
        params.put("NewPassword" , newpass.getText().toString());

        JSONObject object = new JSONObject(params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String result = response.getString("status");

                    if (result.equals("Success")){
                        Toast.makeText(MainActivity.this, "رمز عبور با موفقیت تغیرر یافت !", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "خطا !!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "رمز عبور فعلی را اشتباه وارد کردید", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        queue.add(request);
    }

    //Set Tab Icons Method
    private void setTabIcons() {
        int[] ids = {R.drawable.mali,
                R.drawable.ic_amozeshi_24dp,
                R.drawable.ic_darkhast_24dp,R.drawable.ic_vahed_24dp};
        int selectedColor = Color.parseColor("#C70000");
        int unSelectedColor = Color.parseColor("#ffffff");
        Util.setupTabIcons(getApplicationContext(),tabLayout,ids,1,selectedColor,unSelectedColor);
    }

    //Create Tab Layout Method
    private void setUpViewPager(ViewPager viewPager) {
        Util.ViewPagerAdapter adapter = new Util.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(malli, "امور مالی");
        adapter.addFragment(amozeshi, "امور آموزشی");
        adapter.addFragment(darkhast, "درخواست");
        adapter.addFragment(vahed, "انتخاب واحد");
        viewPager.setAdapter(adapter);
    }
}