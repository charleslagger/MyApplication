package com.khoenguyen.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khoenguyen.myapplication.XuLyDangNhap.M.Model;
import com.khoenguyen.myapplication.XuLyDangNhap.P.PresenterLogic;
import com.khoenguyen.myapplication.XuLyDangNhap.V.ViewXL;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements ViewXL, android.view.View.OnClickListener {
    PresenterLogic presenterLogic;
    Model model;
    EditText edName, edPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = (EditText) findViewById(R.id.edName);
        edPass = (EditText) findViewById(R.id.edPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        presenterLogic = new PresenterLogic(this);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void DangNhapThanhCong() {
        Toast.makeText(MainActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangNhapThatBai() {
        Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(android.view.View v) {
        String name = edName.getText().toString();
        String pass = edPass.getText().toString();
        presenterLogic.KiemTraDangNhap(name, pass);
    }
}
