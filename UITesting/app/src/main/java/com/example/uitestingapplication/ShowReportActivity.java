package com.example.uitestingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.db.entity.Report;
import com.example.uitestingapplication.db.service.ReportViewModel;

import java.util.List;

public class ShowReportActivity extends AppCompatActivity implements ReportRecyclerView.OnDeleteClickListener {


    RecyclerView recyclerView;
    ReportRecyclerView reportRecyclerView;
    ReportViewModel reportViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reports);
        recyclerView =findViewById(R.id.recyclerview);
        reportRecyclerView = new ReportRecyclerView(this,this);
        recyclerView.setAdapter(reportRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
        reportViewModel.getReports().observe(this, new Observer<List<Report>>() {
            @Override
            public void onChanged(List<Report> reports) {
                reportRecyclerView.setReport(reports);
            }
        });
        ImageView fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowReportActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void OnDeleteClickListener(Report report) {
        reportViewModel.delete(report);
    }
}
