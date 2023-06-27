package com.example.portaldaneshjo.Activity.Other_Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.portaldaneshjo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private TextView txt_stu_num,txt_stu_pass,txt_portal;
    private EditText stdCode , stdPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        stdCode = (EditText) findViewById(R.id.txt_stdcode_id);
        stdPass = (EditText) findViewById(R.id.txt_stdpass_id);
        txt_stu_num = (TextView) findViewById(R.id.txt_studenum_id);
        txt_stu_pass = (TextView) findViewById(R.id.txt_stu_pass_id);
        txt_portal = (TextView) findViewById(R.id.portal_id);
        btn_login = (Button) findViewById(R.id.btn_sign_in_id);
        btn_login = (Button) findViewById(R.id.btn_sign_in_id);

        Typeface txtFont = Typeface.createFromAsset(getAssets(),"fonts/IRANSans.ttf");
        txt_stu_num.setTypeface(txtFont);
        txt_stu_pass.setTypeface(txtFont);
        txt_portal.setTypeface(txtFont);
        btn_login.setTypeface(txtFont);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginStudent(v);
            }
        });
    }

    private void LoginStudent(final View view) {
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String URL = "http://se7enf98.ddns.net/webservice/p/StudentLogin.php";
        Hashtable<String , String > params = new Hashtable<>();
        params.put("StudentCode" , stdCode.getText().toString());
        params.put("Password" , stdPass.getText().toString());

        final ProgressDialog dialog ;
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("لطفا صبر کنید ..");
        dialog.setCancelable(false);
        dialog.show();


        JSONObject object = new JSONObject(params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String success = response.getString("error");
                    String fullname = response.getString("FullName");
                    String stdCode = response.getString("StudentCode");
                    String ProfilePic = response.getString("ProfilePic");
                    String nationalnumber = response.getString("NationalCode");

                    String preimg = "http://se7enf98.ddns.net/webservice/p/";
                    String posimg = preimg.concat(ProfilePic);

                    SharedPreferences.Editor editor = getSharedPreferences("login" , Context.MODE_PRIVATE).edit();
                    editor.putString("FullName" , fullname);
                    editor.putString("StudentCode" , stdCode);
                    editor.putString("ProfilePic" , posimg);
                    editor.putString("NationalCode" , nationalnumber);
                    editor.apply();

                    dialog.dismiss();
                    if (success.equals("0")){
                        startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this, "شماره دانشجویی یا رمز عبور شما اشتباه است !", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}
