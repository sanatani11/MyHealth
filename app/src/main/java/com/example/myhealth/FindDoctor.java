package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView cardFDBack = findViewById(R.id.cardFDBack);
        cardFDBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctor.this,HomeActivity.class));
                finishAffinity();
            }
        });
        CardView cardFamilyPhysician = findViewById(R.id.cardFamilyPhysician);
        cardFamilyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title", "Family"+ " Physicians");
                startActivity(intent);

            }
        });
        CardView cardDietician = findViewById(R.id.cardDietician);
        cardDietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctor.this,DoctorDetails.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);

            }
        });
        CardView cardDentist = findViewById(R.id.cardDentist);
        cardDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title", "Dentist");
                startActivity(intent);

            }
        });
        CardView cardSurgeon = findViewById(R.id.cardSurgeon);
        cardSurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title", "Surgeon");
                startActivity(intent);

            }
        });
        CardView cardCardiologists = findViewById(R.id.cardCardiologists);
        cardCardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title", "Cardiologists");
                startActivity(intent);

            }
        });
    }
}