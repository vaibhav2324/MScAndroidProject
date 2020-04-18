package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Registration;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText textUsername;
    EditText textPassword;
    Button cirLoginButton;
    TextView textRegister;


    MedicareAppDatabase medicareAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        medicareAppDatabase = Room.databaseBuilder(getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();
        textUsername = (EditText)findViewById(R.id.edittext_username);
        textPassword = (EditText)findViewById(R.id.edittext_password);
        cirLoginButton = (Button) findViewById(R.id.button_login);
        textRegister = (TextView) findViewById(R.id.textview_register);


        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });

        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = textPassword.getText().toString().trim();
                final String username = textUsername.getText().toString().trim();
                List<Registration> retrievedList = medicareAppDatabase.getRegistrationRepo().getRegistrationsByEmailAndPassword(username,password);
                if(retrievedList.size()>0)
                {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent moveToDashboard = new Intent(LoginActivity.this,DashboardUIActivity.class);
                    startActivity(moveToDashboard);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    Intent moveToRegistration = new Intent(LoginActivity.this,RegistrationActivity.class);
                    startActivity(moveToRegistration);
                }
            }
        });
    }




}

