package com.example.uitestingapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity {
    private static final int NOTIFY_ID = 2020;
    private static final int CAMERA_INTENT = 51;
    CardView medicine, appointment, users, reports, settings;
    CircleImageView circleImageView;
    ImageView imageView;
    Bitmap bitmap;
    TextView user_name, user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_u_i);
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("session", 0); // 0 - for private mode

        Button button;
        button = findViewById(R.id.notify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(DashboardActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setContentTitle("MediCare");
                builder.setContentText("Notification");
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

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(NOTIFY_ID,notification);
            }
        });

        circleImageView = findViewById(R.id.session_profile_image);
        user_name = findViewById(R.id.user_session_name);
        user_email = findViewById(R.id.user_session_email);
        imageView = findViewById(R.id.session_logout);

        String session_user_name = pref.getString("session_user_name", "Admin");
        String session_user_email = pref.getString("session_user_email", "admin@student.com");
        user_name.setText(session_user_name);
        user_email.setText(session_user_email);
        bitmap = null;

        circleImageView.setOnClickListener(new View.OnClickListener() {
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
                            circleImageView.setImageBitmap(bitmap);
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
    }

