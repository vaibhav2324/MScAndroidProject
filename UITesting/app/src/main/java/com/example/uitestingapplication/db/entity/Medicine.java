package com.example.uitestingapplication.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medicine {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String medicineName;
    private String date;
    private String nod;
    private String instruction;
    private String treatmentPeriod;
    private String fileName;
    private byte[] medicineImage;

    private int userID;
}
