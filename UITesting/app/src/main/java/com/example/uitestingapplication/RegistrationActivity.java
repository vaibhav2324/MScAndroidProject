package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uitestingapplication.db.MedicareAppDatabase;

public class Registration extends AppCompatActivity {

    EditText textUsername;
    EditText textPassword;
    Button buttonRegister;
    TextView textLogin;
MedicareAppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db= Room.databaseBuilder(getApplicationContext(),MedicareAppDatabase.class,"medicareDB").build();
        textUsername = (EditText)findViewById(R.id.edittext_username);
        textPassword = (EditText)findViewById(R.id.edittext_password);
        buttonRegister = (Button) findViewById(R.id.button_register);
        textLogin = (TextView) findViewById(R.id.textview_login);

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(Registration.this,Login.class);
                startActivity(LoginIntent);
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = textUsername.getText().toString().trim();
                String pwd = textPassword.getText().toString().trim();

            }
        });
    }
}
