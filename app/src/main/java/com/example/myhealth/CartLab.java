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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CartLab extends AppCompatActivity {
    ArrayList arrayList;
    HashMap hashMap ;
    SimpleAdapter simpleAdapter;
    ListView lvCart;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button btnCartDate,btnCartTime,btnCartCheckOut, btnCartBack;
    TextView tvCartTotalCost;
    private String[][] packages= {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);
        //btnCartBack = findViewById(R.id.btnBMDGoBack);
        btnCartDate = findViewById(R.id.btnCartDate);
        btnCartTime = findViewById(R.id.btnCartTime);
        btnCartCheckOut = findViewById(R.id.btnAddToCart);
        tvCartTotalCost = findViewById(R.id.tvCartTotalCost);
        lvCart = findViewById(R.id.etLabDetails);

        SharedPreferences sharedPreferences = getSharedPreferences("share prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database database = new Database(getApplicationContext(),"MyHealth",null,1);

        float totalAmount = 0;
        ArrayList dataBaseData = database.getCartData(username,"lab");
//        Toast.makeText(getApplicationContext(), ""+dataBaseData, Toast.LENGTH_SHORT).show();

        packages = new String[dataBaseData.size()][];
        for(int i = 0; i < packages.length; i++){
            packages[i] = new String[5];
        }
        for(int i = 0; i < dataBaseData.size();i++){
            String arrayData = dataBaseData.get(i).toString();
            String[] stringData = arrayData.split(Pattern.quote("$"));
            packages[i][0] = stringData[0];
            packages[i][4] = "Cost: "+stringData[1]+"/-";
            totalAmount = totalAmount+ Float.parseFloat(stringData[1]);
        }
        tvCartTotalCost.setText("Total Cost: "+totalAmount+"/-");
        arrayList = new ArrayList();
        for (int i=0;i<packages.length; i++){ 
            hashMap= new HashMap<String, String>(); 
            hashMap.put("line1", packages[i][0]); 
            hashMap.put("line2", packages[i][1]); 
            hashMap.put("line3", packages[i][2]); 
            hashMap.put("line4", packages[i][3]);
            hashMap.put("line5", packages[i][4]);
            arrayList.add(hashMap);
        }
        simpleAdapter= new SimpleAdapter(this, arrayList, R.layout.multi_lines,
        new String[] {"line1", "line2", "line3", "line4", "line5" },
            new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lvCart.setAdapter(simpleAdapter);
//        btnCartBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CartLab.this,LabTest.class));
//                finishAffinity();
//            }
//        });
        btnCartCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartLab.this,LabTestBook.class);
                intent.putExtra("price",tvCartTotalCost.getText());
                intent.putExtra("date",btnCartDate.getText());
                intent.putExtra("time",btnCartTime.getText());
                startActivity(intent);

            }
        });
        initDatePicker();
        btnCartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();

        btnCartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btnCartDate.setText(dayOfMonth+"/"+month+"/"+year);
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
                btnCartTime.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get((Calendar.HOUR));
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;

        timePickerDialog = new TimePickerDialog(this, style, timeSetListener,hrs,mins,true);
    }
}