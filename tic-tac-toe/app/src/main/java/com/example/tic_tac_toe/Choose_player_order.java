package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 * This screen allows users to choose the order in which they play. They
 * can pick who goes first.
 */
public class Choose_player_order extends AppCompatActivity {

    private Button playerFirst;
    private Button compFirst;
    private int playerToGo;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player_order);

        playerFirst = findViewById(R.id.playerFirst);
        compFirst = findViewById(R.id.compFirst);

        // Get the intent so we can extract the player name data
        Intent myIntent = getIntent();
        setGameMode(myIntent.getIntExtra("gameMode", -1));

    }

    /**
     * This method is sets the player to go first
     *
     * @param view
     *          Current view at button press
     */
    public void playerFirstClick(View view){
        playerToGo = 0;
        readyToGo(null);
    }

    /**
     * This method sets the computer to go first
     *
     * @param view
     *          Current view at button press
     */
    public void computerFirstClick(View view){
        playerToGo = 1;
        readyToGo(null);
    }

    /**
     * Keeps the app from crashing when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent goBack = new Intent();
        goBack.putExtra("gameMode", gameMode);
        goBack.putExtra("playerToGo", -1);
        setResult(RESULT_OK, goBack);
        finish();
    }

    /**
     * This method watches for the secret gesture.
     *
     * @param keyCode
     *          The key that was pressed on the keyboard (case sensitive)
     * @param event
     *          The key that was pressed on the keyboard
     * @return True on successful completion
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_W) {
            int game_mode = getGameMode();
            if(game_mode == 1) {
                setGameMode(0);
            } else if(game_mode == 0) {
                setGameMode(1);
            }
            return true;
        }

        return super.onKeyUp(keyCode, event);

    }

    private int getGameMode() {

        return gameMode;

    }

    private void setGameMode(int gameMode) {

        this.gameMode = gameMode;

    }

    /**
     * This method starts the game
     *
     * @param view
     *          Current view at button press
     */
    public void readyToGo(View view){
        Intent goBack = new Intent();
        goBack.putExtra("playerToGo", playerToGo);
        goBack.putExtra("gameMode", gameMode);
        setResult(RESULT_OK, goBack);
        finish();
    }

}
