package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Medicine;

import java.util.List;

public class MedicineNotification extends AppCompatActivity {
    MedicareAppDatabase db;
    ImageView med_image;
    TextView med_name, med_instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_notification);
        db=Room.databaseBuilder(getApplicationContext(), MedicareAppDatabase.class, "medicareDB").allowMainThreadQueries().build();

        med_image = findViewById(R.id.medicine_image_notification);
        med_name = findViewById(R.id.medicine_name_notification);
        med_instruction = findViewById(R.id.medicine_instruction_notification);

        List<Medicine> medicines = medicineNotificationData();
        Medicine medicine = medicines.get(0);
        med_image.setImageBitmap((ImageConverter.convertByteArrayToImage(medicine.getMedicineImage())));
        med_name.setText(medicine.getMedicineName());
        med_instruction.setText(medicine.getInstruction());
    }
    public List<Medicine> medicineNotificationData()
    {
        int id = new SessionManagement(getApplicationContext()).getUserIdBySession();
        return db.getMedicineRepo().getListOfMedicinesByUserID(id);
    }
}
