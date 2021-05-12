package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/**
 * This page gives the user the instructions on how to play the game.
 */
public class instructions extends AppCompatActivity {

    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        // Get the game mode
        Intent mIntent = getIntent();
        setGameMode(mIntent.getIntExtra("gameMode", -1));
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
     * Keeps the app from crashing when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        goBackHome(null);
    }

    /**
     * This method takes the screen back to start game screen
     *
     * @param view
     *          Current view at button press
     */
    public void goBackHome(View view) {
        Intent goBack = new Intent();

        goBack.putExtra("gameMode", gameMode);

        setResult(RESULT_OK, goBack);
        finish();
    }
}
