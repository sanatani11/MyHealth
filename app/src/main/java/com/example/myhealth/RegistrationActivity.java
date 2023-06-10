package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    EditText etRegUsername, etRegEmail, etRegPassword, etPasswordConfirm;
    TextView tvExistingUser;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etRegUsername = findViewById(R.id.etLabBookFullName);
        etRegEmail = findViewById(R.id.etLabBookAddress);
        etRegPassword = findViewById(R.id.etLabBookPinCode);
        etPasswordConfirm = findViewById(R.id.etLabBookContact);
        tvExistingUser = findViewById(R.id.tvExistingUser);
        btnRegister = findViewById(R.id.btnBookLabBook);

        tvExistingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etRegUsername.getText().toString().trim();
                String email = etRegEmail.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();
                String confirm = etPasswordConfirm.getText().toString().trim();
                Database database = new Database(getApplicationContext(),"MyHealth",null, 1);
                if(username.length()==0||email.length()==0||password.length()==0||confirm.length()==0){
                    Toast.makeText(RegistrationActivity.this, "Please fill all details!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            database.register(username,email,password);
                            Toast.makeText(RegistrationActivity.this, "Registration completed.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                        }
                        else
                            Toast.makeText(RegistrationActivity.this, "Password must contain at least 8 characters, having letter, digit and special symbol!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Password and Confirm Password didn't match!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public static boolean isValid(String isPassword){
        int k = 0, m = 0, n = 0;
        if(isPassword.length()<8) return false;
        else {
            for(int p = 0; p < isPassword.length(); p++){
                if(Character.isLetter(isPassword.charAt(p))) k = 1;
            }
            for (int r = 0; r < isPassword.length(); r++){
                if(Character.isDigit(isPassword.charAt(r))) m = 1;
            }
            for(int s = 0; s< isPassword.length(); s++){
                char c = isPassword.charAt(s);
                if(c>=33 && c<=44 || c==64) n = 1;
            }
            if(k==1&&m==1&&n==1) return true;

            return false;
        }
    }
}