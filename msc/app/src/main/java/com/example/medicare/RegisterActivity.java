package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.data.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText textUsername;
    EditText textPassword;
    EditText textCnfPassword;
    Button buttonRegister;
    TextView textLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        db = new DatabaseHelper(this);
        textUsername = (EditText)findViewById(R.id.edittext_username);
        textPassword = (EditText)findViewById(R.id.edittext_password);
        textCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        buttonRegister = (Button) findViewById(R.id.button_register);
        textLogin = (TextView) findViewById(R.id.textview_login);
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = textUsername.getText().toString().trim();
                String pwd = textPassword.getText().toString().trim();
                String cnf_pwd = textCnfPassword.getText().toString().trim();

                if(pwd.equals(cnf_pwd))
                {

                        Toast.makeText(RegisterActivity.this,"You have Registered Successfully",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                        db.addUser(user,pwd);
                        startActivity(moveToLogin);

                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}