package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    private EditText feedbackEditText; // EditText field for entering feedback
    private Button submitButton; // Button for submitting feedback

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackEditText = findViewById(R.id.feedback_edit_text); // Initialize feedbackEditText with the corresponding view from the layout
        submitButton = findViewById(R.id.submit_button); // Initialize submitButton with the corresponding view from the layout

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered feedback
                String feedback = feedbackEditText.getText().toString().trim();

                // Validate if feedback is not empty
                if (!feedback.isEmpty()) {
                    // Process the feedback (e.g., send to server, save locally)

                    // Show success message
                    Toast.makeText(Feedback.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();

                    // Clear the feedback field
                    feedbackEditText.setText("");
                } else {
                    // Show error message if feedback is empty
                    Toast.makeText(Feedback.this, "Please enter your feedback.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
