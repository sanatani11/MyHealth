package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {
    EditText etAppointmentAddress, etAppointmentName, etAppointmentFees, etAppointmentContact;
    TextView tvAppointmentTitle;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button btnBookAppointment, btnAppDate,btnAppTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        etAppointmentContact = findViewById(R.id.etLabBookPinCode);
        etAppointmentAddress = findViewById(R.id.etLabBookAddress);
        etAppointmentFees = findViewById(R.id.etLabBookContact);
        etAppointmentName = findViewById(R.id.etLabBookFullName);
        tvAppointmentTitle = findViewById(R.id.tvAppointmentTitle);
        btnBookAppointment = findViewById(R.id.btnBookLabBook);
        btnAppDate = findViewById(R.id.btnCartDate);
        btnAppTime = findViewById(R.id.btnCartTime);
        etAppointmentName.setKeyListener(null);
        etAppointmentAddress.setKeyListener(null);
        etAppointmentContact.setKeyListener(null);
        etAppointmentFees.setKeyListener(null);

        Intent intent = getIntent();
        tvAppointmentTitle.setText(intent.getStringExtra("text1"));
        etAppointmentName.setText(intent.getStringExtra("text2"));
        etAppointmentAddress.setText(intent.getStringExtra("text3"));
        etAppointmentContact.setText(intent.getStringExtra("text4"));
        etAppointmentFees.setText("Cons Fee:"+intent.getStringExtra("text5")+"/-");
        initDatePicker();
        btnAppDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();

        btnAppTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database database = new Database(getApplicationContext(), "MyHealth", null, 1);

                if(database.checkAppointment(username,tvAppointmentTitle+"=>"+etAppointmentName,etAppointmentAddress.getText().toString(),etAppointmentContact.getText().toString(),btnAppDate.getText().toString(),btnAppTime.getText().toString())==1){
                    Toast.makeText(getApplicationContext(), "Appointment Already booked!", Toast.LENGTH_SHORT).show();
                }
                else{
                    database.addOrder(username,etAppointmentName.getText().toString(),etAppointmentAddress.getText().toString(),etAppointmentContact.getText().toString(),0,btnAppDate.getText().toString(),btnAppTime.getText().toString(),Float.parseFloat(intent.getStringExtra("text5")),"appointment");
                    Toast.makeText(getApplicationContext(), "Appointment is successfully booked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointment.this, HomeActivity.class));
                    finishAffinity();
                }

            }
        });


    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btnAppDate.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                btnAppTime.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get((Calendar.HOUR));
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;

        timePickerDialog = new TimePickerDialog(this, style, timeSetListener,hrs,mins,true);
    }
}