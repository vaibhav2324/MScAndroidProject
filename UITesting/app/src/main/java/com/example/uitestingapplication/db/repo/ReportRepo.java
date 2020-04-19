package com.example.uitestingapplication.db.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.uitestingapplication.db.entity.Report;

import java.util.List;

@Dao
public interface ReportRepo {
  @Insert
    void insertReport(Report report);
    @Update
    void updateReport(Report report);
    @Delete
    int deleteReport(Report report);
    @Query("select * from reports")
    LiveData<List<Report>> getAllReports();


}
