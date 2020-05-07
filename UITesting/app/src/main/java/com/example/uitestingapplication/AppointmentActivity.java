package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Appointment;
import com.example.uitestingapplication.db.repo.AppointmentRepo;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity implements
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private Appointment appointment = new Appointment();
    private MedicareAppDatabase db;
    private EditText appointmentTitle, doctorName;
    private ImageView save,show_btn;
    private TextView tvResult;

    int day,month,year,hour,minute;
    int finalDay,finalMonth,finalYear,finalHour,finalMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= Room.databaseBuilder(getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();
        setContentView(R.layout.activity_appointment_u_i);
        appointmentTitle = findViewById(R.id.appointment_title);
        doctorName = findViewById(R.id.doctor_name);
        tvResult = findViewById(R.id.tvResult);
        save = findViewById(R.id.save_appointment);
        show_btn = findViewById(R.id.show_appointment_btn);
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AppointmentActivity.this, AppointmentActivity.this, year, month, day );
            datePickerDialog.show();

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test: ","good");

                saveAppointment();
               // Intent showAppointments = new Intent(AppointmentActivity.this,ShowAppointmentActivity.class);
            //   startActivity(showAppointments);
                Log.i("test: ","good");
            }
        });

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentActivity.this,ShowAppointmentActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        finalYear = i;
        finalMonth = i1;
        finalDay = i2;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog((AppointmentActivity.this), AppointmentActivity.this, hour,minute,false);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        finalHour = i;
        finalMinute = i1;
        tvResult.setText(finalDay+"-"+finalMonth+"-"+finalYear+" "+" Time "+finalHour+":"+finalMinute);
    }

    public void saveAppointment()
    {
        appointment.setAppointmentTitle(appointmentTitle.getText().toString());
        appointment.setDoctorName(doctorName.getText().toString());
        appointment.setDateAndTime(Integer.toString(finalDay+finalMonth+finalYear+finalHour+finalMinute));
        db.getAppointmentRepo().insertAppointment(appointment);
        Toast.makeText(this,"Appointment set Successfully",Toast.LENGTH_SHORT).show();

    }
}
