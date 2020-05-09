package com.example.uitestingapplication.db.service;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Report;
import com.example.uitestingapplication.db.repo.ReportRepo;

import java.util.List;

public class ReportViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private ReportRepo reportRepo;
    private LiveData<List<Report>> mAllReports;

    public ReportViewModel(@NonNull Application application) {
        super(application);
        MedicareAppDatabase db = MedicareAppDatabase.getDatabase(application);
        reportRepo = db.getReportRepo();
        mAllReports = reportRepo.getAllReports();
    }
    public LiveData<List<Report>> getReports(){
        return mAllReports;
    }
    public void delete(Report report) {
        new DeleteAsyncTask(reportRepo).execute(report);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    class OperationsAsyncTask extends AsyncTask<Report, Void, Void> {

        ReportRepo mAsyncTaskDao;

        OperationsAsyncTask(ReportRepo dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Report... reports) {
            return null;
        }
    }

    class DeleteAsyncTask extends OperationsAsyncTask {

        DeleteAsyncTask(ReportRepo reportDao) {
            super(reportRepo);
        }

        @Override
        protected Void doInBackground(Report... reports) {
            mAsyncTaskDao.deleteReport(reports[0]);
            return null;
        }
    }
}

