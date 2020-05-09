package com.example.uitestingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.db.entity.Medicine;
import com.example.uitestingapplication.db.service.MedicineViewModel;

import java.util.List;

public class ShowMedicineActivity extends AppCompatActivity implements MedicineRecyclerView.OnDeleteClickListener{


    RecyclerView recyclerView;
    MedicineRecyclerView medicineRecyclerView;
    MedicineViewModel medicineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_medicine);
        recyclerView = findViewById(R.id.recyclerviewMedicines);
        medicineRecyclerView = new MedicineRecyclerView(this, this);
        recyclerView.setAdapter(medicineRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicineViewModel = ViewModelProviders.of(this).get(MedicineViewModel.class);
        medicineViewModel.getMedicines().observe(this, new Observer<List<Medicine>>() {
            @Override
            public void onChanged(List<Medicine> medicines) {
                medicineRecyclerView.setMedicine(medicines);
            }
        });
        ImageView fab = findViewById(R.id.add_medicine);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowMedicineActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void OnDeleteClickListener(Medicine medicine) {
        medicineViewModel.delete(medicine);
    }
}
