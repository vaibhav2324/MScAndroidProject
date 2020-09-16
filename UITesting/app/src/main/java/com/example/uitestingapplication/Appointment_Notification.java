package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Appointment;

import java.util.List;

public class Appointment_Notification extends AppCompatActivity {
TextView title, doctor, date;
MedicareAppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment__notification);

        db = Room.databaseBuilder(getApplicationContext(), MedicareAppDatabase.class, "medicareDB").allowMainThreadQueries().build();
        title = findViewById(R.id.appointment_name_notification);
        doctor = findViewById(R.id.appointment_dr_notification);
        date = findViewById(R.id.appointment_date_notification);

        List<Appointment> appointments = appointmentNotificationData();

        Appointment appointment = appointments.get(0);
        title.setText(appointment.getAppointmentTitle());
        doctor.setText(appointment.getDoctorName());
        date.setText(appointment.getDateAndTime());


    }
    public List<Appointment> appointmentNotificationData()
    {
        int id = new SessionManagement(getApplicationContext()).getUserIdBySession();
        return db.getAppointmentRepo().getListOfAppointmentsByUserId(id);
    }

}
