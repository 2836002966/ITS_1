package com.example.administrator.its.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.its.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name,pwd1,pwd2,sj;
    private Button qx,zc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initViews();
    }
    private void initViews(){
        name = findViewById(R.id.et_username2);
        pwd1 = findViewById(R.id.et_password1);
        pwd2 = findViewById(R.id.et_password2);
        sj = findViewById(R.id.et_shouji);
        qx = findViewById(R.id.bt_qx);
        zc = findViewById(R.id.bt_register2);
        qx.setOnClickListener(this);
        zc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_qx:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_register2:
                String a = "";
                if(a.equals(name.getText().toString())){
                    Toast.makeText(this,"用户名为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                else if (a.equals(pwd1.getText().toString())){
                    Toast.makeText(this,"密码为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if (a.equals(pwd2.getText().toString())){
                    Toast.makeText(this,"重复密码为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(!pwd1.getText().toString().equals(pwd2.getText().toString())){
                    Toast.makeText(this,"两次密码不同",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(a.equals(sj.getText().toString())){
                    Toast.makeText(this,"手机号码为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
                break;
            case R.id.imageview_002:
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                final View contentView = View.inflate(this, R.layout.logindialog, null);
                builder.setView(contentView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
    }
}
