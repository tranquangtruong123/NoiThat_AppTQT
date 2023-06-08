package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Admin.QLAdmin;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.HienThiAcTy.Them_Danh_Sach_YT;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.HienThiAcTy.Them_Hien_Thi;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Login;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Register;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.SplashActivity;
import com.example.do_an_ket_thuc_hoc_phan.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class ThemFragment extends Fragment implements ItemClickView {
    private TextView txt_thoat;
    private NavigationView navigationView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_them, container, false);
        anhxa();
        txt_thoat.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity().getApplication(), Login.class);
            startActivity(intent);
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.menu_tttk){
                    Intent intent = new Intent(getActivity().getApplication(), Them_Hien_Thi.class);
                    startActivity(intent);
                }
                if(id == R.id.menu_dsyt){
                    Intent intent = new Intent(getActivity().getApplication(), Them_Danh_Sach_YT.class);
                    startActivity(intent);
                }
                if(id == R.id.menu_adim){
                    opendialog();

                }
                return true;
            }


        });

        return view;
    }

    private void opendialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog);
        Window window = dialog.getWindow();
        if(window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setCancelable(false);
        AppCompatButton bnt_dn = dialog.findViewById(R.id.bnt_dn);
        AppCompatButton bnt_can = dialog.findViewById(R.id.bnt_cancel);
        TextInputLayout edt_user = dialog.findViewById(R.id.edt_dn2);
        TextInputLayout edt_pass = dialog.findViewById(R.id.edt_password2);
        dialog.show();
        bnt_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        bnt_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_user.getEditText().getText().toString().trim().equals("admin") && edt_pass.getEditText().getText().toString().trim().equals("123456")){
                    Intent intent = new Intent(getActivity().getApplication(), QLAdmin.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(), "Sai Thong Tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void anhxa() {
        txt_thoat = view.findViewById(R.id.txt_thoat);
        navigationView = view.findViewById(R.id.navigation);
    }

    @Override
    public void onclick(int posi) {

    }


}