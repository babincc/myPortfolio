package com.uxuiclass.vcuscavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class displays a question that the user has already answered. If the user wants
 * to view an ansered question, this page allows them to do that without having the input
 * box to change their answer.
 */
public class answeredQuestion extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private ImageView gradeImageView;
    private TextView messageTextView;
    private ImageView coinImageView;
    private Button okayBtn;

    // Are used to tell the user if they got the question right or wrong
    String grade;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answered_question);

        // Initiating everything that can be seen on screen
        gradeImageView = findViewById(R.id.gradeImageView);
        messageTextView = findViewById(R.id.messageTextView);
        coinImageView = findViewById(R.id.coinImageView);
        okayBtn = findViewById(R.id.okayBtn);

        // Getting the results in
        grade = getIntent().getStringExtra("grade");
        message = getIntent().getStringExtra("msg");

        // Display the results
        putInfoOnScreen();
    }

    /**
     * This method puts the reults on the screen to let the user know how they did.
     */
    private void putInfoOnScreen() {

        // If the user go the question right, it will show them the coin and "Correct"
        // Otherwise, they don't see the coin, and they see "Incorrect"
        if(grade.equals("correct")){
            coinImageView.setVisibility(View.VISIBLE);
            gradeImageView.setImageResource(R.drawable.correct);

            // For blind people
            gradeImageView.setContentDescription("Correct");
        } else {
            gradeImageView.setImageResource(R.drawable.incorrect);

            // For blind people
            gradeImageView.setContentDescription("Incorrect");
        }

        // The message telling them what it means to be right or wrong
        messageTextView.setText(message);
    }

    // Keeps the back button from breaking the app or allowing cheating
    @Override
    public void onBackPressed() {
        onClickSubmit(null);
    }

    /**
     * This method sends the user back to wherever they were before
     *
     * @param view - this current state of the app
     */
    public void onClickSubmit(View view) {
        Intent goBack = new Intent();

        goBack.putExtra("grade", grade);

        setResult(RESULT_OK, goBack);

        finish();
    }
}
