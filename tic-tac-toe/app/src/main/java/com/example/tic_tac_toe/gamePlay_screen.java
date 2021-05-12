package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This screen is where the user actually plays the game.
 */ 
public class gamePlay_screen extends AppCompatActivity {

    private int gameMode; // 0 for easy || 1 for impossible || 2 for multiplayer
    private int winner; // 1 for player1 || 2 for player2/computer || 3 for tie
    private int player; // player that is about to move
                        // 0 for uninitialized || odd number for player1 || even number for player2
    private int move; // The number of moves that have been made
    private boolean gameOver; // Has the game ended
                                // Either the board is full and/or someone has won
    private int[][] gameBoard; // The tic-tac-toe playing field
    private int[] scoreBoard; // An array that holds each win like this
                                // { [player1], [player2/computer], [tie] }
    private boolean quit; // Has the user decided to quit the game
    private int numGames; // How many games have been played
    private String player1; // Player 1 name
    private String player2; // Player 2 name

    // Buttons on the game board
    private Button topLeft;
    private Button topMiddle;
    private Button topRight;
    private Button middleLeft;
    private Button center;
    private Button middleRight;
    private Button bottomLeft;
    private Button bottomMiddle;
    private Button bottomRight;
    private Button quitBtn;

    private TextView whosTurn; // Prompt that tells who's turn it is
    private TextView scoreBoardDisplay; // The scoreboard as a printout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen);

        // Get the game mode
        Intent mIntent = getIntent();
        setGameMode(mIntent.getIntExtra("gameMode", -1));
        setPlayer1(mIntent.getStringExtra("player1"));
        setPlayer2(mIntent.getStringExtra("player2"));

        // Initializing all of the buttons on the game board and the textviews
        topLeft = findViewById(R.id.topLeft);
        topMiddle = findViewById(R.id.topMiddle);
        topRight = findViewById(R.id.topRight);
        middleLeft = findViewById(R.id.middleLeft);
        center = findViewById(R.id.center);
        middleRight = findViewById(R.id.middleRight);
        bottomLeft = findViewById(R.id.bottomLeft);
        bottomMiddle = findViewById(R.id.bottomMiddle);
        bottomRight = findViewById(R.id.bottomRight);
        quitBtn = findViewById(R.id.quitBtn);
        whosTurn = findViewById(R.id.whosTurn);
        scoreBoardDisplay = findViewById(R.id.scoreBoardDisplay);

        // Starting out with no past games on record
        setNumGames(0);

        // Start with a blank score board
        setScoreBoard();

        // Nobody has clicked Quit yet
        setQuit(false);

        final int first = whoGoesFirst();

        findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                initializeNewGame(first);
            }
        });
    }

    private void setGameMode(int gameMode) {

        this.gameMode = gameMode;

    }

    private void setWinner(int winner) {

        this.winner = winner;

    }

    private void setPlayer(int player) {

        this.player = player;

    }

    private void setMove(int move) {

        this.move = move;

    }

    private void setGameOver(boolean gameOver) {

        this.gameOver = gameOver;

    }

    private void setGameBoard() {

        gameBoard = new int[3][3];

        // Initialize the game board
        // -1 is an empty space
        // 1 will represent X
        // 0 will represent O
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = -1;
            }
        }

    }

    private void setScoreBoard() {

        if(numGames <=0){
            scoreBoard = new int[3];
        }

    }

    private void setQuit(boolean quit) {

        this.quit = quit;

    }

    private void setNumGames(int numGames) {

        this.numGames = numGames;

    }

    private void setPlayer1(String player1) {

        this.player1 = player1;

    }

    private void setPlayer2(String player2) {

        this.player2 = player2;

    }

    private int getGameMode() {

        return gameMode;

    }

    private int getWinner() {

        return winner;

    }

    private int getPlayer() {

        return player;

    }

    private int getMove() {

        return move;

    }

    private boolean getGameOver() {

        return gameOver;

    }

    private int[][] getGameBoard() {

        return gameBoard;

    }

    private int[] getScoreBoard() {

        return scoreBoard;

    }

    private boolean getQuit() {

        return quit;

    }

    private int getNumGames() {

        return numGames;

    }

    private String getPlayer1() {

        return player1;

    }

    private String getPlayer2() {

        return player2;

    }

    /**
     * This method is called at the start of each game to reset the board (Note that some
     * information, like score, will be saved)
     *
     * @param player
     *          The current player (the one to start the game)
     */
    private void initializeNewGame(int player){
        // Keep track of current player
        setPlayer(player);

        // Let there be no moves on record
        setMove(0);

        // Reset the game board to empty
        setGameBoard();

        // The game is not over (it is just beginning)
        setGameOver(false);

        // Grab the scoreboard to display it
        int[] board = getScoreBoard();

        // Get player1 and player2's names
        String p1 = getPlayer1();
        String p2 = getPlayer2();

        // Prompt the players to know who's turn it is
        String curPlayer;
        if((player+1) % 2 == 0) {
            curPlayer = "Your turn " + p2;
            whosTurn.setText(curPlayer);
        } else {
            curPlayer = "Your turn " + p1;
            whosTurn.setText(curPlayer);
        }

        // Set the scoreboard and show it
        String score = "" + p1 + ": " + board[0] + "     " + p2 + ": " + board[1] + "     " + "Ties: " + board[2];
        scoreBoardDisplay.setText(score);

        // Make sure the visual tic-tac-toe board is empty
        topLeft.setText("");
        topMiddle.setText("");
        topRight.setText("");
        middleLeft.setText("");
        center.setText("");
        middleRight.setText("");
        bottomLeft.setText("");
        bottomMiddle.setText("");
        bottomRight.setText("");

        // Make sure the visual tic-tac-toe board is clickable
        topLeft.setEnabled(true);
        topMiddle.setEnabled(true);
        topRight.setEnabled(true);
        middleLeft.setEnabled(true);
        center.setEnabled(true);
        middleRight.setEnabled(true);
        bottomLeft.setEnabled(true);
        bottomMiddle.setEnabled(true);
        bottomRight.setEnabled(true);
    }

    /**
     * This is called when the player chooses the top left space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveTL(View view){

        final int playerMove = 1;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            topLeft.setTextColor(Color.parseColor("#00bdc7"));
            topLeft.setText("X");
        } else if (getPlayer() % 2 == 0) {
            topLeft.setTextColor(Color.parseColor("#ec00dc"));
            topLeft.setText("O");
        }

        // Make this space unclickable so it can't be used again
        topLeft.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the top middle space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveTM(View view){

        final int playerMove = 2;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            topMiddle.setTextColor(Color.parseColor("#00bdc7"));
            topMiddle.setText("X");
        } else if (getPlayer() % 2 == 0) {
            topMiddle.setTextColor(Color.parseColor("#ec00dc"));
            topMiddle.setText("O");
        }

        // Make this space unclickable so it can't be used again
        topMiddle.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the top right space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveTR(View view){

        final int playerMove = 3;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            topRight.setTextColor(Color.parseColor("#00bdc7"));
            topRight.setText("X");
        } else if (getPlayer() % 2 == 0) {
            topRight.setTextColor(Color.parseColor("#ec00dc"));
            topRight.setText("O");
        }

        // Make this space unclickable so it can't be used again
        topRight.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the middle left space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveML(View view){

        final int playerMove = 4;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            middleLeft.setTextColor(Color.parseColor("#00bdc7"));
            middleLeft.setText("X");
        } else if (getPlayer() % 2 == 0) {
            middleLeft.setTextColor(Color.parseColor("#ec00dc"));
            middleLeft.setText("O");
        }

        // Make this space unclickable so it can't be used again
        middleLeft.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the center space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveC(View view){

        final int playerMove = 5;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            center.setTextColor(Color.parseColor("#00bdc7"));
            center.setText("X");
        } else if (getPlayer() % 2 == 0) {
            center.setTextColor(Color.parseColor("#ec00dc"));
            center.setText("O");
        }

        // Make this space unclickable so it can't be used again
        center.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the middle right space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveMR(View view){

        final int playerMove = 6;

        newMove(6);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            middleRight.setTextColor(Color.parseColor("#00bdc7"));
            middleRight.setText("X");
        } else if (getPlayer() % 2 == 0) {
            middleRight.setTextColor(Color.parseColor("#ec00dc"));
            middleRight.setText("O");
        }

        // Make this space unclickable so it can't be used again
        middleRight.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the bottom left space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveBL(View view){

        final int playerMove = 7;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            bottomLeft.setTextColor(Color.parseColor("#00bdc7"));
            bottomLeft.setText("X");
        } else if (getPlayer() % 2 == 0) {
            bottomLeft.setTextColor(Color.parseColor("#ec00dc"));
            bottomLeft.setText("O");
        }

        // Make this space unclickable so it can't be used again
        bottomLeft.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the bottom middle space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveBM(View view){

        final int playerMove = 8;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            bottomMiddle.setTextColor(Color.parseColor("#00bdc7"));
            bottomMiddle.setText("X");
        } else if (getPlayer() % 2 == 0) {
            bottomMiddle.setTextColor(Color.parseColor("#ec00dc"));
            bottomMiddle.setText("O");
        }

        // Make this space unclickable so it can't be used again
        bottomMiddle.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This is called when the player chooses the bottom right space on the board
     *
     * @param view
     *          Current view at button press
     */
    public void moveBR(View view){

        final int playerMove = 9;

        newMove(playerMove);

        // Put an X or O based on player
        if (getPlayer() % 2 == 1) {
            bottomRight.setTextColor(Color.parseColor("#00bdc7"));
            bottomRight.setText("X");
        } else if (getPlayer() % 2 == 0) {
            bottomRight.setTextColor(Color.parseColor("#ec00dc"));
            bottomRight.setText("O");
        }

        // Make this space unclickable so it can't be used again
        bottomRight.setEnabled(false);

        // Let the computer go in single player mode
        if((getGameMode() == 0 || getGameMode() == 1) && getPlayer() % 2 == 1 && !getGameOver()){
            disableBoard();
            view.post(new Runnable() {
                @Override
                public void run() {
                    computerMove(playerMove);
                }
            });
        }

    }

    /**
     * This method is how the move is put onto the board in a multiplayer game.
     *
     * @param playerMove
     *          The space that they player just moved
     */
    private void newMove(int playerMove) {

        // Grab the gameboard to add the move
        int[][] gameBoard = getGameBoard();

        // Increment the player to know who is going then get it
        setPlayer(getPlayer() + 1);
        int player = getPlayer();

        // Increment the move to know how many moves there have been
        setMove(getMove() + 1);

        // Chooses X or O to put on the board based on who's turn it is
        if (player % 2 == 1) {
            player = 1;
        } else if (player % 2 == 0) {
            player = 2;
        }

        // Used to remember where the player is putting their move
        int row;
        int col;

        // Puts the move on the board depending on player input
        switch (playerMove) {
            case 1:
                if (player == 1) {
                    gameBoard[0][0] = 1;
                } else {
                    gameBoard[0][0] = 0;
                }
                row = 0;
                col = 0;
                break;
            case 2:
                if (player == 1) {
                    gameBoard[0][1] = 1;
                } else {
                    gameBoard[0][1] = 0;
                }
                row = 0;
                col = 1;
                break;
            case 3:
                if (player == 1) {
                    gameBoard[0][2] = 1;
                } else {
                    gameBoard[0][2] = 0;
                }
                row = 0;
                col = 2;
                break;
            case 4:
                if (player == 1) {
                    gameBoard[1][0] = 1;
                } else {
                    gameBoard[1][0] = 0;
                }
                row = 1;
                col = 0;
                break;
            case 5:
                if (player == 1) {
                    gameBoard[1][1] = 1;
                } else {
                    gameBoard[1][1] = 0;
                }
                row = 1;
                col = 1;
                break;
            case 6:
                if (player == 1) {
                    gameBoard[1][2] = 1;
                } else {
                    gameBoard[1][2] = 0;
                }
                row = 1;
                col = 2;
                break;
            case 7:
                if (player == 1) {
                    gameBoard[2][0] = 1;
                } else {
                    gameBoard[2][0] = 0;
                }
                row = 2;
                col = 0;
                break;
            case 8:
                if (player == 1) {
                    gameBoard[2][1] = 1;
                } else {
                    gameBoard[2][1] = 0;
                }
                row = 2;
                col = 1;
                break;
            case 9:
                if (player == 1) {
                    gameBoard[2][2] = 1;
                } else {
                    gameBoard[2][2] = 0;
                }
                row = 2;
                col = 2;
                break;
            default:
                throw new IllegalArgumentException("ERROR: Invalid move!");
        }

        // See if we have reached the end of the game (either by win or tie)
        checkGameOverStatus(row, col, player);

        // If the game is over...
        if(getGameOver()){

            // Put the score on the scoreboard
            int winner = getWinner();
            if (winner == 1 || winner == 2 || winner == 3) {
                scoreBoard[winner - 1] += 1;
            } else {
                throw new IllegalArgumentException("ERROR: Invalid winner at end of game!");
            }

            // And go to the game over screen
            Intent gameOverIntent = new Intent(this, GameOver.class);
            final int result = 3;
            String name;
            if(winner == 3){    // Setting the name of the winner for the game over screen
                name = "tie";
            } else if(winner == 1){
                name = getPlayer1();
            } else if(winner == 2){
                name = getPlayer2();
            } else {
                throw new IllegalArgumentException("ERROR: Invalid winner at end of game!");
            }
            gameOverIntent.putExtra("playerName", name);
            gameOverIntent.putExtra("gameMode", getGameMode());
            startActivityForResult(gameOverIntent, result);
            return;
        }

        // Sets current player name
        if (player == 1) {
            String playerOne = "Your turn " + getPlayer2();
            whosTurn.setText(playerOne);
        } else if (player == 2) {
            String playerTwo = "Your turn " + getPlayer1();
            whosTurn.setText(playerTwo);
        }

    }

    /**
     * This method is how the computer starts its move
     */
    private void computerMove(int playerMove) {

        // make the computer wait a second before moving to seem more life-like
        try{
            Thread.sleep(800);
        } catch(InterruptedException e){
            System.out.println("The computer died! Please restart the app.");
        }

        int win = winningMove(); // check to see if the computer can make a winning move

        if(win == 1) { // if the computer moved, go back to let the player go
            findViewById(android.R.id.content).post(new Runnable() {
                @Override
                public void run() {
                    enableBoard();
                }
            });
            return;
        }

        int defence = checkForTwoInRow(playerMove, true); // check to see if the player is about to win

        if(defence == 1){ // if the computer moved, go back to let the player go
            findViewById(android.R.id.content).post(new Runnable() {
                @Override
                public void run() {
                    enableBoard();
                }
            });
            return;
        }

        // based on the mode (easy or hard) send the computer off to decide where to make it's move
        if(getGameMode() == 1) {
            computerDecisionHard(playerMove);
        } else {
            computerDecisionEasy(playerMove);
        }

        findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                enableBoard();
            }
        });

    }

    /**
     * This method disables the board so the user can't click before the computer has gone
     */
    private void disableBoard() {

        topLeft.setEnabled(false);
        topMiddle.setEnabled(false);
        topRight.setEnabled(false);
        middleLeft.setEnabled(false);
        center.setEnabled(false);
        middleRight.setEnabled(false);
        bottomLeft.setEnabled(false);
        bottomMiddle.setEnabled(false);
        bottomRight.setEnabled(false);

    }

    /**
     * This method re-enabes the board so the player can make a move after the con=mputer has gone
     */
    private void enableBoard() {

        if(topLeft.getText().equals("")) {
            topLeft.setEnabled(true);
        }

        if(topMiddle.getText().equals("")) {
            topMiddle.setEnabled(true);
        }

        if(topRight.getText().equals("")) {
            topRight.setEnabled(true);
        }

        if(middleLeft.getText().equals("")) {
            middleLeft.setEnabled(true);
        }

        if(center.getText().equals("")) {
            center.setEnabled(true);
        }

        if(middleRight.getText().equals("")) {
            middleRight.setEnabled(true);
        }

        if(bottomLeft.getText().equals("")) {
            bottomLeft.setEnabled(true);
        }

        if(bottomMiddle.getText().equals("")) {
            bottomMiddle.setEnabled(true);
        }

        if(bottomRight.getText().equals("")) {
            bottomRight.setEnabled(true);
        }

    }

    /**
     * This method checks to see if there are two computer symbols in a line. If there are, it moves
     * to win the game.
     *
     * @return 1 if they computer moved and -1 if the computer did not move
     */
    private int winningMove() {

        int[][] gameBoard = getGameBoard();

        if(gameBoard[0][0] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(1, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[0][1] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(2, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[0][2] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(3, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[1][0] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(4, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[1][1] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(5, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[1][2] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(6, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[2][0] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(7, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[2][1] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(8, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        if(gameBoard[2][2] == 0) { // check to see if the computer has a piece on the board here
            int win = checkForTwoInRow(9, false);

            if (win == 1) { // if the computer found two in one row, it will have moved and won
                return 1;
            }
        }

        return -1; // computer didn't move

    }

    /**
     * This method checks to see if there are two enemy symbols in a line. If there are, it moves
     * to block an enemy win.
     *
     * @param playerMove
     *          Where the player just went so it doesn't have to check every where
     * @param isDefence
     *          True if the computer is going to be checking for enemy marks, and false if the
     *          computer is going to be checking for its own marks
     * @return 1 if they computer moved and -1 if the computer did not move
     */
    private int checkForTwoInRow(int playerMove, boolean isDefence) {

        int[][] gameBoard = getGameBoard();

        int symbol;

        if(isDefence) {
            symbol = 1;
        } else {
            symbol = 0;
        }

        switch (playerMove) {
            case 1:
                if(gameBoard[0][1] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[0][2] == -1){ // make sure the space is available
                        moveTR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][2] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[0][1] == -1){ // make sure the space is available
                        moveTM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][0] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[2][0] == -1){ // make sure the space is available
                        moveBL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][0] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[1][0] == -1){ // make sure the space is available
                        moveML(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[2][2] == -1){ // make sure the space is available
                        moveBR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][2] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            case 2:
                if(gameBoard[0][0] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[0][2] == -1){ // make sure the space is available
                        moveTR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][2] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[0][0] == -1){ // make sure the space is available
                        moveTL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[2][1] == -1){ // make sure the space is available
                        moveBM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][1] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }
                break;
            case 3:
                if(gameBoard[0][0] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[0][1] == -1){ // make sure the space is available
                        moveTM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][1] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[0][0] == -1){ // make sure the space is available
                        moveTL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][2] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[2][2] == -1){ // make sure the space is available
                        moveBR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][2] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[1][2] == -1){ // make sure the space is available
                        moveMR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[2][0] == -1){ // make sure the space is available
                        moveBL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][0] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            case 4:
                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[1][2] == -1){ // make sure the space is available
                        moveMR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][2] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][0] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[2][0] == -1){ // make sure the space is available
                        moveBL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][0] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[0][0] == -1){ // make sure the space is available
                        moveTL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            case 5:
                if(gameBoard[1][0] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[1][2] == -1){ // make sure the space is available
                        moveMR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][2] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[1][0] == -1){ // make sure the space is available
                        moveML(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][1] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[2][1] == -1){ // make sure the space is available
                        moveBM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][1] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[0][1] == -1){ // make sure the space is available
                        moveTM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][0] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[2][2] == -1){ // make sure the space is available
                        moveBR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][2] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[0][0] == -1){ // make sure the space is available
                        moveTL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][2] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[2][0] == -1){ // make sure the space is available
                        moveBL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][0] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[0][2] == -1){ // make sure the space is available
                        moveTR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            case 6:
                if(gameBoard[1][0] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[1][0] == -1){ // make sure the space is available
                        moveML(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][2] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[2][2] == -1){ // make sure the space is available
                        moveBR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][2] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[0][2] == -1){ // make sure the space is available
                        moveTR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            case 7:
                if(gameBoard[2][1] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[2][2] == -1){ // make sure the space is available
                        moveBR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][2] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[2][1] == -1){ // make sure the space is available
                        moveBM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][0] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[1][0] == -1){ // make sure the space is available
                        moveML(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][0] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[0][0] == -1){ // make sure the space is available
                        moveTL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[0][2] == -1){ // make sure the space is available
                        moveTR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][2] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            case 8:
                if(gameBoard[2][0] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[2][2] == -1){ // make sure the space is available
                        moveBR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][2] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[2][0] == -1){ // make sure the space is available
                        moveBL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][1] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[0][1] == -1){ // make sure the space is available
                        moveTM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            case 9:
                if(gameBoard[2][0] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[2][1] == -1){ // make sure the space is available
                        moveBM(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[2][1] == symbol) { // checking for two enemy marks in a horizontal line
                    if(gameBoard[2][0] == -1){ // make sure the space is available
                        moveBL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][2] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[1][2] == -1){ // make sure the space is available
                        moveMR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][2] == symbol) { // checking for two enemy marks in a vertical line
                    if(gameBoard[0][2] == -1){ // make sure the space is available
                        moveTR(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[0][0] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[1][1] == -1){ // make sure the space is available
                        moveC(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                if(gameBoard[1][1] == symbol) { // checking for two enemy marks in a diagonal line
                    if(gameBoard[0][0] == -1){ // make sure the space is available
                        moveTL(null); // move in that space
                        return 1; // return that the move was a success
                    }
                }

                break;
            default:
                throw new IllegalArgumentException("ERROR: Invalid move!");
        }

        return -1;

    }

    /**
     * This method is how the computer makes its final decision on where to move in the hard game
     * mode
     */
    private void computerDecisionHard(int playerMove) {

        int[][] gameBoard = getGameBoard();

        // edge cases
        if(getMove() <= 4) {
            if ((gameBoard[0][1] == 1 && gameBoard[1][0] == 1) || (gameBoard[0][1] == 1 && gameBoard[1][2] == 1) ||
                    (gameBoard[1][0] == 1 && gameBoard[2][1] == 1) || (gameBoard[2][1] == 1 && gameBoard[1][2] == 1)) {
                moveC(null);
                return;
            }

            if (isInCorner(playerMove)) {
                if(gameBoard[0][1] == 1 || gameBoard[1][0] == 1 || gameBoard[1][2] == 1 || gameBoard[2][1] == 1){
                    moveC(null);
                    return;
                }
            }

            if ((gameBoard[0][0] == 1 && gameBoard[2][1] == 1) || (gameBoard[1][0] == 1 && gameBoard[2][2] == 1)) {
                if(gameBoard[2][0] == -1) {
                    moveBL(null);
                    return;
                }
            }

            if ((gameBoard[2][0] == 1 && gameBoard[1][2] == 1) || (gameBoard[0][2] == 1 && gameBoard[2][1] == 1)) {
                if(gameBoard[2][2] == -1) {
                    moveBR(null);
                    return;
                }
            }

            if ((gameBoard[0][1] == 1 && gameBoard[2][2] == 1) || (gameBoard[0][0] == 1 && gameBoard[1][2] == 1)) {
                if(gameBoard[0][2] == -1) {
                    moveTR(null);
                    return;
                }
            }

            if ((gameBoard[0][2] == 1 && gameBoard[1][0] == 1) || (gameBoard[0][1] == 1 && gameBoard[2][0] == 1)) {
                if(gameBoard[0][0] == -1) {
                    moveTL(null);
                    return;
                }
            }
        }

        if(getMove() == 1) { // if this is the computer's first move but it is the second move in the game
            if(isInCorner(playerMove)) {
                moveC(null);
                return;
            }
        }

        if(gameBoard[1][1] == 0){ // if it has gone in the center, it needs to go on an edge
            int edgeSecured = goForEdge();
            if(edgeSecured == 1) {
                return;
            }
        }

        // move in a corner
        int cornerSecured = goForCorner(playerMove);
        if(cornerSecured == 1) {
            return;
        }

        pickASpot(); // if it hasn't moved yet, just go somewhere

    }

    /**
     * This method is how the computer makes its final decision on where to move in the easy game
     * mode
     */
    private void computerDecisionEasy(int playerMove) {

        int edgeSecured = goForEdge();
        if(edgeSecured == 1) {
            return;
        }

        // move in a corner
        int cornerSecured = goForCorner(playerMove);
        if(cornerSecured == 1) {
            return;
        }

        pickASpot(); // if it hasn't moved yet, just go somewhere

    }

    /**
     * This method checks to see if the player went in the corner of the board
     *
     * @param playerMove
     *          The player's last move
     * @return True if the player went in the corner; otherwise, false
     */
    private boolean isInCorner(int playerMove) {
        return (playerMove == 1 || playerMove == 3 || playerMove == 7 || playerMove == 9);
    }

    /**
     * This method is how the computer finds an empty corner to move to.
     *
     * @return 1 if the computer moved and -1 if it didn't
     */
    private int goForCorner(int playerMove){

        int[][] board = getGameBoard();

        if(playerMove == 2){
            if(board[0][0] == -1) {
                moveTL(null);
                return 1;
            }

            if(board[0][2] == -1) {
                moveTR(null);
                return 1;
            }
        } else if(playerMove == 4){
            if(board[0][0] == -1) {
                moveTL(null);
                return 1;
            }

            if(board[2][0] == -1){
                moveBL(null);
                return 1;
            }
        } else if(playerMove == 6){
            if(board[0][2] == -1) {
                moveTR(null);
                return 1;
            }

            if(board[2][2] == -1){
                moveBR(null);
                return 1;
            }
        } else if(playerMove == 8){
            if(board[2][0] == -1){
                moveBL(null);
                return 1;
            }

            if(board[2][2] == -1){
                moveBR(null);
                return 1;
            }
        }

        if(board[0][0] == -1) {
            moveTL(null);
            return 1;
        }

        if(board[0][2] == -1) {
            moveTR(null);
            return 1;
        }

        if(board[2][0] == -1){
            moveBL(null);
            return 1;
        }

        if(board[2][2] == -1){
            moveBR(null);
            return 1;
        }

        return -1;

    }

    private int goForEdge(){

        int[][] gameBoard = getGameBoard();

        if(gameBoard[0][1] == -1){ // looking for an empty edge
            moveTM(null);
            return 1;
        }

        if(gameBoard[1][0] == -1){ // looking for an empty edge
            moveML(null);
            return 1;
        }

        if(gameBoard[2][1] == -1){ // looking for an empty edge
            moveBM(null);
            return 1;
        }

        if(gameBoard[1][2] == -1){ // looking for an empty edge
            moveMR(null);
            return 1;
        }

        return -1;

    }

    /**
     * This method is when the computer needs to make a move and it does not matter where it goes
     */
    private void pickASpot(){

        int[][] gameBoard = getGameBoard();

        if(gameBoard[0][0] == -1) {
            moveTL(null);
        } else if(gameBoard[0][1] == -1) {
            moveTM(null);
        } else if(gameBoard[0][2] == -1) {
            moveTR(null);
        } else if(gameBoard[1][0] == -1) {
            moveML(null);
        } else if(gameBoard[1][1] == -1) {
            moveC(null);
        } else if(gameBoard[1][2] == -1) {
            moveMR(null);
        } else if(gameBoard[2][0] == -1) {
            moveBL(null);
        } else if(gameBoard[2][1] == -1) {
            moveBM(null);
        } else if(gameBoard[2][2] == -1) {
            moveBR(null);
        }

    }

    /**
     * This method check to see if the game has come to an end (either by win or tie)
     *
     * @param row
     *          The row that the player just went in
     * @param col
     *          The column that they player just went in
     * @param player
     *          The current player
     */
    private void checkGameOverStatus(int row, int col, int player) {

        // Setting default values
        boolean boardFull;
        boolean threeInARow;

        // Checks to see if the board is full
        boardFull = isBoardFull();

        // Checks to see if there are three of the same symbol in a row
        threeInARow = isThreeInARow(row, col);

        // This sets the winner if the game is over
        if (threeInARow) { // If there are three in a row, set the current player as the winner
            int winner = player % 2;
            if (winner == 0) {
                winner = 2;
            }
            setWinner(winner);
            setGameOver(true);
        } else if (boardFull) { // If there is not three in a row but the board is full, set as tie
            setWinner(3);
            setGameOver(true);
        }

    }

    /**
     * This method is how the program knows who is going to make the first move.
     *
     * @return It returns an even number for player1 and an odd number for player2/computer
     */
    private int whoGoesFirst(){
        if(getGameMode() == 2){
            if(getNumGames() == 0) {
                return 0;
            } else if (getWinner() == 1) {
                return 1;
            } else if (getWinner() == 2) {
                return 2;
            } else if (getWinner() == 3) {
                return 0;
            } else {
                throw new IllegalArgumentException("ERROR: Invalid winner at start of game!");
            }
        } else {
            choosePlayerOrder();
            return getPlayer();
        }
    }

    /**
     * This method lets the player choose to go before or after the computer
     */
    private void choosePlayerOrder(){
        Intent choosePlayerOrderIntent = new Intent(this, Choose_player_order.class);

        final int result = 4;

        choosePlayerOrderIntent.putExtra("gameMode", gameMode);

        startActivityForResult(choosePlayerOrderIntent, result);
    }

    /**
     * This method checks to see if the board is full
     *
     * @return True if the each space on the game board has been filled, false otherwise
     */
    private boolean isBoardFull() {

        int currentMove = getMove();

        // Once all 9 spaces are filled, the board is full
        return currentMove >= 9;

    }

    /**
     * This method checks to see if there are three of the same symbol in a row
     *
     * @param row
     *          The row of the move that was just made
     * @param col
     *          The column of the move that was just made
     * @return True if there are three of the same symbol in a row (horizontally, vertically,
     *          and/or diagonally.
     */
    private boolean isThreeInARow(int row, int col) {

        // Get the game board so it can be parsed
        int[][] board = getGameBoard();

        // Who just went
        int player = getPlayer();
        // What piece were they using
        int playerPiece = player % 2;

        // Checking the neighbors of the symbol just placed to see if they match
        if (row == 0) {
            if (col == 0) {
                return ((board[0][1] == playerPiece && board[0][2] == playerPiece) // Win horizontally
                        || (board[1][0] == playerPiece && board[2][0] == playerPiece) // Win Vertically
                        || (board[1][1] == playerPiece && board[2][2] == playerPiece)); // Win diagonally
            } else if (col == 1) {
                return ((board[0][0] == playerPiece && board[0][2] == playerPiece) // Win horizontally
                        || (board[1][1] == playerPiece && board[2][1] == playerPiece)); // Win Vertically
            } else if (col == 2) {
                return ((board[0][0] == playerPiece && board[0][1] == playerPiece) // Win horizontally
                        || (board[1][2] == playerPiece && board[2][2] == playerPiece) // Win Vertically
                        || (board[1][1] == playerPiece && board[2][0] == playerPiece)); // Win diagonally
            } else {
                throw new IllegalArgumentException(
                        "Error: Column is out of bounds of the game board in the first row!");
            }
        } else if (row == 1) {
            if (col == 0) {
                return ((board[0][0] == playerPiece && board[2][0] == playerPiece) // Win Vertically
                        || (board[1][1] == playerPiece && board[1][2] == playerPiece)); // Win horizontally
            } else if (col == 1) {
                return ((board[0][0] == playerPiece && board[2][2] == playerPiece) // Win diagonally
                        || (board[0][2] == playerPiece && board[2][0] == playerPiece) // Win diagonally
                        || (board[1][0] == playerPiece && board[1][2] == playerPiece) // Win horizontally
                        || (board[0][1] == playerPiece && board[2][1] == playerPiece)); // Win Vertically
            } else if (col == 2) {
                return ((board[1][0] == playerPiece && board[1][1] == playerPiece) // Win horizontally
                        || (board[0][2] == playerPiece && board[2][2] == playerPiece)); // Win diagonally
            } else {
                throw new IllegalArgumentException(
                        "Error: Column is out of bounds of the game board in the second row!");
            }
        } else if (row == 2) {
            if (col == 0) {
                return ((board[2][1] == playerPiece && board[2][2] == playerPiece) // Win horizontally
                        || (board[0][0] == playerPiece && board[1][0] == playerPiece) // Win Vertically
                        || (board[1][1] == playerPiece && board[0][2] == playerPiece)); // Win diagonally
            } else if (col == 1) {
                return ((board[2][0] == playerPiece && board[2][2] == playerPiece) // Win horizontally
                        || (board[0][1] == playerPiece && board[1][1] == playerPiece)); // Win Vertically
            } else if (col == 2) {
                return ((board[2][0] == playerPiece && board[2][1] == playerPiece) // Win horizontally
                        || (board[0][2] == playerPiece && board[1][2] == playerPiece) // Win Vertically
                        || (board[0][0] == playerPiece && board[1][1] == playerPiece)); // Win diagonally
            } else {
                throw new IllegalArgumentException(
                        "Error: Column is out of bounds of the game board in the third row!");
            }
        } else {
            throw new IllegalArgumentException("Error: Row is out of bounds of the game board!");
        }

    }

    /**
     * Keeps the app from crashing when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        endGameQuit(null);
    }

    /**
     * This is called if the quit button is pushed. It closes this activity and moves back to the
     * home screen.
     *
     * @param view
     *          The current view at button press
     */
    public void endGameQuit(View view){
        Intent goBack = new Intent();

        goBack.putExtra("gameMode", gameMode);

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

    /**
     * What is received from the activity on its way back here
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

        // For player names
        if(requestCode == 2){
            if (resultCode == RESULT_OK) {
                setPlayer1(data.getStringExtra("player_1"));
                setPlayer2(data.getStringExtra("player_2"));
                setGameMode(data.getIntExtra("gameMode", -1));
                initializeNewGame(0);
            }
        }

        // For coming back from a game over screen
        else if(requestCode == 3){
            boolean toQuit = data.getBooleanExtra("quit", false);
            setGameMode(data.getIntExtra("gameMode", -1));
            setQuit(toQuit);
            if(getQuit()){
                endGameQuit(null);
                return;
            }
            setNumGames(getNumGames() + 1);
            int player = whoGoesFirst();
            initializeNewGame(player);
        }

        // For coming back from choosing who goes first in single player
        else if(requestCode == 4){
            int playerToGo = data.getIntExtra("playerToGo", 0);
            setGameMode(data.getIntExtra("gameMode", -1));
            if(playerToGo == -1){
                endGameQuit(null);
                return;
            }
            setPlayer(playerToGo);
            initializeNewGame(getPlayer());
            if(getPlayer() == 1) {
                moveTL(null);
            }
        }
    }

}
