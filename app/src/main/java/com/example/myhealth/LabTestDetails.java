package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetails extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText etLabDetails;
    Button btnAddToCart, btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackageName = findViewById(R.id.tvPackageName);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        etLabDetails = findViewById(R.id.etLabDetails);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnGoBack = findViewById(R.id.btnBMDGoBack);
        etLabDetails.setKeyListener(null);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        etLabDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total cost: "+intent.getStringExtra("text3")+"/-");

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetails.this,LabTest.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database database = new Database(getApplicationContext(), "MyHealth", null, 1);
                if(database.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already added!", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.addCart(username,product,price,"lab");
                    Toast.makeText(LabTestDetails.this, "Report inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetails.this, LabTest.class));
                }

            }
        });


    }
}