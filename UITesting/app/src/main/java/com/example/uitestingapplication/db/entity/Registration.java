package com.example.uitestingapplication.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Registration {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private  String email;
    private String password;
    private  String mobile;
    private int age;
    private  String gender;
    private int userID;


    public Registration(String name,String email, String password, String mobile, int age, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
    }
}
