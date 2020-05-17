package com.example.uitestingapplication.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "reports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Report {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String description;
    private String fileName;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    private int userID;
}
