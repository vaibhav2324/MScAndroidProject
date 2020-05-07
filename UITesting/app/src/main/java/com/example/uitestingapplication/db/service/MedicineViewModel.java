package com.example.uitestingapplication.db.service;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Medicine;
import com.example.uitestingapplication.db.repo.MedicineRepo;

import java.util.List;


public class MedicineViewModel extends AndroidViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private MedicineRepo medicineRepo;
    private LiveData<List<Medicine>> mAllMedicines;

    public MedicineViewModel(@NonNull Application application) {
        super(application);
        MedicareAppDatabase db = MedicareAppDatabase.getDatabase(application);
        medicineRepo = db.getMedicineRepo();
        mAllMedicines = medicineRepo.getAllMedicines();
    }
    public LiveData<List<Medicine>> getMedicines(){
        return mAllMedicines;
    }
    public void delete(Medicine medicine) {
        new MedicineViewModel.DeleteAsyncTask(medicineRepo).execute(medicine);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }
    private class OperationsAsyncTask extends AsyncTask<Medicine, Void, Void> {

        MedicineRepo mAsyncTaskDao;

        OperationsAsyncTask(MedicineRepo dao) {
            this.mAsyncTaskDao = (MedicineRepo) dao;
        }

        @Override
        protected Void doInBackground(Medicine... medicines) {
            return null;
        }
    }
    private class DeleteAsyncTask extends MedicineViewModel.OperationsAsyncTask {

        DeleteAsyncTask(MedicineRepo medicineDao) {
            super(medicineRepo);
        }

        @Override
        protected Void doInBackground(Medicine... medicines) {
            mAsyncTaskDao.deleteMedicine(medicines[0]);
            return null;
        }
    }
}
