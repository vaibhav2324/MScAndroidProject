package com.example.uitestingapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.uitestingapplication.R;
import com.example.uitestingapplication.ReportUIActivity;
import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Report;

import java.util.List;

public class ReportRecyclerView extends RecyclerView.Adapter<ReportViewHolder> {
    List<Report> reportList;

    public ReportRecyclerView(List<Report> reports){

        reportList = reports;
    }



    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.report_items_layout,
                parent,
                false
        );
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder reportViewHolder, int position) {
         Report report = reportList.get(position);
        final Report report1 = report;
        reportViewHolder.imageView.setImageBitmap(ReportUIActivity.convertByteArrayToImage(report.getImage()));
        reportViewHolder.filename.setText(report.getFileName());
        reportViewHolder.description.setText(report.getDescription());
        reportViewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReport(report1);
            }
        });
    }

    public void deleteReport(Report report)
    {
        reportList.remove(report);
    }
    @Override
    public int getItemCount() {
        return reportList.size();
    }





}
