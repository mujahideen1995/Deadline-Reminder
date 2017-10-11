package com.example.mujahideen1995.deadline_reminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Build;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mujahideen1995.deadline_reminder.adapter.ReminderAdapter;
import com.example.mujahideen1995.deadline_reminder.helper.DBHandler;
import com.example.mujahideen1995.deadline_reminder.model.Reminder;

import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReminder extends AppCompatActivity {

    protected Cursor cursor;
    //DataHelper dbHelper;

    @BindView(R.id.txt_title) TextView txt_title;

    @BindView(R.id.txtdate) EditText txtdate;
    @BindView(R.id.txttime) EditText txtime;
    @BindView(R.id.SaveButton) Button SaveButton;
    @BindView(R.id.text_description) EditText text_description;

    private DBHandler dbHandler;
    private ReminderAdapter adapter;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_reminder);

        dbHandler = new DBHandler(this);
        initComponents();

        ButterKnife.bind(this);
        myCalendar = Calendar.getInstance();

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddReminder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        txtdate.setText(sdf.format(myCalendar.getTime()));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
                       txtime.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Calendar mcurrentTime = Calendar.getInstance();
                               int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                               int minute = mcurrentTime.get(Calendar.MINUTE);
                               TimePickerDialog mTimePicker;

                               mTimePicker = new TimePickerDialog(AddReminder.this, new TimePickerDialog.OnTimeSetListener() {
                                   @Override
                                   public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute) {
                                       txtime.setText(selectHour + ":" + selectMinute);
                                   }
                               }, hour, minute, true);
                               mTimePicker.setTitle("Select Time");
                               mTimePicker.show();
                           }
                       });


    }

    private void initComponents(){

        SaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            validasiForm();

            }
                                      });
    }

    private void validasiForm(){

        String form_title = txt_title.getText().toString();
        String form_description = text_description.getText().toString();
        String form_date = txtdate.getText().toString();
        String form_time = txtime.getText().toString();

        if (form_title.isEmpty()){
            txt_title.setError("Please Input Your Reminder First !");
            txt_title.requestFocus();
        } if (form_date.isEmpty()){
            txtdate.setError("Date cannot be empty !");
            txtdate.requestFocus();
        } if (form_description.isEmpty()){
            text_description.setError("Please input the description");
            text_description.requestFocus();
        } if (form_time.isEmpty()){
            txtime.setError("Time cannot be Empty !");
            txtime.requestFocus();
        }

        else {
            dbHandler.tambahReminder(new Reminder(form_title, form_description, form_date, form_time));
            List<Reminder> reminderList = dbHandler.getSemuaReminder();
            adapter = new ReminderAdapter(reminderList);
            adapter.notifyDataSetChanged();

            Toast.makeText(AddReminder.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
        }
    }
}