package com.example.uitestingapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Appointment;
import com.example.uitestingapplication.db.entity.Medicine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    private static final int NOTIFY_ID = 2020;
    private static final int CAMERA_INTENT = 51;
    private static final String CHANNEL_ID = "Medicare_notification";
    CardView medicine, appointment, users, reports, settings;
    ImageView imageView,profile_pic;
    Bitmap bitmap;
    MedicareAppDatabase db;
    TextView user_name, user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_u_i);
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("session", 0); // 0 - for private mode
        db = Room.databaseBuilder(getApplicationContext(), MedicareAppDatabase.class, "medicareDB").allowMainThreadQueries().build();

        Button button;
        button = findViewById(R.id.notify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineNotification();
                //appointmentNotification();
            }
        });

        profile_pic = findViewById(R.id.session_profile_image);
        user_name = findViewById(R.id.user_session_name);
        user_email = findViewById(R.id.user_session_email);
        imageView = findViewById(R.id.session_logout);

        String session_user_name = pref.getString("session_user_name", "Admin");
        String session_user_email = pref.getString("session_user_email", "admin@student.com");
        user_name.setText(session_user_name);
        user_email.setText(session_user_email);
        bitmap = null;

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_INTENT);
                }
            }
        });

        medicine = findViewById(R.id.medicine_activity);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        appointment = findViewById(R.id.appointment_activity);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DashboardActivity.this, AppointmentActivity.class);
                startActivity(intent2);
            }
        });

        users = findViewById(R.id.users_activity);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ShowUsersActivity.class);
                startActivity(intent);
            }
        });

        reports = findViewById(R.id.reports_activity);
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DashboardActivity.this, ReportActivity.class);
                startActivity(intent3);
            }
        });

        settings = findViewById(R.id.settings_activity);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("session_user_id", -1).apply();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                //  editor.commit();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
                case CAMERA_INTENT:

                    if (resultCode == Activity.RESULT_OK) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        if (bitmap == null) {
                            Toast.makeText(this, "Bitmap is null", Toast.LENGTH_SHORT).show();
                        } else {
                            profile_pic.setImageBitmap(bitmap);
                            Toast.makeText(this, "Image picked Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Result not OK", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default: {
                    Toast.makeText(this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    public List<Medicine> medicineNotificationData()
    {
        int id = new SessionManagement(getApplicationContext()).getUserIdBySession();
        return db.getMedicineRepo().getListOfMedicinesByUserID(id);

        //String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        //String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    }
        public  void medicineNotification()
        {
            List<Medicine> medicines = medicineNotificationData();
            Medicine medicine = medicines.get(0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(DashboardActivity.this);
            builder.setChannelId(CHANNEL_ID);
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            builder.setCategory(NotificationCompat.CATEGORY_REMINDER);
            builder.setSmallIcon(R.mipmap.ic_launcher_round);
            builder.setLargeIcon(ImageConverter.convertByteArrayToImage(medicine.getMedicineImage()));
            builder.setColor(1);
            builder.setContentTitle(medicine.getMedicineName());
            builder.setContentText(medicine.getInstruction());
            builder.setLights(Color.BLUE,200,200);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            builder.setSound(soundUri);
            long[] vibrate = {100,500,100,500};
            builder.setVibrate(vibrate);

            //create pending activity
            Intent intent = new Intent(DashboardActivity.this,DashboardActivity.class);
            intent.putExtra("key",NOTIFY_ID);
            PendingIntent pendingIntent = PendingIntent.getActivity(DashboardActivity.this, 123,intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher_round,"Action 1",pendingIntent);
            NotificationCompat.Action action = actionBuilder.build();
            builder.addAction(action);
            builder.addAction(R.mipmap.ic_launcher_round,"Action 2",pendingIntent);

            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

            builder.setStyle(bigPictureStyle);

            Notification notification = builder.build();

            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(NOTIFY_ID,notification);
            NotificationManager mNotificationManager =(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify_001");
            // NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = CHANNEL_ID;
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "MediCare",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(channelId);
            }
        }

        public List<Appointment> appointmentNotificationData()
        {
            int id = new SessionManagement(getApplicationContext()).getUserIdBySession();

            List<Appointment> listOfAppointmentsByUserId = db.getAppointmentRepo().getListOfAppointmentsByUserId(id);
            return listOfAppointmentsByUserId;
        }

    public  void appointmentNotification()
    {
        List<Appointment> appointments = appointmentNotificationData();
        Appointment appointment = appointments.get(0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(DashboardActivity.this);
        builder.setChannelId(CHANNEL_ID);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        builder.setCategory(NotificationCompat.CATEGORY_REMINDER);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle(appointment.getAppointmentTitle());
        builder.setContentText(appointment.getDoctorName());
        builder.setLights(Color.BLUE,200,200);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(soundUri);
        long[] vibrate = {100,500,100,500};
        builder.setVibrate(vibrate);

        //create pending activity
        Intent intent = new Intent(DashboardActivity.this,DashboardActivity.class);
        intent.putExtra("key",NOTIFY_ID);
        PendingIntent pendingIntent = PendingIntent.getActivity(DashboardActivity.this, 123,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher_round,"Action 1",pendingIntent);
        NotificationCompat.Action action = actionBuilder.build();
        builder.addAction(action);
        builder.addAction(R.mipmap.ic_launcher_round,"Action 2",pendingIntent);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

        builder.setStyle(bigPictureStyle);

        Notification notification = builder.build();

        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID,notification);
        NotificationManager mNotificationManager =(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify_001");
        // NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = CHANNEL_ID;
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "MediCare",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
    }
    }

