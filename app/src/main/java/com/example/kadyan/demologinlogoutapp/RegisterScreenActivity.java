package com.example.kadyan.demologinlogoutapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreenActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SHARED_PREFERNCES="MYPREFERENCES";
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String MOBILE="mobile";
    public static final String PASSWORD="password";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText name,email,mobile,password,rePassword;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitActivity();

        register.setOnClickListener(this);
    }

    private void onInitActivity() {
        sharedPreferences=getSharedPreferences(SHARED_PREFERNCES, MODE_PRIVATE);
        setContentView(R.layout.activity_register_screen);
        name=findViewById(R.id.userName);
        email=findViewById(R.id.userEmail);
        mobile=findViewById(R.id.userMobile);
        password=findViewById(R.id.userPassword);
        rePassword=findViewById(R.id.userReEnterPassword);
        register=findViewById(R.id.btn_register);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_register){
            if (TextUtils.isEmpty(name.getText().toString())){
                Toast.makeText(getBaseContext(),"INVALID NAME",Toast.LENGTH_SHORT).show();
            }else if (!TextUtils.isEmpty(email.getText().toString()) &&
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() ) {
                Toast.makeText(getBaseContext(),"INVALID EMAIL",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(mobile.getText().toString()) || mobile.getText().length() < 10 ) {
                Toast.makeText(getBaseContext(),"INVALID PHONE",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(password.getText().toString()) ) {
                Toast.makeText(getBaseContext(),"INVALID PASSWORD",Toast.LENGTH_SHORT).show();
            }else if (!password.getText().toString().equals(rePassword.getText().toString())) {
                Toast.makeText(getBaseContext(),"PASSWORDS DO NOT MATCH",Toast.LENGTH_SHORT).show();
            }else {
                hitRegisterRequest();
            }
        }
    }

    private void hitRegisterRequest() {
        editor=sharedPreferences.edit();
        editor.putString(NAME,name.getText().toString());
        editor.putString(EMAIL,email.getText().toString());
        editor.putString(MOBILE,mobile.getText().toString());
        editor.putString(PASSWORD,password.getText().toString());
        editor.apply();
        startActivity(new Intent(RegisterScreenActivity.this,LoginScreenActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
