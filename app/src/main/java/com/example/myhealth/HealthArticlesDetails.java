package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDetails extends AppCompatActivity {
    TextView tvHADTitle;
    ImageView ivHAD;
    Button btnBackHAD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);

        tvHADTitle = findViewById(R.id.tvHADTitle);
        ivHAD = findViewById(R.id.ivHAD);
        btnBackHAD = findViewById(R.id.btnBackHAD);

        Intent intent = getIntent();
        tvHADTitle.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int resId = bundle.getInt("text2");
            ivHAD.setImageResource(resId);
        }
        btnBackHAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesDetails.this,HealthArticles.class));
            }
        });
    }
}