package com.example.uitestingapplication.db.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uitestingapplication.db.entity.Appointment;

import java.util.List;
@Dao
public interface AppointmentRepo {
    @Insert
    long insertAppointment(Appointment appointment);

    @Update
    void updateAppointment(Appointment appointment);

    @Delete
    void deleteAppointment(Appointment appointment);

    @Query("select * from appointments")
    LiveData<List<Appointment>> getAllAppointments();

}
