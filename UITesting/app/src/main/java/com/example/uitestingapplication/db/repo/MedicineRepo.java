package com.example.uitestingapplication.db.repo;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uitestingapplication.MainActivity;
import com.example.uitestingapplication.db.entity.Medicine;
import com.example.uitestingapplication.db.entity.Registration;

import java.util.List;

public interface MedicineRepo {

    @Insert
    public long insertMedicine(Medicine medicine);

    @Update
    public void updateMedicine(Medicine medicine);

    @Delete
    public void deleteMedicine(Medicine medicine);

    @Query("select * from medicines")
    public List<Medicine> getAllMedicines();

}
