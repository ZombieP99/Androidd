package com.example.yousef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private TextView textUserName;
    private ImageView btnLogout;
    private LinearLayout cardBookAppointment, cardViewRecords, cardFindDoctor, cardEmergency;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("HealthcareApp", MODE_PRIVATE);

        // Check if user is logged in
        if (!sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToLogin();
            return;
        }

        // Initialize views
        initializeViews();
        setupClickListeners();
        loadUserData();
    }

    private void initializeViews() {
        textUserName = findViewById(R.id.textUserName);
        btnLogout = findViewById(R.id.btnLogout);
        cardBookAppointment = findViewById(R.id.cardBookAppointment);
        cardViewRecords = findViewById(R.id.cardViewRecords);
        cardFindDoctor = findViewById(R.id.cardFindDoctor);
        cardEmergency = findViewById(R.id.cardEmergency);
    }

    private void setupClickListeners() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        cardBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Book Appointment feature coming soon!", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to appointment booking activity
            }
        });

        cardViewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Medical Records feature coming soon!", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to medical records activity
            }
        });

        cardFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Find Doctor feature coming soon!", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to find doctor activity
            }
        });

        cardEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmergencyDialog();
            }
        });
    }

    private void loadUserData() {
        String userEmail = sharedPreferences.getString("userEmail", "");
        if (!userEmail.isEmpty()) {
            // Extract name from email or use stored name
            String userName = sharedPreferences.getString("user_" + userEmail, "");
            if (userName.isEmpty()) {
                // Extract name from email (part before @)
                userName = userEmail.substring(0, userEmail.indexOf("@"));
                userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
            }
            textUserName.setText(userName);
        }
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    performLogout();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void showEmergencyDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Emergency Call")
                .setMessage("Do you want to call emergency services?")
                .setPositiveButton("Call 911", (dialog, which) -> {
                    makeEmergencyCall();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void performLogout() {
        // Clear login state but keep user data for future logins
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.remove("userEmail");
        editor.apply();

        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        navigateToLogin();
    }

    private void makeEmergencyCall() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:911"));
            startActivity(callIntent);
        } catch (SecurityException e) {
            // If permission is not granted, use dial intent
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:911"));
            startActivity(dialIntent);
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Show exit confirmation dialog
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    finishAffinity();
                })
                .setNegativeButton("No", null)
                .show();
    }
}