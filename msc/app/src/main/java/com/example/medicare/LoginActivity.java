package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText textUsername;
    EditText textPassword;
    Button buttonLogin;
    TextView textRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

//        db = new DatabaseHelper(this);
        textUsername = (EditText)findViewById(R.id.edittext_username);
        textPassword = (EditText)findViewById(R.id.edittext_password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        textRegister = (TextView) findViewById(R.id.textview_register);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(registerIntent);
                }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = textUsername.getText().toString().trim();
                String pwd = textPassword.getText().toString().trim();

                if(user=="santosh"&&pwd=="santosh")
                {
                    Toast.makeText(LoginActivity.this,"Successfully Logged In",Toast.LENGTH_SHORT);
                    Intent moveToMain = new Intent(LoginActivity.this,MainActivity.class);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT);
                }
            }

        });

    }
String user;
    String pwd;





    public void loginchecking(View view) {
        user = textUsername.getText().toString().trim();
        pwd = textPassword.getText().toString().trim();
         Boolean res = db.checkUser(user,pwd);
        //checkUserAndPassword(user,pwd);
        if(res)
        {
            Log.i("Info","Login Success");

            Toast.makeText(LoginActivity.this,"Successfully Logged In",Toast.LENGTH_SHORT).show();
            Intent moveToMain = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(moveToMain);
        }
        else
        {
            Log.i("Info","Login Error");
            Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean checkUserAndPassword(String user, String pwd) {
        if(user.equals("santosh")&&pwd.equals("santosh"))
            return true;
        return  false;
    }
}
