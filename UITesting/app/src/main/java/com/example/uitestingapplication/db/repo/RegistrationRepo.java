package com.example.uitestingapplication.db.repo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uitestingapplication.db.entity.Registration;

import java.util.List;

@Dao
public interface RegistrationRepo {
    @Insert
    long addRegistration(Registration registration);
    @Update
    void updateRegistration(Registration registration);
    @Delete
    void deleteRegistration(Registration registration);
    @Query("select * from registration")
    List<Registration> getAllRegistrations();
    @Query("select * from registration where email==:email and password==:password")
    List<Registration> getRegistrationsByEmailAndPassword(String email, String password);
}
