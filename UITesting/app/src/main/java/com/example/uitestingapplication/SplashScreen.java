package com.example.uitestingapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
SessionManagement ss=new SessionManagement(SplashScreen.this);
        int id = ss.getUserIdBySession();

        if(id==-1) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
