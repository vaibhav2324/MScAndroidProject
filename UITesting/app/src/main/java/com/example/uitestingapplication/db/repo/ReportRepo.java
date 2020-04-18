package com.example.uitestingapplication.db.repo;

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
    public void insertReport(Report report);
    @Update
    public void updateReport(Report report);
    @Delete
    public void deleteReport(Report report);
    @Query("select * from reports")
    public List<Report> getAllReports();
}
