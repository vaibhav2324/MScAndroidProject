//package com.example.uitestingapplication;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.DialogFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class InstructionDialog extends DialogFragment {
//    public interface onMultiChoiceListener{
//    void onPositiveButtonClick(String[] items,String selectedItems);
//    void onNegativeButtonClick();
//}
//
//    onMultiChoiceListener listener;
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        try {
//            listener = (onMultiChoiceListener) context;
//        } catch (Exception e) {
//            throw new ClassCastException(getActivity().toString()+"onMultiChoiceListener must be implemented");
//        }
//    }
//    //private String[] items= (getActivity()).getResources().getStringArray(R.array.instructions);
//    private List<String> selectedItems;
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//            selectedItems = new ArrayList<>();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Instructions for taking Medicines");
//        builder.setMultiChoiceItems(R.array.instructions, null, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//
//
//                 if (isChecked){
//                     selectedItems.add(items[which]);
//                 }
//                 else selectedItems.remove(items[which]);
//            }
//        });
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String final_selection = "";
//                for (String Item : selectedItems)
//                {
//                    final_selection = final_selection+Item+",";
//                }
////                medicine.setInstruction(final_selection);
//                Toast.makeText(getActivity(),final_selection,Toast.LENGTH_SHORT).show();
//
//                listener.onPositiveButtonClick(items, final_selection);
//            }
//        });
//
//
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                listener.onNegativeButtonClick();
//            }
//        });
//        return builder.create();
//    }
//
//
//}
