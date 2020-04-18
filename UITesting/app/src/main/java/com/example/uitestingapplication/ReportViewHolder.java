package com.example.uitestingapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReportViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView filename,description;
    Button btn;
    

    public ReportViewHolder(@NonNull View itemView) {
        super(itemView);
        filename = itemView.findViewById(R.id.filename);
        description = itemView.findViewById(R.id.description);
        imageView = itemView.findViewById(R.id.set_image);
        btn = itemView.findViewById(R.id.btn_delete);
    }
}
