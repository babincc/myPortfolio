package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This screen is shown whenever a game ends. It will display a message
 * based on who won the game.
 */
public class GameOver extends AppCompatActivity {

    private TextView multiWinner;
    private Button playAgain;
    private Button quit;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Get the intent so we can extract the player name data
        Intent myIntent = getIntent();
        setGameMode(myIntent.getIntExtra("gameMode", -1));

        // Set the winner's name
        String pName = myIntent.getStringExtra("playerName");

        // A string to build a message for the winner
        String winner;
        if (pName == null) {
            throw new IllegalArgumentException("Player name is NULL! Restart required!");
        } else if(pName.equals("tie")){
            winner = "Tie!";
        } else if(pName.equals("Computer")) {
            winner = "You lost! Better luck next time!";
        } else {
            winner = "Congrats " + pName + "!";
        }

        // Initialize buttons and textviews
        multiWinner = findViewById(R.id.multiWinner);
        playAgain = findViewById(R.id.playAgain);
        quit = findViewById(R.id.quit);

        // Set the winner message to display
        multiWinner.setText(winner);
    }

    /**
     * This method starts a new game, puts the screen back to startgame screen
     *
     * @param view
     *          Current view at button press
     */
    public void playAgain(View view){
        Intent goBack = new Intent();
        goBack.putExtra("quit", false);
        goBack.putExtra("gameMode", gameMode);
        setResult(RESULT_OK, goBack);
        finish();
    }

    /**
     * Keeps the app from crashing when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        quitGame(null);
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
     * This method quits the game and returns to home
     *
     * @param view
     *          Current view at button press
     */
    public void quitGame(View view){
        Intent goBack = new Intent();
        goBack.putExtra("quit", true);
        goBack.putExtra("gameMode", gameMode);
        setResult(RESULT_OK, goBack);
        finish();
    }
}
