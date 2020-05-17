package com.example.uitestingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.uitestingapplication.db.MedicareAppDatabase;
import com.example.uitestingapplication.db.entity.Report;

public class ReportActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final int CAMERA_INTENT = 51;
    ImageConverter imageConverter = new ImageConverter();
    Report report = new Report();
    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    private Bitmap bitmap;
    private EditText metFileName, reportDescription;
    private ImageView mImage;
    private MedicareAppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_u_i);
        db = Room.databaseBuilder(getApplicationContext(), MedicareAppDatabase.class, "medicareDB").allowMainThreadQueries().build();
        ImageView mBtnChoose = findViewById(R.id.choose);
        ImageView image = findViewById(R.id.camera);
        metFileName = findViewById(R.id.filename);
        reportDescription = findViewById(R.id.description);
        mImage = findViewById(R.id.report_image);
        Button save = findViewById(R.id.save_report);
        ImageView show = findViewById(R.id.showReport);
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
                if (intent.resolveActivity(getPackageManager()) != null) {
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


    private void openFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAMERA_INTENT:

                if (resultCode == Activity.RESULT_OK) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    if (bitmap == null) {
                        Toast.makeText(this, "Bitmap is null", Toast.LENGTH_SHORT).show();
                    } else {
                        mImage.setImageBitmap(bitmap);
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

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri mImageUri = data.getData();
            mImage.setImageURI(mImageUri);
        }
    }

    private boolean reportValidations() {
        awesomeValidation.addValidation(this, R.id.filename, RegexTemplate.NOT_EMPTY, R.string.invalid_report_filename);
        awesomeValidation.addValidation(this, R.id.description, RegexTemplate.NOT_EMPTY, R.string.invalid_report_description);
        if (mImage.getDrawable() == null) {
            Toast.makeText(this, "Image is missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void saveReport(View view) {
        if (awesomeValidation.validate() && reportValidations()) {
            report.setDescription(reportDescription.getText().toString());
            report.setFileName(metFileName.getText().toString());
            report.setImage(ImageConverter.convertImageToByteArray(mImage));
            int id = new SessionManagement(getApplicationContext()).getUserIdBySession();
            report.setUserID(id);
            db.getReportRepo().insertReport(report);
            Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
            Intent showReports = new Intent(ReportActivity.this, ShowReportActivity.class);
            startActivity(showReports);
        }
    }

    public void showReports(View view) {
        Intent intent = new Intent(ReportActivity.this, ShowReportActivity.class);
        startActivity(intent);
    }
}