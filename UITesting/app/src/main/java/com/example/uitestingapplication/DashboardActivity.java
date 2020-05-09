package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    CardView medicine,appointment,users,reports,settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_u_i);

        medicine = findViewById(R.id.medicine_activity);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DashboardActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        appointment = findViewById(R.id.appointment_activity);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DashboardActivity.this, AppointmentActivity.class);
                startActivity(intent2);

            }
        });

        users = findViewById(R.id.users_activity);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ShowUsersActivity.class);
                startActivity(intent);
            }
        });

        reports = findViewById(R.id.reports_activity);
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DashboardActivity.this, ReportActivity.class);
                startActivity(intent3);

            }
        });
    }
}
