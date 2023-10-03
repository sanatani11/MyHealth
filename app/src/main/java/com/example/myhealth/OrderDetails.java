package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetails extends AppCompatActivity {
    private String [][] order_details = {};

    HashMap hashMap;
    ArrayList arrayList;
    SimpleAdapter simpleAdapter;
    ListView lvOrderDetails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        lvOrderDetails = findViewById(R.id.etLabDetails);

        SharedPreferences sharedPreferences = getSharedPreferences("share prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Database database = new Database(getApplicationContext(), "MyHealth", null, 1);
        ArrayList dbData = database.getOrderData(username);

        order_details = new String[dbData.size()][];
        for (int i=0;i<order_details.length;i++){
            order_details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$")); order_details[i][0] = strData[0];
            order_details[i][1] = strData[1]; //+" "+strData[3];
            if(strData[7].compareTo("medicine")==0){
                order_details[i][3] = "Del: "+strData[4];
            }else {
                order_details[i][3] = "Del:" + strData[4] +strData[5];
            }
            order_details[i][2] = "Rs. "+strData[6];
            order_details[i][4] = strData[7];

        }
        arrayList = new ArrayList();
        for(int i=0; i < order_details.length; i++){
            hashMap = new HashMap<>();
            hashMap.put("line1",order_details[i][0]);
            hashMap.put("line2",order_details[i][1]);
            hashMap.put("line3",order_details[i][2]);
            hashMap.put("line4",order_details[i][3]);
            hashMap.put("line5",order_details[i][4]);
            arrayList.add(hashMap);

        }
        simpleAdapter = new SimpleAdapter(this,arrayList,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lvOrderDetails.setAdapter(simpleAdapter);
    }
}