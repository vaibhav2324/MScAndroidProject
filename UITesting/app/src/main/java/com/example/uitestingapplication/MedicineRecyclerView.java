package com.example.uitestingapplication;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.db.entity.Medicine;

import java.util.List;

public class MedicineRecyclerView extends RecyclerView.Adapter<MedicineRecyclerView.MedicineViewHolder> {
    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Medicine medicine);
    }
    private Medicine medicine;
    private List<Medicine> medicineList;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private MedicineRecyclerView.OnDeleteClickListener onDeleteClickListener;

    MedicineRecyclerView(Context context, MedicineRecyclerView.OnDeleteClickListener listener){
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public MedicineRecyclerView.MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(
                R.layout.medicine_items_layout,
                parent,
                false
        );
        return new MedicineRecyclerView.MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineRecyclerView.MedicineViewHolder holder, int position) {
        if (medicineList != null) {
            Medicine note = medicineList.get(position);
            holder.setData(note, position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.medicine_name.setText(R.string.no_Medicine);
        }
    }

    @Override
    public int getItemCount() {
        if (medicineList != null)
            return medicineList.size();
        else return 0;
    }

    public void setMedicine(List<Medicine> medicines){
        medicineList = medicines;
        notifyDataSetChanged();
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder{
        private TextView medicine_name;
        private ImageView image;
        private TextView date_time;
        private TextView instruction;
        private int mPosition;
        private ImageView delete;

        MedicineViewHolder(View itemView) {
            super(itemView);
            medicine_name = itemView.findViewById(R.id.set_medicine_name);
            date_time = itemView.findViewById(R.id.set_medicine_date);
            instruction = itemView.findViewById(R.id.set_medicine_instruction);
            image = itemView.findViewById(R.id.set_image);
            delete 	 = itemView.findViewById(R.id.btn_delete);
        }

        void setData(Medicine medicine, int position) {
            mPosition = position;
            medicine_name.setText(medicine.getMedicineName());
            date_time.setText(medicine.getDate());
            instruction.setText(medicine.getInstruction());
            image.setImageBitmap(ImageConverter.convertByteArrayToImage(medicine.getMedicineImage()));
        }

        void setListeners() {

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(medicineList.get(mPosition));
                    }
                }
            });
        }
    }

}
