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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Appointment;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity implements
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
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
              saveAppointment();
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
        finalMonth = i1+1;
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

    private boolean appointmentValidation(){
        awesomeValidation.addValidation(this,R.id.appointment_title, RegexTemplate.NOT_EMPTY,R.string.invalid_appointment_title);
        awesomeValidation.addValidation(this,R.id.doctor_name, RegexTemplate.NOT_EMPTY,R.string.invalid_doctor_name);
        if (appointmentTitle.getText().toString().isEmpty() || doctorName.getText().toString().isEmpty()){
            return false;
        }

        return true;
    }

    public void saveAppointment()
    {
      Boolean b=  awesomeValidation.validate();
      Log.i("test",b.toString());
        if (appointmentValidation()) {
            appointment.setAppointmentTitle(appointmentTitle.getText().toString());
            appointment.setDoctorName(doctorName.getText().toString());
            appointment.setDateAndTime(finalDay+"-"+finalMonth+"-"+finalYear+" "+" Time "+finalHour+":"+finalMinute);
            int id = new SessionManagement(getApplicationContext()).getUserIdBySession();
            appointment.setUserID(id);
            db.getAppointmentRepo().insertAppointment(appointment);
            Toast.makeText(this, "Appointment set Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AppointmentActivity.this, ShowAppointmentActivity.class);
            startActivity(intent);
        }
    }
}
