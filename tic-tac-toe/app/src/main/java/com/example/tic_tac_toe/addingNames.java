package com.example.tic_tac_toe;

import android.content.Intent;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class watches over the other "add name" pages and when a user types
 * a "w", it keeps the game mode from switching.
 */
abstract class addingNames extends AppCompatActivity {

    private int gameMode;

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

    private int wPressed(){
        int game_mode = getGameMode();
        if(game_mode == 1) {
            return 0;
        } else if(game_mode == 0) {
            return 1;
        }
        return -1;
    }

    protected int getGameMode() {

        return gameMode;

    }

    protected void setGameMode(int gameMode) {

        this.gameMode = gameMode;

    }

    /**
     * Keeps the app from crashing when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        readyToGoBack("", "", getGameMode());
    }

    protected void readyToGoBack(String player1, String player2, int gameMode){

        // Makes sure that if the player has a "W" in their name, it does not change the game mode
        int wCounter = 0;
        int strLength = player1.length();
        for(int i = 0; i < strLength; i++){
            if(player1.charAt(i) == 'w' || player1.charAt(i) == 'W'){
                wCounter++;
            }
        }
        strLength = player2.length();
        for(int i = 0; i < strLength; i++) {
            if (player2.charAt(i) == 'w' || player2.charAt(i) == 'W') {
                wCounter++;
            }
        }
        if(wCounter % 2 != 0){
            gameMode = wPressed();
        }

        if(gameMode == 2) {
            if(player1.equals("")){
                player1 = "Player 1";
            }

            if(player2.equals("")){
                player2 = "Player 2";
            }
        } else if(gameMode == 1 || gameMode == 0){
            if(player1.equals("")){
                player1 = "Player";
            }
        } else {
            throw new IllegalArgumentException("Invalid game mode!");
        }


        Intent goBack = new Intent();

        goBack.putExtra("player_1", player1);
        goBack.putExtra("player_2", player2);
        goBack.putExtra("gameMode", gameMode);

        setResult(RESULT_OK, goBack);

        finish();

    }

}
