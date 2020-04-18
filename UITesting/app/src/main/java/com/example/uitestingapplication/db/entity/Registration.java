package com.example.uitestingapplication.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private  String email;
    private String password;
    private  String mobile;
    private int age;
    private  String gender;

    public Registration(String email, String password, String mobile, int age, String gender) {
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
    }
}
