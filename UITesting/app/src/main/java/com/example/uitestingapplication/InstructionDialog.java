package com.example.uitestingapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class InstructionDialog extends DialogFragment {

    private List<String> selectedItems;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            selectedItems = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Instructions for taking Medicines");
        builder.setMultiChoiceItems(R.array.instructions, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String[] items=getActivity().getResources().getStringArray(R.array.instructions);

                 if (isChecked){
                     selectedItems.add(items[which]);
                 }
                 else if(selectedItems.contains(items[which])){
                     selectedItems.remove(items[which]);
                 }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String final_selecion = "";
                for (String Item : selectedItems)
                {
                    final_selecion = final_selecion+"\n"+Item;
                }
                Toast.makeText(getActivity(),final_selecion,Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
