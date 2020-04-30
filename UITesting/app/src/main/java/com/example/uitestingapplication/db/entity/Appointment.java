package com.example.uitestingapplication.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String appointmentTitle;
    private String doctorName;
    private String dateAndTime;
}
