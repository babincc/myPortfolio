package com.uxuiclass.vcuscavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This page shows information about the app. Depending on the info button the user
 * pressed to get here, a different info mesage will be shown.
 */
public class Info extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button backBtn;
    private Button homeBtn;
    private TextView infoTextView;

    // Which class called to info
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Initiating everything that can be seen on screen
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);
        infoTextView = findViewById(R.id.infoTextView);
        infoTextView.setMovementMethod(new ScrollingMovementMethod());

        // Initiating the caller class
        from = getIntent().getStringExtra("from");

        // Starts the process of displaying the relevant information to the user
        setInfoTextView();
    }

    /**
     * This method checks to see which class called to this info class and calls to the appropriate
     * method to display the message.
     */
    private void setInfoTextView() {
        if (from.equals("MainActivity")) {
            // No need to show home button if we are coming from the home screen
            homeBtn.setVisibility(View.INVISIBLE);
            msgMainActivity();
        } else if(from.equals("addUsername")) {
            msgAddUsername();
        } else if(from.equals("Question")) {
            msgQuestion();
        } else if(from.equals("Hunt_Categories")) {
            msgHuntCategories();
        } else if(from.equals("Specific_Category")) {
            msgSpecificCategory();
        } else {
            msgError(); // This means the info page was accidentally summoned from some unknown place (this should be impossible)
        }
    }

    /**
     * This method displays the message meant to help with the welcome/home page.
     */
    private void msgMainActivity() {
        String msg = "<h3>About:</h3><p>The goal of this app is to make the user more familiar with VCU. You will learn some history about the school, where to go for fun, places to eat, and much more! Once you finish the game, you should be comfortable on the VCU campus.</p><br><h3>Instructions:</h3><p>Once you click \"Play\" you will be shown a list of categories. You can choose and category and start hunting! Inside of each category are lists of places to find. You click on a place and are given a question about that place. Each question will have an answer that will become obvious once you arrive at the proper location. Once you have your answer, you can submit it to find out if you are correct. Correct answers will be rewarded Rodney Ram coins (you can count your coins in \"Profile\"). The game is complete once you find every location and correctly answer each question.</p><br><h3>Penalties:</h3><p>Each question is initially worth 100 Rodney Ram coins; however, each time you answer a question incorrectly, its value drops by 10 coins.</p><br><br>";
        infoTextView.setText(Html.fromHtml(msg));
    }

    /**
     * This method displays the message meant to help with the adding username page.
     */
    private void msgAddUsername() {
        String msg = "Your username is what you will be called when you use this app. It can be changed at any time.\n\nNote: There is a 10 character limit.";
        infoTextView.setText(msg);
    }

    /**
     * This method displays the message meant to help the user know how to answer the questions.
     */
    private void msgQuestion() {
        String msg = "<h3>Answer Tips:</h3><p>Try to answer in as few words as possible for best results. This app is only set to accept certain answers. Extra words and miss-spellings will confuse it.</p><br><h3>Penalties:</h3><p>Each question is initially worth 100 Rodney Ram coins; however, each time you answer a question incorrectly, its value drops by 10 coins.</p><br><h3>Already Answered:</h3><<p>Once you have correctly answered a question, it will display your correct answer in the answer box (which will no longer be editable). There will also be a checkmark to signify that you have correctly answered this question already.</p><br><br>";
        infoTextView.setText(Html.fromHtml(msg));
    }

    /**
     * This method displays the message meant to help the user know how what the categories mean.
     */
    private void msgHuntCategories() {
        String msg = "These are the hunt categories. Each one contains a list of locations specific to that category.";
        infoTextView.setText(msg);
    }

    /**
     * This method displays the message meant to help the user know how what the locations mean.
     */
    private void msgSpecificCategory() {
        String msg = "These are the locations. Each one contains a question about a specific location on/near the VCU Monroe Park campus.";
        infoTextView.setText(msg);
    }

    /**
     * This method displays an error message when the caller class is unidentified.
     */
    private void msgError() {
        String msg = "Hmmm? Something has gone wrong. Please go back and try again.";
        infoTextView.setText(msg);
    }

    /**
     * This method goes back to the page that called this one.
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
