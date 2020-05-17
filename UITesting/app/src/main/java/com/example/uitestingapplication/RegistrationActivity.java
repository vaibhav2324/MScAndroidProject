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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Registration;

public class RegistrationActivity extends AppCompatActivity {

    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    private EditText textName, textUsername, textPassword, mobile, age;
    private RadioGroup gender_group;
    private RadioButton gender_button;
    private MedicareAppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = Room.databaseBuilder(getApplicationContext(), MedicareAppDatabase.class, "medicareDB").allowMainThreadQueries().build();
        textName = findViewById(R.id.edittext_name);
        textUsername = findViewById(R.id.edittext_username);
        textPassword = findViewById(R.id.edittext_password);
        mobile = findViewById(R.id.edittext_mobile);
        age = findViewById(R.id.edittext_age);
        gender_group = findViewById(R.id.genders);
        Button buttonRegister = findViewById(R.id.button_register);
        TextView textLogin = findViewById(R.id.textview_login);

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

                if (awesomeValidation.validate() && registrationValidation()) {
                    String name = textName.getText().toString().trim();
                    String email = textUsername.getText().toString().trim();
                    String password = textPassword.getText().toString().trim();
                    String mobileNo = mobile.getText().toString().trim();
                    int ageStr = Integer.parseInt(age.getText().toString().trim());
                    int selectedId = gender_group.getCheckedRadioButtonId();
                    gender_button = findViewById(selectedId);
                    String gender = gender_button.getText().toString().trim();

                    Registration register = new Registration(name,email, password, mobileNo, ageStr, gender);
                    long id = db.getRegistrationRepo().addUser(register);
                    Log.i("db", "success with id " + id);
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    static String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    private boolean registrationValidation(){
        awesomeValidation.addValidation(this,R.id.edittext_name,RegexTemplate.NOT_EMPTY,R.string.invalid_registration_name);
        awesomeValidation.addValidation(this, R.id.edittext_username, RegexTemplate.NOT_EMPTY,R.string.invalid_registration_username);
        awesomeValidation.addValidation(this, R.id.edittext_password, RegexTemplate.NOT_EMPTY,R.string.invalid_registration_password);
        awesomeValidation.addValidation(this, R.id.edittext_age, RegexTemplate.NOT_EMPTY,R.string.invalid_registration_age);
        awesomeValidation.addValidation(this, R.id.edittext_mobile, RegexTemplate.NOT_EMPTY,R.string.invalid_registration_mobile);
        awesomeValidation.addValidation(this, R.id.edittext_mobile, RegexTemplate.TELEPHONE,R.string.invalid_registration_valid_mobile);
        awesomeValidation.addValidation(this, R.id.edittext_username, emailPattern,R.string.invalid_registration_username);

        int radio = gender_group.getCheckedRadioButtonId();
        if (radio == -1){
            Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
