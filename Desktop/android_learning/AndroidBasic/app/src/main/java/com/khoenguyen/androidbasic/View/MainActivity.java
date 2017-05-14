package com.khoenguyen.androidbasic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khoenguyen.androidbasic.Presenter.LogIn.PresenterLogic;
import com.khoenguyen.androidbasic.R;
import com.khoenguyen.androidbasic.View.LogIn.ViewLogIn;

public class MainActivity extends AppCompatActivity implements ViewLogIn, View.OnClickListener{
    private PresenterLogic presenterLogic;
    public EditText edName, edPass;
    public Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //chay logo tren action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //gan id tuong ung va khoi tao
        connectView();

        //bat su kien khi click Login
        btnLogin.setOnClickListener(this);

    }

    public void connectView(){
        edName = (EditText) findViewById(R.id.edName);
        edPass = (EditText) findViewById(R.id.edPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        presenterLogic = new PresenterLogic(this);

    }

    @Override
    public void LoginSuccess() {
        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        //Chuyen sang activity moi
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);

    }

    @Override
    public void LoginFailed() {
        Toast.makeText(MainActivity.this, "Again.", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "User: android", Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, "pass: basic", Toast.LENGTH_LONG).show();
        //Nhap Lai
    }

    @Override
    public void onClick(View v) {
        String name = edName.getText().toString();
        String pass = edPass.getText().toString();
        presenterLogic.KiemTraDangNhap(name, pass);
    }
}
