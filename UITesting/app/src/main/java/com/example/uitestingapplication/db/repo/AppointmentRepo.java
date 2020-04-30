package com.example.uitestingapplication.db.repo;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uitestingapplication.db.entity.Appointment;
import com.example.uitestingapplication.db.entity.Medicine;

import java.util.List;

public interface AppointmentRepo {
    @Insert
    public long insertAppointment(Appointment appointment);

    @Update
    public void updateAppointment(Appointment appointment);

    @Delete
    public void deleteAppointment(Appointment appointment);

    @Query("select * from appointments")
    public List<Appointment> getAllAppointments();

}
