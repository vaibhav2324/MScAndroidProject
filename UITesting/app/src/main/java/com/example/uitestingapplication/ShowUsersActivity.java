package com.example.uitestingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.db.entity.Registration;
import com.example.uitestingapplication.db.service.UserViewModel;

import java.util.List;

public class ShowUsersActivity extends AppCompatActivity implements UserRecyclerView.OnDeleteClickListener {

    RecyclerView recyclerView;
    UserRecyclerView userRecyclerView;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        recyclerView =findViewById(R.id.recyclerviewUsers);
        userRecyclerView = new UserRecyclerView(this,this);
        recyclerView.setAdapter(userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, new Observer<List<Registration>>() {
            @Override
            public void onChanged(List<Registration> registrations) {
                userRecyclerView.setUsers(registrations);
                Log.i("data",registrations.toString());
            }
        });
        ImageView fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowUsersActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void OnDeleteClickListener(Registration user) {
        userViewModel.delete(user);
    }
}
