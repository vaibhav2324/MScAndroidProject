package com.example.uitestingapplication.db.repo;

import androidx.lifecycle.LiveData;
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
    long addUser(Registration registration);
    @Update
    int updateUser(Registration registration);
    @Delete
    int deleteUser(Registration registration);
    @Query("select * from registration")
    LiveData<List<Registration>> getAllUsers();
    @Query("select * from registration where email==:email and password==:password")
    List<Registration> getUserByEmailAndPassword(String email, String password);
    @Query("select * from registration")
    List<Registration> getAllUserWithArrayList();
}
