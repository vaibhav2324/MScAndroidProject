package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import petrov.kristiyan.colorpicker.ColorPicker;


public class MainActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private static final int PICK_IMAGE = 1;

    private ImageView mBtnChoose;
    private Button mBtnUpload;
    private TextView mtvShow;
    private EditText metFileName;
    private ImageView mImage;

    private Uri mImageUri;

    private TextView textView;
    private LinearLayout color_change;

    int day,month,year,hour,minute;
    int finalDay,finalMonth,finalYear,finalHour,finalMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnChoose = (ImageView) findViewById(R.id.choose);
        mBtnChoose = findViewById(R.id.choose);

        metFileName = (EditText) findViewById(R.id.filename);
        metFileName = findViewById(R.id.filename);

        mImage = (ImageView) findViewById(R.id.image);
        mImage = findViewById(R.id.image);

        mBtnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFile();
            }
        });
        metFileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        textView = (TextView) findViewById(R.id.instructions) ;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        textView = (TextView) findViewById(R.id.date_time);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, MainActivity.this, year, month, day );
                datePickerDialog.show();
            }
        });



        color_change = (LinearLayout)findViewById(R.id.color_viewer);
        color_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });
        }

    private void openFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        finalYear = i;
        finalMonth = i1;
        finalDay = i2;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog((MainActivity.this), MainActivity.this, hour,minute,false);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        finalHour = i;
        finalMinute = i1;


        textView.setText(finalDay+"-"+finalMonth+"-"+finalYear+" "+" Time "+finalHour+":"+finalMinute);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();
            mImage.setImageURI(mImageUri);
        }
    }

        public void openDialog(){
            InstructionDialog dialogFragment = new InstructionDialog();
            dialogFragment.show(getSupportFragmentManager(),"Instruction Dialog Box");
        }

        public void openColorPicker(){
        final ColorPicker colorPicker = new ColorPicker(this);
            ArrayList<String> colors=new ArrayList<>();
            colors.add("#258174");
            colors.add("#3C8D2F");
            colors.add("#20724F");
            colors.add("#6A3AB2");
            colors.add("#323299");
            colors.add("#800080");
            colors.add("#B79716");
            colors.add("#258174");
            colors.add("#966D37");
            colors.add("#B77231");
            colors.add("#808000");


            colorPicker.setColors(colors)
            .setColumns(5)
                    .setRoundColorButton(true)
                    .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                        @Override
                        public void onChooseColor(int position, int color) {
                            color_change.setBackgroundColor(color);
                        }

                        @Override
                        public void onCancel() {

                        }
            }).show();
        }


    }

