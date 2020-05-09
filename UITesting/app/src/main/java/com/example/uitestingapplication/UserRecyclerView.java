package com.example.uitestingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitestingapplication.db.entity.Registration;

import java.util.List;

public class UserRecyclerView extends RecyclerView.Adapter<UserRecyclerView.UserViewHolder> {

    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Registration registration);
    }
    private Registration registration;
    private List<Registration> registrationList;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private OnDeleteClickListener onDeleteClickListener;

    UserRecyclerView(Context context, UserRecyclerView.OnDeleteClickListener listener){
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public UserRecyclerView.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(
                R.layout.user_items_layout,
                parent,
                false
        );
        return new UserRecyclerView.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerView.UserViewHolder holder, int position) {

        if (registrationList != null) {
            Registration registration = registrationList.get(position);
            holder.setData(registration, position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.user_name.setText(R.string.no_users);
        }
    }

    @Override
    public int getItemCount() {
        if (registrationList != null)
            return registrationList.size();
        else return 0;
    }

    public void setUsers(List<Registration> registrations){
        registrationList = registrations;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView user_name;
        private TextView user_age;
        private TextView user_gender;
        private ImageView image;
        private int mPosition;
        ImageView delete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.set_user_name);
            user_age = itemView.findViewById(R.id.set_user_age);
            user_gender = itemView.findViewById(R.id.set_user_gender);
            delete 	 = itemView.findViewById(R.id.delete_user);
        }

        void setData(Registration registration, int position) {
            mPosition = position;
            user_name.setText(registration.getEmail());
            user_age.setText(Integer.toString(registration.getAge()));
            user_gender.setText(registration.getGender());
//          image.setImageBitmap(ImageConverter.convertByteArrayToImage(registration.getImage()));
        }
        void setListeners() {

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(registrationList.get(mPosition));
                    }
                }
            });
        }
    }
}
