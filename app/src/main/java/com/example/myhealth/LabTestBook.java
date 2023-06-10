package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBook extends AppCompatActivity {
 EditText etLabBookFullName, etLabBookAddress, etLabBookPinCode, etLabBookContact;
 Button btnBookLabBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        etLabBookFullName = findViewById(R.id.etLabBookFullName);
        etLabBookAddress = findViewById(R.id.etLabBookAddress);
        etLabBookPinCode = findViewById(R.id.etLabBookPinCode);
        etLabBookContact = findViewById(R.id.etLabBookContact);
        btnBookLabBook = findViewById(R.id.btnBookLabBook);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBookLabBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database database = new Database(getApplicationContext(), "MyHealth", null, 1);
                database.addOrder(username,etLabBookFullName.getText().toString(),etLabBookAddress.getText().toString(),etLabBookContact.getText().toString(),Integer.parseInt(etLabBookPinCode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].replace("/-","")),"lab");
                database.removeCart(username,"lab");
                Toast.makeText(LabTestBook.this, "Booking successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBook.this,HomeActivity.class));
            }
        });
    }
}