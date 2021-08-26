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
 * This class is the home page for the app. it initializes everything and gives the user
 * all of their oprions to get started, change settings, or get help and info.
 */
public class MainActivity extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button infoBtn;
    private TextView welcomeTextView;
    private Button playButton;
    private Button profileBtn;

    // User information
    private String userName;
    private int numCoins;

    // Keeps track of user information after app is closed
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_COINS = "coins";
    private static final String KEY_DB_NUM = "dbNum";

    // The version of the database we are on
    private static final int DB_NUM = 5; // IMPORTANT NOTE: This changes with app updates if database changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting ready to store user info
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initiating everything that can be seen on screen
        infoBtn = findViewById(R.id.infoBtn);
        welcomeTextView = findViewById(R.id.welcomeTextView);
        playButton = findViewById(R.id.playButton);
        profileBtn = findViewById(R.id.profileBtn);

        // Deciding if the app is being opened for the first time
        // If it is, set a username and numCoins; otherwise, use the ones in storage
        if (!sharedPreferences.contains(KEY_USERNAME)) {
            addUsername();
            numCoins = 0;
            editor.putInt(KEY_COINS, 0);
            editor.apply();
        } else {
            userName = sharedPreferences.getString(KEY_USERNAME, "No_name_entered");
            numCoins = sharedPreferences.getInt(KEY_COINS, -1);
            setWelcomeText(userName);
        }

        // If the database has been updated, bring in those updates and mark that we did so in shared memory
        if(!sharedPreferences.contains(KEY_DB_NUM) || sharedPreferences.getInt(KEY_DB_NUM, -1) != DB_NUM) {
            Intent initializeDataBase = new Intent(this, loadingScreen.class);

            final int result = 729;

            startActivityForResult(initializeDataBase, result);
        }

    }

    /**
     * This method sets the text on the welcome/home screen to say the user's name and make it feel
     * more personal.
     *
     * @param userName - The name the user wants to go by
     */
    private void setWelcomeText(String userName) {
        String welcomeText = "Welcome " + userName + "!";
        welcomeTextView.setText(welcomeText);
    }

    /**
     * This method sends the user to the page where they can pick a username.
     */
    private void addUsername() {
        Intent addUsernameIntent = new Intent(this, addUsername.class);

        addUsernameIntent.putExtra("from", "MainActivity");

        final int result = 844;

        startActivityForResult(addUsernameIntent, result);
    }

    /**
     * This method sends the user to the page where they can pick a category to start hunting.
     *
     * @param view - this current state of the app
     */
    public void onClickPlay(View view) {
        Intent playIntent = new Intent(this, Hunt_Categories.class);

        playIntent.putExtra("username", userName);
        playIntent.putExtra("coins", numCoins);

        final int result = 21;

        startActivityForResult(playIntent, result);
    }

    /**
     * This method sends the user to the page where they can view their user information.
     *
     * @param view - this current state of the app
     */
    public void onClickProfile(View view) {
        Intent profileIntent = new Intent(this, profile.class);

        final int result = 656;

        startActivityForResult(profileIntent, result);
    }

    /**
     * This method sends the user to the info/help page to find out more about the app.
     *
     * @param view - this current state of the app
     */
    public void onClickInfo(View view) {
        Intent infoIntent = new Intent(this, Info.class);

        infoIntent.putExtra("from", "MainActivity");

        final int result = 20;

        startActivityForResult(infoIntent, result);
    }

    /**
     * This method sends the user to the map page to see the map.
     *
     * @param view - this current state of the app
     */
    public void onClickMap(View view) {
        Intent mapIntent = new Intent(this, map.class);

        mapIntent.putExtra("from", "MainActivity");

        final int result = 513;

        startActivityForResult(mapIntent, result);
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
            if (requestCode == 844) { // Returning from adding username

                // Get the username and display it
                userName = data.getStringExtra("username").trim();
                setWelcomeText(userName);

                // Store the username into shared memory
                editor.putString(KEY_USERNAME, userName);
                editor.apply();
            } else if (requestCode == 656) { // Returning from profile

                // Check to see if the username changed
                String returnedUserName = data.getStringExtra("username");
                if (!userName.equals(returnedUserName)) {

                    // Display the new username (which was updated in shared memory on the addUsername page)
                    userName = returnedUserName;
                    setWelcomeText(returnedUserName);
                }
            } else if (requestCode == 729) { // Returning from initializing database

                // Let the app know that the database has been updated
                editor.putInt(KEY_DB_NUM, DB_NUM);
                editor.apply();
            }
        }
    }

}
