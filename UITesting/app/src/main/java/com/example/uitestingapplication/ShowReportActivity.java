package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Report;
import com.example.uitestingapplication.db.repo.ReportRepo;
import com.example.uitestingapplication.db.service.ReportViewModel;

public class ShowReportActivity extends AppCompatActivity implements ReportRecyclerView.OnDeleteClickListener {

    MedicareAppDatabase db;
    RecyclerView recyclerView;
    ReportRecyclerView reportRecyclerView;
    ReportRepo repo;
    ReportViewModel reportViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reports);
        db = Room.databaseBuilder(getApplicationContext(), MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();
        recyclerView =findViewById(R.id.recyclerview);
        db.getReportRepo().getAllReports();
        reportRecyclerView = new ReportRecyclerView(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reportRecyclerView);    }

    @Override
    public void OnDeleteClickListener(Report report) {
        reportViewModel.delete(report);
    }
}
