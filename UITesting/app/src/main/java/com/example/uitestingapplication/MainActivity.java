package com.example.uitestingapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Medicine;
import java.util.ArrayList;
import java.util.Calendar;
import petrov.kristiyan.colorpicker.ColorPicker;


public class MainActivity extends AppCompatActivity implements
  DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private static final int PICK_IMAGE = 1;
    Medicine medicine=new Medicine();
    private RadioButton ongoingTreatment, noOfDays,beforeEating,whileEating,afterEating,notMatters;
    private EditText medName,metFileName;
    private ImageView mImage,mBtnChoose,showMedicines;
    private TextView textView;
    private LinearLayout color_change;
    private MedicareAppDatabase db;

    int day,month,year,hour,minute;
    int finalDay,finalMonth,finalYear,finalHour,finalMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= Room.databaseBuilder(getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();

        showMedicines = findViewById(R.id.show_medicines_btn);
        medName = findViewById(R.id.med_name);
        ongoingTreatment = findViewById(R.id.ongoing_treatment);
        noOfDays = findViewById(R.id.no_of_days);
        beforeEating = findViewById(R.id.before_eating);
        whileEating = findViewById(R.id.while_eating);
        afterEating = findViewById(R.id.after_eating);
        notMatters = findViewById(R.id.not_matters);
        mBtnChoose = findViewById(R.id.choose);
        metFileName = findViewById(R.id.filename);
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

        showMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowMedicineActivity.class);
                startActivity(intent);
            }
        });
//        textView = findViewById(R.id.instructions);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialog();
//            }
//        });

        textView = findViewById(R.id.date_time);
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

        Button saveMedicine = findViewById(R.id.save_medicines);
        saveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveMedicineData();
            }
        });

        color_change = findViewById(R.id.color_viewer);
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
            Uri mImageUri = data.getData();
            mImage.setImageURI(mImageUri);
        }
    }

//        public void openDialog(){
//            InstructionDialog dialogFragment = new InstructionDialog();
//            dialogFragment.show(getSupportFragmentManager(),"Instruction Dialog Box");
//        }

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
       public void saveMedicineData(){

            medicine.setMedicineName(medName.getText().toString());
            medicine.setDate(textView.getText().toString());
            String onGoingTreatment = ongoingTreatment.getText().toString();
            String numberOfDays = noOfDays.getText().toString();
           String before = beforeEating.getText().toString();
           String after = afterEating.getText().toString();
           String whileEat = whileEating.getText().toString();
           String notMatter = notMatters.getText().toString();
            medicine.setFileName(metFileName.getText().toString());
            medicine.setMedicineImage(ImageConverter.convertImageToByteArray(mImage));
            if(beforeEating.isChecked()){
                medicine.setInstruction(before);
            }
           if(whileEating.isChecked()){
               medicine.setInstruction(whileEat);
           }
           if(afterEating.isChecked()){
               medicine.setInstruction(after);
           }
           if(notMatters.isChecked()){
               medicine.setInstruction(notMatter);
           }

            if(ongoingTreatment.isChecked()){
                medicine.setTreatmentPeriod(onGoingTreatment);
            }
            else{
                medicine.setTreatmentPeriod(numberOfDays);
            }
//            medicine.setInstruction(getInstructions());
            db.getMedicineRepo().insertMedicine(medicine);
            Toast.makeText(this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
            Intent moveToMedicineList = new Intent(MainActivity.this,ShowMedicineActivity.class);
            startActivity(moveToMedicineList);
        }

//    @Override
//    public void onPositiveButtonClick(String[] items, String selectedItems) {
//        medicine.setInstruction(selectedItems);
//    }
//
//    @Override
//    public void onNegativeButtonClick() {
//
//    }
}

