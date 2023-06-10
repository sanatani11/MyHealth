package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTest extends AppCompatActivity {

    private String[][] packages = {
                    {"Package 1: Full Body Checkup","","","","999"},
                    {"Package 2: Blood Glucose Fasting","","","","499"},
                    {"Package 3: COVID-19 Antibody-IgG","","","","899"},
                    {"Package 4: Thyroid Check","","","","899"},
                    {"Package 5 Immunity Check","","","","299"}
    };
    private String[] package_details ={
        "Blood Glucose Fasting\n"+
        "Complete Hemogram\n",
        "HbA1c\n"+
        "Iron Studies \n" +
        "Kidney Function Test\n"+
        "LDH Lactate Dehydrogenase, Serum\n"+
        "Lipid Profile\n"+
        "Liver Function Test",
        "Blood Glucose Fasting",
        "COVID-19 Antibody - IgG",
        "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)","Complete Hemogram\n"+
        "CRP (C Reactive Protein) Quantitative, Serum\n"+"Iron Studies\n"+
        "Kidney Function Test\n"+
        "Vitamin D Total-25 Hydroxy\n"+
        "Liver Function Test\n"+
        "Lipid Profile"
    };
    ArrayList arrayList;
    HashMap hashMap;
    SimpleAdapter simpleAdapter;
    Button btnLtGoToCart, btnLtBack;
    ListView lvLabTest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnLtGoToCart = findViewById(R.id.btnAddToCart);
        btnLtBack = findViewById(R.id.btnBMDGoBack);
        lvLabTest = findViewById(R.id.etLabDetails);
        btnLtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTest.this,HomeActivity.class));
            }
        });
        arrayList = new ArrayList();
        for (int i=0;i<packages.length;i++){
            hashMap = new HashMap<>();
            hashMap.put("line1", packages[i][0]);
            hashMap.put("line2", packages[i][1]);
            hashMap.put("line3", packages[i][2]);
            hashMap.put("line4", packages[i][3]);
            hashMap.put("line5", "Total Cost: "+packages[i][4]+"/");
            arrayList.add(hashMap);
        }
        simpleAdapter = new SimpleAdapter( this, arrayList,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5" },
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
                lvLabTest.setAdapter(simpleAdapter);
        lvLabTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LabTest.this,LabTestDetails.class);
                intent.putExtra("text1",packages[position][0]);
                intent.putExtra("text2",package_details[position]);
                intent.putExtra("text3",packages[position][4]);
                startActivity(intent);

            }
        });
        btnLtGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTest.this,CartLab.class));
            }
        });

    }
}