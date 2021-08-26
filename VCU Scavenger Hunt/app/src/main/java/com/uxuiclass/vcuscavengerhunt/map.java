package com.uxuiclass.vcuscavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * This class shows a map of the campus to help the user on their quest.
 */
public class map extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button backBtn;
    private Button homeBtn;
    private ImageView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initiating everything that can be seen on screen
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);
        map = findViewById(R.id.map);

        // No need to show home button if we are coming from the home screen
        if(getIntent().getStringExtra("from") != null && getIntent().getStringExtra("from").equals("MainActivity")) {
            homeBtn.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * This method sends the user back to wherever they were before
     *
     * @param view - this current state of the app
     */
    public void onClickBack(View view) {
        Intent goBack = new Intent();

        goBack.putExtra("goHome", false);

        setResult(RESULT_OK, goBack);

        finish();
    }

    /**
     * This method sends the user back to wherever they were before
     *
     * @param view - this current state of the app
     */
    public void onClickHome(View view) {
        Intent goHome = new Intent();

        goHome.putExtra("goHome", true);

        setResult(RESULT_OK, goHome);

        finish();
    }
}
