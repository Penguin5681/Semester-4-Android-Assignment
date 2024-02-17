package com.example.assignedproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    Context context;
    ArrayList<ProfileDataModel> profileList;

    public ProfileAdapter(Context context, ArrayList<ProfileDataModel> profileList) {
        this.context = context;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_row, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        ProfileDataModel profile = profileList.get(position);
        holder.nameTextView.setText(profile.name);
        holder.emailTextView.setText(profile.email);
        holder.specializationTextView.setText(profile.specialization);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, specializationTextView;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textView);
            emailTextView = itemView.findViewById(R.id.email_textView);
            specializationTextView = itemView.findViewById(R.id.specialization_textView);
        }
    }
}
