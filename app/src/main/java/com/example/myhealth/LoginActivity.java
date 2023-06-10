package com.example.myhealth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvNewUser;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etLabBookFullName);
        etPassword = findViewById(R.id.etPasswordCnfrm);
        btnLogin = findViewById(R.id.btnBookLabBook);
        tvNewUser = findViewById(R.id.tvExistingUser);

        // Retrieve the shared preferences
        sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);

        // Check if the user is already logged in
        if (isLoggedIn()) {
            proceedToHome();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                Database database = new Database(getApplicationContext(), "MyHealth", null, 1);

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please fill all details!", Toast.LENGTH_SHORT).show();
                } else {
                    if (database.login(username, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                        // Store the logged-in username in SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();

                        proceedToHome();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private boolean isLoggedIn() {
        // Check if the username is present in SharedPreferences
        return sharedPreferences.contains("username");
    }

    private void proceedToHome() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish(); // Optional: Finish the LoginActivity to prevent going back to it with the back button
    }
}
