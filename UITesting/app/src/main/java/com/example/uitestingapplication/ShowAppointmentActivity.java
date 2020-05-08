package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.uitestingapplication.db.entity.Appointment;
import com.example.uitestingapplication.db.entity.Report;
import com.example.uitestingapplication.db.service.AppointmentViewModel;
import com.example.uitestingapplication.db.service.ReportViewModel;

import java.util.List;

public class ShowAppointmentActivity extends AppCompatActivity implements AppointmentRecyclerView.OnDeleteClickListener{

    RecyclerView recyclerView;
    AppointmentRecyclerView appointmentRecyclerView;
    AppointmentViewModel appointmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointment);
        recyclerView =findViewById(R.id.appointmentRecyclerview);
        appointmentRecyclerView = new AppointmentRecyclerView(this,this);
        recyclerView.setAdapter(appointmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentViewModel = ViewModelProviders.of(this).get(AppointmentViewModel.class);
        appointmentViewModel.getAppointments().observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {
                appointmentRecyclerView.setAppointment(appointments);

            }

        });
//        ImageView fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ShowAppointmentActivity.this, AppointmentActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void OnDeleteClickListener(Appointment appointment) {
        appointmentViewModel.delete(appointment);
    }
}
