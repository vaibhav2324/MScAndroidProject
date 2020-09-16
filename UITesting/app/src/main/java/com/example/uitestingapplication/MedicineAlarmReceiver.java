package com.example.uitestingapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MedicineAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DashboardActivity dashboardActivity = new DashboardActivity();

//        dashboardActivity.medicineNotificationData();
//        dashboardActivity.medicineNotification();

    }
}
