package com.example.uitestingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.db.entity.Appointment;

import java.util.List;

public class AppointmentRecyclerView extends RecyclerView.Adapter<AppointmentRecyclerView.AppointmentViewHolder> {
    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Appointment appointment);
    }

    private Appointment appointment;
    private List<Appointment> appointmentList;
    private final LayoutInflater layoutInflater;
    private Context mContext;
    private AppointmentRecyclerView.OnDeleteClickListener onDeleteClickListener;


    public AppointmentRecyclerView(Context context, AppointmentRecyclerView.OnDeleteClickListener listener) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(
                R.layout.appointment_items_layout,
                parent,
                false
        );
        return new AppointmentRecyclerView.AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        if (appointmentList != null) {
            Appointment note = appointmentList.get(position);
            holder.setData(note, position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.appointmentTitle.setText(R.string.no_appointment);
        }
    }

    @Override
    public int getItemCount() {
        if (appointmentList != null)
            return appointmentList.size();
        else return 0;
    }

    public void setAppointment(List<Appointment> appointments) {
        appointmentList = appointments;
        notifyDataSetChanged();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private TextView appointmentTitle;
        private TextView doctorName;
        private TextView appointmentDate;
        private int mPosition;
        ImageView delete;

        AppointmentViewHolder(View itemView) {
            super(itemView);
            appointmentTitle = itemView.findViewById(R.id.show_appointment_title);
            doctorName = itemView.findViewById(R.id.show_doctor_name);
            appointmentDate = itemView.findViewById(R.id.show_appointment_date);
            delete 	 = itemView.findViewById(R.id.btn_delete);
        }

        void setData(Appointment appointment, int position) {
            mPosition = position;
            appointmentTitle.setText(appointment.getAppointmentTitle());
            doctorName.setText(appointment.getDoctorName());
            appointmentDate.setText(appointment.getDateAndTime());
        }

        void setListeners() {

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(appointmentList.get(mPosition));
                    }
                }
            });
        }
    }
}
