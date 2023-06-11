package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Support extends AppCompatActivity {
    private Button contactButton;
    private Button feedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        // Initialize contactButton and feedbackButton
        contactButton = findViewById(R.id.contact_button);
        feedbackButton = findViewById(R.id.feedback_button);

        // Set click listener for contactButton
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open email client with predefined email address
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:support@example.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Food App Support");
                startActivity(Intent.createChooser(intent, "Contact Support"));
            }
        });

        // Set click listener for feedbackButton
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open feedback form activity
                Intent intent = new Intent(Support.this, Feedback.class);
                startActivity(intent);
            }
        });
    }
}
