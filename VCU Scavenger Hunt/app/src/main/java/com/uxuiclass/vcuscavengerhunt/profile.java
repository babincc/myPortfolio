package com.uxuiclass.vcuscavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class is the user's profile page. They can use it to view and change their personal
 * information.
 */
public class profile extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button backBtn;
    private TextView usernameTextView;
    private Button editBtn;
    private TextView numCoinsTextView;

    // User information
    private String userName;
    private int numCoins;

    // Keeps track of user information after app is closed
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_COINS = "coins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Getting ready to store user info
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initiating everything that can be seen on screen
        backBtn = findViewById(R.id.backBtn);
        usernameTextView = findViewById(R.id.usernameTextView);
        editBtn = findViewById(R.id.editBtn);
        numCoinsTextView = findViewById(R.id.numCoinsTextView);

        // Grabbing the user information and setting it
        userName = sharedPreferences.getString(KEY_USERNAME, "No_name_entered");
        numCoins = sharedPreferences.getInt(KEY_COINS, -1);

        // Show user's information on screen for them
        displayUserInfo();
    }

    /**
     * This method displays the user info on the screen.
     */
    private void displayUserInfo() {
        String tempUsername = "Username: " + userName;
        usernameTextView.setText(tempUsername);

        String tempCoins = "Coins: " + numCoins;
        numCoinsTextView.setText(tempCoins);
    }

    /**
     * This method sends the user to the addUserName page to change their username.
     *
     * @param view - this current state of the app
     */
    public void onClickEditUsername(View view) {
        Intent editUsernameIntent = new Intent(this, addUsername.class);

        editUsernameIntent.putExtra("from", "profile");

        final int result = 729;

        startActivityForResult(editUsernameIntent, result);
    }

    /**
     * This method sends the user back to whatever class called this one.
     *
     * @param view - this current state of the app
     */
    public void onClickBack(View view) {
        Intent goBack = new Intent();

        goBack.putExtra("username", userName);

        setResult(RESULT_OK, goBack);

        finish();
    }

    /**
     * This method handles returns from other pages.
     *
     * @param requestCode   - To identify what was just called and returned
     * @param resultCode    - Tells if everything went okay
     * @param data          - The information sent along from the other activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 729) { // Returning from editing the username

                // Check to see if the username changed
                String returnedUserName = data.getStringExtra("username").trim();
                if(!userName.equals(returnedUserName)) {

                    // Set the new username
                    userName = returnedUserName;

                    // Put new username in shared memory
                    editor.putString(KEY_USERNAME, returnedUserName);
                    editor.apply();

                    // Display the new username
                    String tempUserName = "Username: " + returnedUserName;
                    usernameTextView.setText(tempUserName);
                }
            }
        }
    }

}
