package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Registration;

public class RegistrationActivity extends AppCompatActivity {

    private EditText textName,textUsername,textPassword,mobile,age;
    private RadioGroup gender_group;
    private RadioButton gender_button;
    private Button buttonRegister;
    private TextView textLogin;
    private MedicareAppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db= Room.databaseBuilder(getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();
        textName = (EditText)findViewById(R.id.edittext_name);
        textUsername = (EditText)findViewById(R.id.edittext_username);
        textPassword = (EditText)findViewById(R.id.edittext_password);
        mobile = (EditText)findViewById(R.id.edittext_mobile);
        age = (EditText)findViewById(R.id.edittext_age);
        gender_group = (RadioGroup)findViewById(R.id.genders);
        buttonRegister = (Button) findViewById(R.id.button_register);
        textLogin = (TextView) findViewById(R.id.textview_login);

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = textName.getText().toString().trim();
                String email = textUsername.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String mobileNo = mobile.getText().toString().trim();
                int ageStr = Integer.parseInt(age.getText().toString().trim());
                int selectedId = gender_group.getCheckedRadioButtonId();
                gender_button = (RadioButton)findViewById(selectedId);
                String gender =  gender_button.getText().toString().trim();

          Registration register=new Registration(email,password,mobileNo,ageStr,gender);
          long id=db.getRegistrationRepo().addRegistration(register);
                Log.i("db","success with id "+id);
                Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
