package com.example.tic_tac_toe;

import androidx.test.espresso.action.ViewActions;
import androidx.test.runner.AndroidJUnit4;

import android.view.KeyEvent;

import org.junit.Rule;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Test;

import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<>(MainActivity.class);

/* ************************************************************************************************
 *
 *         ITERATION 1 - These are the tests for the user stories in the first iteration
 *
 ************************************************************************************************/


    /* ***********************************
     * User Story
     *   Learn how to play #4
     */

    /**
     * Scenario
     *   Player has never played before  &&  Argument over win/loss  &&  See how the machine will play
     *
     *   Since the only thing on the screen is the instructions, this test covers the first
     *      3 scenarios
     */
    @Test
    public void testInstructions() {
        onView(withId(R.id.instructionsButton)).perform(click());
        onView(withId(R.id.instructionsText)).check(matches(withId(R.id.instructionsText)));
    }

    /**
     * Scenario
     *   Finished looking at instructions
     */
    @Test
    public void testInstructionsBack() {
        onView(withId(R.id.instructionsButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.instructionsButton)).check(matches(withId(R.id.instructionsButton)));
    }





    /* ***********************************
     * User Story
     *   Start game #6
     */

    /**
     * Scenario
     *   Giving it to a friend  &&  Showing I’m a genius
     *
     *   Since this user story covers starting a game in all three game modes (multiplayer,
     *      single player hard, and single player easy), we will not be able to test each
     *      scenario. The only functional scenario from this user story, in this iteration,
     *      is multiplayer.
     *
     *
     * Added in Iteration 2 - Giving it to a friend
     */
    @Test
    public void testStartGame_singlePlayerHard(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("Player Name")));
    }
    /**
     * Added in Iteration 3 - Showing I’m a genius
     */
    @Test
    public void testStartGame_singlePlayerEasy(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.singlePlayerPrompt)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_W));
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Ariana")));
    }

    /**
     * Scenario
     *   Multiplayer
     */
    @Test
    public void testStartGame_Multiplayer(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("Player 1 Name")));
    }





    /* ***********************************
     * User Story
     *   Multiplayer #3
     */

    /* *
     * Scenario
     *   Choosing multiplayer
     *
     *   This is covered by testStartGame_Multiplayer()
     */

    /**
     * Scenario
     *   Player 1
     */
    @Test
    public void testMultiplayer_Player1(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("John")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Sara")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn John")));
    }

    /**
     * Scenario
     *   Player 2
     */
    @Test
    public void testMultiplayer_Player2(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("John")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Sara")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Sara")));
    }





    /* ***********************************
     * User story
     *   Keep score #5
     */

    /**
     * Scenario
     *   Player wins (player 1)
     */
    @Test
    public void testKeepScore_PlayerWins(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("John")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Sara")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        onView(withId(R.id.topRight)).perform(click());
        onView(withId(R.id.bottomLeft)).perform(click());
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.scoreBoardDisplay)).check(matches(withText("John: 1     Sara: 0     Ties: 0")));
    }

    /**
     * Scenario
     *   Player wins (player 2)
     */
    @Test
    public void testKeepScore_PlayerWins2(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("John")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Sara")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        onView(withId(R.id.center)).perform(click());
        onView(withId(R.id.topRight)).perform(click());
        onView(withId(R.id.bottomMiddle)).perform(click());
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.scoreBoardDisplay)).check(matches(withText("John: 0     Sara: 1     Ties: 0")));
    }

    /**
     * Scenario
     *   The computer wins
     *
     *   This can not be tested in this iteration because it is part of a functionality that will
     *      come in another iteration
     *
     *
     * Added in Iteration 2 - The computer wins
     */
    @Test
    public void testKeepScore_computerWins(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.scoreBoardDisplay)).check(matches(withText("Ryan: 0     Computer: 1     Ties: 0")));
    }

    /**
     * Scenario
     *   It is a tie
     */
    @Test
    public void testKeepScore_Tie(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("John")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Sara")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.topRight)).perform(click());
        onView(withId(R.id.bottomLeft)).perform(click());
        onView(withId(R.id.bottomMiddle)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        onView(withId(R.id.center)).perform(click());
        onView(withId(R.id.middleRight)).perform(click());
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.scoreBoardDisplay)).check(matches(withText("John: 0     Sara: 0     Ties: 1")));
    }


