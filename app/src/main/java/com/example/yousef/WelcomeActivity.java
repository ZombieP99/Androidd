package com.example.yousef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnGetStarted;
    private TextView btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Hide the action bar for full screen experience
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        btnGetStarted = findViewById(R.id.btnGetStarted);
        btnSkip = findViewById(R.id.btnSkip);

        // Set click listeners
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoginActivity();
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoginActivity();
            }
        });
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Close welcome activity
    }

    @Override
    public void onBackPressed() {
        // Disable back button on welcome screen
        // User must use Get Started or Skip
    }
}