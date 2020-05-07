package com.example.uitestingapplication.db.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uitestingapplication.db.entity.Medicine;

import java.util.List;
@Dao
public interface MedicineRepo {

    @Insert
    long insertMedicine(Medicine medicine);

    @Update
    void updateMedicine(Medicine medicine);

    @Delete
    void deleteMedicine(Medicine medicine);

    @Query("select * from medicines")
    LiveData<List<Medicine>> getAllMedicines();

}
