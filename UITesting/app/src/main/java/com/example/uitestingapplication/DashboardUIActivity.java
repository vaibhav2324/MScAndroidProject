package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardUIActivity extends AppCompatActivity {

    CardView medicine,appointment,users,reports,settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_u_i);

        medicine = (CardView) findViewById(R.id.medicine_activity);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DashboardUIActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        appointment = (CardView) findViewById(R.id.appointment_activity);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DashboardUIActivity.this,AppointmentUIActivity.class);
                startActivity(intent2);

            }
        });

        reports = (CardView) findViewById(R.id.reports_activity);
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DashboardUIActivity.this,ReportUIActivity.class);
                startActivity(intent3);

            }
        });


    }
}
