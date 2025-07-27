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

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editTextEmail, editTextPassword;
    private CheckBox checkBoxRememberMe;
    private Button btnLogin;
    private TextView textForgotPassword, textSignUp;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("HealthcareApp", MODE_PRIVATE);

        // Check if user is already logged in
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToMainActivity();
            return;
        }

        // Initialize views
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
        btnLogin = findViewById(R.id.btnLogin);
        textForgotPassword = findViewById(R.id.textForgotPassword);
        textSignUp = findViewById(R.id.textSignUp);

        // Load saved email if remember me was checked
        String savedEmail = sharedPreferences.getString("savedEmail", "");
        if (!savedEmail.isEmpty()) {
            editTextEmail.setText(savedEmail);
            checkBoxRememberMe.setChecked(true);
        }
    }

    private void setupClickListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement forgot password functionality
                Toast.makeText(LoginActivity.this, "Forgot password feature coming soon!", Toast.LENGTH_SHORT).show();
            }
        });

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to registration activity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void performLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Basic validation
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        // Demo login - accept any valid email/password combination
        // In a real app, you would authenticate with a server
        if (authenticateUser(email, password)) {
            // Save login state
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.putString("userEmail", email);
            
            // Save email if remember me is checked
            if (checkBoxRememberMe.isChecked()) {
                editor.putString("savedEmail", email);
            } else {
                editor.remove("savedEmail");
            }
            
            editor.apply();

            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            navigateToMainActivity();
        } else {
            Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean authenticateUser(String email, String password) {
        // Demo authentication - accept any email with password length >= 6
        // In a real app, you would validate against a database or API
        return email.contains("@") && password.length() >= 6;
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Exit app when back is pressed on login screen
        finishAffinity();
    }
}