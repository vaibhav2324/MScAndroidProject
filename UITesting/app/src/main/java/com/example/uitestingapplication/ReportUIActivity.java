package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.MedicareAppDatabase_Impl;
import com.example.uitestingapplication.db.entity.Report;
import com.example.uitestingapplication.db.repo.RegistrationRepo;
import com.example.uitestingapplication.db.repo.ReportRepo;

import java.io.ByteArrayOutputStream;

public class ReportUIActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final int CAMERA_INTENT = 51;

    private Bitmap bitmap;
    private ImageView mBtnChoose;
    private ImageView image;
    private EditText metFileName,reportDescription;
    private ImageView mImage;
    private Uri mImageUri;
    private ImageView save;
    private ImageView show;
    private MedicareAppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_u_i);

        db = Room.databaseBuilder(getApplicationContext(),MedicareAppDatabase.class,"medicareDB").allowMainThreadQueries().build();

        mBtnChoose =  findViewById(R.id.choose);
        image = findViewById(R.id.camera);
        metFileName =  findViewById(R.id.filename);
        reportDescription = findViewById(R.id.description);
        mImage = findViewById(R.id.image);
        save =  findViewById(R.id.save_report);
        show=  findViewById(R.id.showReport);
        bitmap = null;

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReports(v);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReport(v);
            }
        });
        mBtnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFile();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_INTENT);
                }
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReport(v);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case CAMERA_INTENT:
            if(requestCode==Activity.RESULT_OK)
            {
                bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap==null){
                    Toast.makeText(this,"Bitmap is null",Toast.LENGTH_SHORT).show();
                }
                else{
                    mImage.setImageBitmap(bitmap);
                }
            }
            else{
                Toast.makeText(this,"Result not OK",Toast.LENGTH_SHORT).show();
            }
            break;
        }

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();
            mImage.setImageURI(mImageUri);
        }
    }

    public void saveReport(View view){


        if(mImage==null||metFileName.getText().toString().isEmpty()||reportDescription.getText().toString().isEmpty()){
            Toast.makeText(this,"Data is missing",Toast.LENGTH_SHORT).show();
        }
        else{
            Report report = new Report();
            report.setDescription(reportDescription.getText().toString());
            report.setFileName(metFileName.getText().toString());
            report.setImage(convertImageToByteArray(mImage));
            db.getReportRepo().insertReport(report);
            Toast.makeText(this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
            Intent showReports = new Intent(ReportUIActivity.this,ShowReportActivity.class);
            startActivity(showReports);
        }
    }

    public static byte[] convertImageToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,0,stream);
        return stream.toByteArray();
    }
    public static byte[] convertImageToByteArray(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,0,stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArrayToImage(byte[] array){
        return BitmapFactory.decodeByteArray(array,0,array.length);
    }

    public void showReports(View view)
    {
        Intent intent = new Intent(ReportUIActivity.this,ShowReportActivity.class);
        startActivity(intent);
    }
}