package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout edt_dangnhap,edt_mk;
    private ImageView img_facebook,img_google,img_twitter;
    private AppCompatButton bnt_skip,bnt_login,bnt_signup;
    private TextView txtpassword;
    private CheckBox checkbook;
    SharedPreferences sharedPreferences;
    CreateDatabase database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        database = new CreateDatabase(this);
        sharedPreferences = getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        edt_dangnhap.getEditText().setText(sharedPreferences.getString("user",""));
        edt_mk.getEditText().setText(sharedPreferences.getString("password",""));
        checkbook.setChecked(sharedPreferences.getBoolean("check",false));

        bnt_skip.setOnClickListener(this);
        bnt_signup.setOnClickListener(this);
        bnt_login.setOnClickListener(this);
    }

    private void anhxa() {
        edt_dangnhap = (TextInputLayout)findViewById(R.id.edt_dn);
        edt_mk = (TextInputLayout)findViewById(R.id.edt_password);
        img_facebook = findViewById(R.id.bnt_fa);
        img_google = findViewById(R.id.bnt_go);
        img_twitter = findViewById(R.id.bnt_ti);
        bnt_skip = findViewById(R.id.bnt_skip);
        bnt_login = findViewById(R.id.bnt_login);
        bnt_signup =  findViewById(R.id.bnt_sinup);
        txtpassword = findViewById(R.id.txt_forget);
        checkbook = findViewById(R.id.checkbox);
    }
    @Override
    public void onClick(View view) {
        if(view == bnt_login){
            if(!checkDN() | !checkMK()){
                return;
            }
            String s1 = edt_dangnhap.getEditText().getText().toString();
            String s2 = edt_mk.getEditText().getText().toString();
            int ktra = database.KiemTraDN(s1,s2);
            if(s1.equals("123456") && s2.equals("123456")){
                if (checkbook.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", s1);
                    editor.putString("password", s2);
                    editor.putBoolean("check", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", "");
                    editor.putString("password", "");
                    editor.putBoolean("check", false);
                    editor.commit();
                }
                startActivity(new Intent(Login.this, MainActivity.class));
                return;
            }
             else if (ktra != 0 ) {
                    if (checkbook.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user", s1);
                        editor.putString("password", s2);
                        editor.putBoolean("check", true);
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user", "");
                        editor.putString("password", "");
                        editor.putBoolean("check", false);
                        editor.commit();
                    }
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Sai Thong Tin DN", Toast.LENGTH_SHORT).show();
                }
            }
        if(view == bnt_skip){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
        if(view == bnt_signup){
            Intent intent = new Intent(Login.this,Register.class);
            startActivity(intent);
        }

    }

    private boolean checkDN() {
        String var = edt_dangnhap.getEditText().getText().toString().trim();
        if(var.isEmpty()){
            edt_dangnhap.setError(getResources().getString(R.string.dntrong));
            return false;
        }
        else{
            edt_dangnhap.setError(null);
            edt_dangnhap.setErrorEnabled(false);
            return true;
        }
    }
    private boolean checkMK() {
        String var = edt_mk.getEditText().getText().toString().trim();
        if(var.isEmpty()){
            edt_mk.setError(getResources().getString(R.string.dntrong));
            return false;
        }
        else{
            edt_mk.setError(null);
            edt_mk.setErrorEnabled(false);
            return true;
        }
    }
}