package com.example.mujahideen1995.deadline_reminder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mujahideen1995.deadline_reminder.R;
import com.example.mujahideen1995.deadline_reminder.model.Reminder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by mujahideen1995 on 10/11/17.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {


    private List<Reminder> reminderList = new ArrayList<>();

    public ReminderAdapter(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }

    @Override
    public ReminderAdapter.ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_home, parent, false);
        ReminderViewHolder reminderViewHolder = new ReminderViewHolder(view);
        return reminderViewHolder;
    }

    @Override
    public void onBindViewHolder(ReminderAdapter.ReminderViewHolder holder, int position) {
        holder.txt_resulttitle.setText(reminderList.get(position).getTitle());
        holder.txt_resulttime.setText(reminderList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public static class ReminderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_resulttime) TextView txt_resulttime;
        @BindView(R.id.txt_resulttitle) TextView txt_resulttitle;

        public ReminderViewHolder(View itemView) {
            super(itemView);


        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}

