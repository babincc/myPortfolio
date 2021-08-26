package com.uxuiclass.vcuscavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This page allows the user to add or change their username.
 */
public class addUsername extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button backBtn;
    private EditText usernameEditText;
    private TextView charCount;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_username);

        // Initiating everything that can be seen on screen
        backBtn = findViewById(R.id.backBtn);
        usernameEditText = findViewById(R.id.usernameEditText);
        charCount = findViewById(R.id.charCount);
        submitBtn = findViewById(R.id.submitBtn);

        // Only allow back button to show if we are coming from the profile page
        if(getIntent().getStringExtra("from").equals("profile")) {
            backBtn.setVisibility(View.VISIBLE);
        }

        // If the username textbox had a change made by the user, this will see it and react
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // It will only let the submit button be visible if there are letters in the textbox
                int userLen = 10 - s.length();
                String userLenStr = "" + userLen;
                charCount.setText(userLenStr);
                if(s.length() != 0 && s.toString().matches("[^\\s]+.*")) {
                    submitBtn.setVisibility(View.VISIBLE);
                } else {
                    submitBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * This method sends the user and their NEW information back to wherever they were before
     *
     * @param view - this current state of the app
     */
    public void onClickSubmit(View view) {
        Intent goBack = new Intent();

        String username = usernameEditText.getText().toString();

        goBack.putExtra("username", username);

        setResult(RESULT_OK, goBack);

        finish();
    }

    /**
     * This method sends the user and their OLD information back to wherever they were before
     *
     * @param view - this current state of the app
     */
    public void onClickBack(View view) {
        Intent goBack = new Intent();

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "No_name_entered");

        goBack.putExtra("username", username);

        setResult(RESULT_OK, goBack);

        finish();
    }

    /**
     * This method sends the user to the info/help page to find out more about the what a username
     * is for.
     *
     * @param view - this current state of the app
     */
    public void onClickInfo(View view) {
        Intent infoIntent = new Intent(this, Info.class);

        infoIntent.putExtra("from", "addUsername");

        final int result = 20;

        startActivityForResult(infoIntent, result);
    }
}