/* ************************************************************************************************
 *
 *         ITERATION 2 - These are the tests for the user stories in the second iteration
 *
 ************************************************************************************************/


    /* ***********************************
     * User story
     *   Choose who goes first #9
     */

    /**
     * Scenario
     *   Player goes first
     */
    @Test
    public void testPlayerOrder_playerFirst(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Ryan")));
    }

    /**
     * Scenario
     *   Computer goes first
     */
    @Test
    public void testPlayerOrder_computerFirst(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.topLeft)).check(matches(withText("O")));
    }

    /**
     * Scenario
     *   Multiplayer (player 1 first)
     *   Note: Player 1 always starts first. It is only after player 1 wins that player 2 will start
     */
    @Test
    public void testPlayerOrder_player1First(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Chrissy")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Ryan")));
    }

    /**
     * Scenario
     *   Multiplayer (player 2 first)
     *   Note: Player 1 always starts first. It is only after player 1 wins that player 2 will start
     */
    @Test
    public void testPlayerOrder_player2First(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Chrissy")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        onView(withId(R.id.topRight)).perform(click());
        onView(withId(R.id.bottomLeft)).perform(click());
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Chrissy")));
    }





    /* ***********************************
     * User story
     *   Play tic-tac-toe #2
     */

    /**
     * Scenario
     *   Challenge myself
     *
     *   Tested seven different ways to make sure the computer is winning consistently
     */
    @Test
    public void testGamePlay_hard1(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.middleRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomLeft)).perform(click());
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }

    @Test
    public void testGamePlay_hard2(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.center)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }

    @Test
    public void testGamePlay_hard3(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.middleRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }

    @Test
    public void testGamePlay_hard4(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.center)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }

    @Test
    public void testGamePlay_hard5(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("You lost! Better luck next time!")));
    }

    @Test
    public void testGamePlay_hard6(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }

    @Test
    public void testGamePlay_hard7(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("You lost! Better luck next time!")));
    }

    /*
     * Scenario
     *   Practice
     *
     *   This functionality cannot be tested now. It will be coming in a future iteration.
     */

    /**
     * Scenario
     *   Multiplayer
     */
    @Test
    public void testGamePlay_multiplayer(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Chrissy")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        onView(withId(R.id.topRight)).perform(click());
        onView(withId(R.id.bottomLeft)).perform(click());
        onView(withId(R.id.multiWinner)).check(matches(withText("Congrats Ryan!")));
    }





    /* ***********************************
     * User story
     *   Quit #8
     */

    /**
     * Scenario
     *   From game
     */
    @Test
    public void testQuit_duringGamePlayMulti(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Chrissy")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.quitBtn)).perform(click());
        onView(withId(R.id.title)).check(matches(withHint("tic-tac-toe")));
    }

    @Test
    public void testQuit_duringGamePlaySingle(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.quitBtn)).perform(click());
        onView(withId(R.id.title)).check(matches(withHint("tic-tac-toe")));
    }

    /**
     * Scenario
     *   From victory screen
     */
    @Test
    public void testQuit_afterGameFinishesWin(){
        onView(withId(R.id.multiplayerButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.player2)).perform(typeText("Chrissy")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.topLeft)).perform(click());
        onView(withId(R.id.topMiddle)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        onView(withId(R.id.topRight)).perform(click());
        onView(withId(R.id.bottomLeft)).perform(click());
        onView(withId(R.id.quit)).perform(click());
        onView(withId(R.id.title)).check(matches(withHint("tic-tac-toe")));
    }

    /**
     * Scenario
     *   From loss and tie screen
     */
    @Test
    public void testQuit_afterGameFinishesLossOrTie(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ryan")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
        onView(withId(R.id.quit)).perform(click());
        onView(withId(R.id.title)).check(matches(withHint("tic-tac-toe")));
    }

    /*
     * Scenario
     *   See the number of games played
     *
     *   This scenario was removed because it is better to have this information displayed the
     *   whole time rather than just at the end
     */


    /* ************************************************************************************************
     *
     *         ITERATION 3 - These are the tests for the user stories in the third iteration
     *
     ************************************************************************************************/


    /* ***********************************
     * User Story
     *   Play a prank #1
     */

    /**
     * Scenario
     *   Give the game to a friend
     */
    @Test
    public void testPrankFriendStep1() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }

    /**
     * Scenario
     *   Showing the friend that I am a genius
     */
    @Test
    public void testPrankFriendStep2() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_W));
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.center)).perform(click());
        onView(withId(R.id.multiWinner)).check(matches(withText("Congrats Ariana!")));
    }

    /**
     * Scenario
     *   They try again
     */
    @Test
    public void testPrankFriendStep3() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_W));
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.center)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_W));
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }





    /* ***********************************
     * User story
     *   See achievement #10
     */

    /**
     * Scenario
     *   Win
     */
    @Test
    public void testAffirmationCongrats(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.singlePlayerPrompt)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_W));
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.center)).perform(click());
        onView(withId(R.id.multiWinner)).check(matches(withText("Congrats Ariana!")));
    }

    /**
     * Scenario
     *   Lose
     */
    @Test
    public void testAffirmationYouLose(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("You lost! Better luck next time!")));
    }

    /**
     * Scenario
     *   Tie
     */
    @Test
    public void testAffirmationTieGame(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.multiWinner)).check(matches(withText("Tie!")));
    }





    /* ***********************************
     * User story
     *   Play again #7
     */

    /**
     * Scenario
     *   On a loss
     */
    @Test
    public void testTryAgainAfterLoss(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Ariana")));
    }

    /**
     * Scenario
     *   On a win
     */
    @Test
    public void testTryAgainAfterWin(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.singlePlayerPrompt)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_W));
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.center)).perform(click());
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Ariana")));
    }

    /**
     * Scenario
     *   On a tie
     */
    @Test
    public void testTryAgainAfterTie(){
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.player1)).perform(click());
        onView(withId(R.id.player1)).perform(typeText("Ariana")).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.plyButton)).perform(click());
        onView(withId(R.id.compFirst)).perform(click());
        onView(withId(R.id.middleLeft)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.topRight)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.bottomMiddle)).perform(click());
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println("Computer died! Need to restart.");}
        onView(withId(R.id.playAgain)).perform(click());
        onView(withId(R.id.playerFirst)).perform(click());
        onView(withId(R.id.whosTurn)).check(matches(withText("Your turn Ariana")));
    }

}