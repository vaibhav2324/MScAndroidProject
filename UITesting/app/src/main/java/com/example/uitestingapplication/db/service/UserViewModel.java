package com.example.uitestingapplication.db.service;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Registration;
import com.example.uitestingapplication.db.repo.RegistrationRepo;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private String TAG = this.getClass().getSimpleName();
    private RegistrationRepo userRepo;

    private LiveData<List<Registration>> mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        MedicareAppDatabase db= Room.databaseBuilder(application.getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();

        userRepo = db.getRegistrationRepo();
        mAllUsers = userRepo.getAllUsers();
       }

    public LiveData<List<Registration>> getUsers(){
        return mAllUsers;
    }
    public void delete(Registration user) {
        new DeleteAsyncTask(userRepo).execute(user);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class OperationsAsyncTask extends AsyncTask<Registration, Void, Void> {

        RegistrationRepo mAsyncTaskDao;

        OperationsAsyncTask(RegistrationRepo dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Registration... registrations) {
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        DeleteAsyncTask(RegistrationRepo userDao) {
            super(userRepo);
        }

        @Override
        protected Void doInBackground(Registration... registrations) {
            mAsyncTaskDao.deleteUser(registrations[0]);
            return null;
        }
    }
}
