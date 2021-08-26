package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * This is the home page for the app. The user can start a game from this page.
 */
public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button multiplayerButton;
    private Button instructionsButton;
    private String player1;
    private String player2;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        multiplayerButton = findViewById(R.id.multiplayerButton);
        instructionsButton = findViewById(R.id.instructionsButton);
        setGameMode(1);

    }

    private int getGameMode() {

        return gameMode;

    }

    private void setPlayer1(String player1){
        this.player1 = player1;
    }

    private void setPlayer2(String player2){
        this.player2 = player2;
    }

    private void setGameMode(int gameMode){
        this.gameMode = gameMode;
    }

    /**
     * This method starts up a new multiplayer game
     *
     * @param view
     *          Current view at button press
     */
    public void startMultiplayerGame(View view){

        Intent startMultiplayerGameIntent = new Intent(this, addPlayerNames.class);

        final int result = 2;

        startMultiplayerGameIntent.putExtra("gameMode", gameMode);

        startActivityForResult(startMultiplayerGameIntent, result);

    }


    /**
     * This method starts up a new single player game
     *
     * @param view
     *          Current view at button press
     */
    public void startSinglePlayerGame(View view){

        Intent startSinglePlayerGameIntent = new Intent(this, addPlayerName.class);

        final int result = 5;

        startSinglePlayerGameIntent.putExtra("gameMode", gameMode);

        startActivityForResult(startSinglePlayerGameIntent, result);

    }

    /**
     * This method starts the game with the information that the multiplayer/singleplayer methods
     * collected for it.
     *
     * @param gameMode
     *          Tells the game if it is in multiplayer or single player mode
     */
    private void goToGame(int gameMode){

        Intent goToGameIntent = new Intent(this, gamePlay_screen.class);

        // Return code for later
        final int result = 6;

        goToGameIntent.putExtra("gameMode", gameMode);
        goToGameIntent.putExtra("player1", player1);
        goToGameIntent.putExtra("player2", player2);

        startActivityForResult(goToGameIntent, result);

    }

    /**
     * This method takes the user to the instructions page to learn how to play
     *
     * @param view
     *          Current view at button press
     */
    public void readInstructions(View view){

        Intent readInstructionsIntent = new Intent(this, instructions.class);

        final int result = 0;

        readInstructionsIntent.putExtra("gameMode", gameMode);

        startActivityForResult(readInstructionsIntent, result);

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

    /**
     * Keeps the app from crashing when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        System.out.print("");
    }

    /**
     * What is received from the activity on its way back here. (nothing in this case)
     *
     * @param requestCode
     *          To identify what was just called and returned
     * @param resultCode
     *          Tells if everything went okay
     * @param data
     *          The information sent along from the other activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            setGameMode(data.getIntExtra("gameMode", -1));
        } else if(requestCode == 2 || requestCode == 5){
            if (resultCode == RESULT_OK) {
                setPlayer1(data.getStringExtra("player_1"));
                setPlayer2(data.getStringExtra("player_2"));
                setGameMode(data.getIntExtra("gameMode", -1));

                goToGame(gameMode);
            }
        } else if(requestCode == 6){
            int game_mode = data.getIntExtra("gameMode", -1);
            if(game_mode == 2){
                gameMode = 1;
            } else {
                setGameMode(data.getIntExtra("gameMode", -1));
            }
        }

    }

}
