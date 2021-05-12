package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/** 
 * This screen allows users to add their name to the game.
 */
public class addPlayerNames extends addingNames {

    private Button plyButton;
    private EditText player1;
    private EditText player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player_names);
        plyButton = findViewById(R.id.plyButton);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);

        // Get the game mode
        Intent mIntent = getIntent();
        setGameMode(mIntent.getIntExtra("gameMode", -1));

    }

    /**
     * Keeps the app from crashing when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        readyToGo(null);
    }

    /**
     * This method allows players to enter their names
     *
     * @param view
     *          Current view at button press
     */
    public void readyToGo(View view){

        String string1 = player1.getText().toString();
        String string2 = player2.getText().toString();

        readyToGoBack(string1, string2, 2);

    }
}
