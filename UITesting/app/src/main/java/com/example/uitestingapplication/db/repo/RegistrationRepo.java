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
  public long addRegistration(Registration registration);

    @Update
    public void updateRegistration(Registration registration);

    @Delete
    public void deleteRegistration(Registration registration);

    @Query("select * from registration")
    public List<Registration> getAllRegistrations();

    @Query("select * from registration where email==:email and password==:password")
    public List<Registration> getRegistrationsByEmailAndPassword(String email, String password);
}
