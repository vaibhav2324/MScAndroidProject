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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Registration;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    EditText textUsername;
    EditText textPassword;
    Button cirLoginButton;
    TextView textRegister;
    Registration registration;
    MedicareAppDatabase medicareAppDatabase;

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userID = sessionManagement.getUserIdBySession();
        if(userID != -1){
            //user id logged in and so move to mainActivity
            moveToDashboardActivity();
        }
        else{
            //do nothing
        }
    }

    public void login(View view) {
        // 1.log in to app and save session of user
        // 2. move to mainActivity

        //1. login and save session
        Registration user = new Registration(registration.getName(),registration.getEmail(),registration.getPassword(),registration.getMobile(),registration.getAge(),registration.getGender());  //change **
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        sessionManagement.saveSession(user);
        //2. step
        moveToDashboardActivity();
    }

    private void moveToDashboardActivity() {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        medicareAppDatabase = Room.databaseBuilder(getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();
        textUsername = findViewById(R.id.username_login);
        textPassword = findViewById(R.id.login_edittext_password);
        cirLoginButton = findViewById(R.id.button_login);
        textRegister = findViewById(R.id.textview_register);

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
                if(awesomeValidation.validate() && loginValidation()) {
                    final String password = textPassword.getText().toString().trim();
                    final String username = textUsername.getText().toString().trim();
                    List<Registration> retrievedList = medicareAppDatabase.getRegistrationRepo().getUserByEmailAndPassword(username, password);
                    if (retrievedList.size() > 0) {
                        SessionManagement s = new SessionManagement(LoginActivity.this);
                        s.saveSession(retrievedList.get(0));
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent moveToDashboard = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(moveToDashboard);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                        Intent moveToRegistration = new Intent(LoginActivity.this, RegistrationActivity.class);
                        startActivity(moveToRegistration);
                    }
                }
            }
        });
    }
    public boolean loginValidation(){
        awesomeValidation.addValidation(this,R.id.username_login, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        awesomeValidation.addValidation(this, R.id.login_edittext_password,RegexTemplate.NOT_EMPTY,R.string.invalid_password);
        return !textUsername.getText().toString().isEmpty() && !textPassword.getText().toString().isEmpty();
    }
}

