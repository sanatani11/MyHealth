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

public class BuyMedicineBook extends AppCompatActivity {
    EditText etBBMFullName, etBBMAddress, etBBMPinCode, etBBMContact;
    Button btnBBMBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);
        etBBMFullName = findViewById(R.id.etBMBFullName);
        etBBMAddress = findViewById(R.id.etBBMAddress);
        etBBMPinCode = findViewById(R.id.etBBMPinCode);
        etBBMContact = findViewById(R.id.etBBMContact);
        btnBBMBook = findViewById(R.id.btnBBMBook);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        btnBBMBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                Database database = new Database(getApplicationContext(), "MyHealth", null, 1);
                database.addOrder(username,etBBMFullName.getText().toString(),etBBMAddress.getText().toString(),etBBMContact.getText().toString(),Integer.parseInt(etBBMPinCode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].replace("/-","")),"medicine");
                database.removeCart(username,"medicine");
                Toast.makeText(BuyMedicineBook.this, "Booking successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBook.this,HomeActivity.class));
            }
        });
    }
}