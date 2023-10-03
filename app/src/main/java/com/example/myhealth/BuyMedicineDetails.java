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

public class BuyMedicineDetails extends AppCompatActivity {
    TextView tvBMDPackageName,tvBMDTotalCost;
    EditText etBMDMultiLines;
    Button btnBMDAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        tvBMDPackageName = findViewById(R.id.tvBMDPackageName);
        tvBMDTotalCost = findViewById(R.id.tvBMDTotalCost);
        etBMDMultiLines = findViewById(R.id.etBMDMultiLines);
        btnBMDAddToCart = findViewById(R.id.btnBMDAddToCart);
        etBMDMultiLines.setKeyListener(null);

        Intent intent = getIntent();
        tvBMDPackageName.setText(intent.getStringExtra("text1"));
        etBMDMultiLines.setText(intent.getStringExtra("text2"));
        tvBMDTotalCost.setText("Total cost: "+intent.getStringExtra("text3")+"/-");


        btnBMDAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvBMDPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database database = new Database(getApplicationContext(), "MyHealth", null, 1);
                if(database.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already added!", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.addCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetails.this, "Report inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetails.this, BuyMedicine.class));
                    finish();
                }

            }
        });
    }
}