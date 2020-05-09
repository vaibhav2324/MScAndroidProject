package com.example.uitestingapplication.db.service;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Appointment;
import com.example.uitestingapplication.db.repo.AppointmentRepo;

import java.util.List;

public class AppointmentViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private AppointmentRepo appointmentRepo;
    private LiveData<List<Appointment>> mAllAppointments;

    public AppointmentViewModel(@NonNull Application application) {
        super(application);
        MedicareAppDatabase db= Room.databaseBuilder(application.getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();
        appointmentRepo = db.getAppointmentRepo();
        mAllAppointments = appointmentRepo.getAllAppointments();
    }

    public LiveData<List<Appointment>> getAppointments(){
        return mAllAppointments;
    }
    public void delete(Appointment appointment) {
        new AppointmentDeleteAsyncTask(appointmentRepo).execute(appointment);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class AppointmentOperationsAsyncTask extends AsyncTask<Appointment, Void, Void> {

        AppointmentRepo mAsyncTaskDao;

        AppointmentOperationsAsyncTask(AppointmentRepo dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Appointment... appointments) {
            return null;
        }
    }

    private class AppointmentDeleteAsyncTask extends AppointmentOperationsAsyncTask {

        AppointmentDeleteAsyncTask(AppointmentRepo appointmentDao) {
            super(appointmentRepo);
        }

        @Override
        protected Void doInBackground(Appointment... appointments) {
            mAsyncTaskDao.deleteAppointment(appointments[0]);
            return null;
        }
    }
}
