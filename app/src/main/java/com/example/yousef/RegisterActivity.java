package com.example.yousef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText editTextFullName, editTextRegisterEmail, editTextPhone, 
                              editTextRegisterPassword, editTextConfirmPassword;
    private CheckBox checkBoxTerms;
    private Button btnRegister;
    private TextView textSignIn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("HealthcareApp", MODE_PRIVATE);

        // Initialize views
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextRegisterEmail = findViewById(R.id.editTextRegisterEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextRegisterPassword = findViewById(R.id.editTextRegisterPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        btnRegister = findViewById(R.id.btnRegister);
        textSignIn = findViewById(R.id.textSignIn);
    }

    private void setupClickListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });

        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to login activity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void performRegistration() {
        String fullName = editTextFullName.getText().toString().trim();
        String email = editTextRegisterEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextRegisterPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Validation
        if (fullName.isEmpty()) {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextRegisterEmail.setError("Email is required");
            editTextRegisterEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextRegisterEmail.setError("Please enter a valid email");
            editTextRegisterEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() < 10) {
            editTextPhone.setError("Please enter a valid phone number");
            editTextPhone.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextRegisterPassword.setError("Password is required");
            editTextRegisterPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextRegisterPassword.setError("Password must be at least 6 characters");
            editTextRegisterPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Passwords do not match");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!checkBoxTerms.isChecked()) {
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email already exists (demo implementation)
        if (isEmailAlreadyRegistered(email)) {
            editTextRegisterEmail.setError("Email already registered");
            editTextRegisterEmail.requestFocus();
            return;
        }

        // Register user (demo implementation)
        if (registerUser(fullName, email, phone, password)) {
            Toast.makeText(this, "Registration successful! Please login.", Toast.LENGTH_LONG).show();
            
            // Navigate to login activity
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra("registered_email", email);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailAlreadyRegistered(String email) {
        // Demo implementation - check if email exists in SharedPreferences
        return sharedPreferences.contains("user_" + email);
    }

    private boolean registerUser(String fullName, String email, String phone, String password) {
        // Demo implementation - save user data to SharedPreferences
        // In a real app, you would send this data to a server
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user_" + email, fullName);
            editor.putString("phone_" + email, phone);
            editor.putString("password_" + email, password);
            editor.apply();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        // Navigate back to login activity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}