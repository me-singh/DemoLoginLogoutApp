package com.example.kadyan.demologinlogoutapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity implements View.OnClickListener{

    Button register,login,forgetPassword;
    EditText userMobile,userPassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitActivity();

        forgetPassword.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    private void onInitActivity() {
        sharedPreferences=getSharedPreferences(RegisterScreenActivity.SHARED_PREFERNCES,MODE_PRIVATE);
        setContentView(R.layout.activity_login_screen);
        register=findViewById(R.id.btn_go_to_register);
        login=findViewById(R.id.btn_Login);
        forgetPassword=findViewById(R.id.btn_forgot_password);
        userMobile=findViewById(R.id.userMobile);
        userPassword=findViewById(R.id.userPassword);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_forgot_password: {
                startActivity(new Intent(LoginScreenActivity.this, ForgotPasswordActivity.class));
                break;
            }
            case R.id.btn_go_to_register: {
                startActivity(new Intent(LoginScreenActivity.this, RegisterScreenActivity.class));
                finish();
                break;
            }
            case R.id.btn_Login: {
                if (TextUtils.isEmpty(userMobile.getText().toString()) || userMobile.getText().length() < 10) {
                    Toast.makeText(LoginScreenActivity.this, "INVALID MOBILE", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userPassword.getText().toString())) {
                    Toast.makeText(LoginScreenActivity.this, "INVALID PASSWORD", Toast.LENGTH_SHORT).show();
                } else {
                    hitLoginRequest();
                }
                break;
            }
        }
    }

    private void hitLoginRequest() {
        //loading
        if (userMobile.getText().toString().equals(sharedPreferences.getString(RegisterScreenActivity.MOBILE, "123"))
                && userPassword.getText().toString().equals(sharedPreferences.getString(RegisterScreenActivity.PASSWORD, "PASS"))){
            Intent intent=new Intent(LoginScreenActivity.this, HomeScreen.class);
            intent.putExtra(RegisterScreenActivity.NAME,sharedPreferences.getString(RegisterScreenActivity.NAME,"name"));
            startActivity(intent);
            finish();
        }else
            Snackbar.make(findViewById(R.id.loginParentLayout),"INCORRECT CREDENTIALS",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
