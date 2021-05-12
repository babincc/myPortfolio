package com.uxuiclass.vcuscavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Scanner;

/**
 * This class is the question screen. It displays a question to the user and gives them
 * a space to answer it.
 */
public class Question extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button infoBtn;
    private Button backBtn;
    private TextView locationTextView;
    private TextView instructionTextView;
    private ImageView imageImageView;
    private TextView questionTextView;
    private EditText answerEditText;
    private ImageView checkImageView;
    private Button submitBtn;
    private TextView pointsTextView;

    // User information
    private String userName;
    private int numCoins;

    // Keeps track of user information after app is closed
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_COINS = "coins";

    // These allow interaction with the database
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "Hunt_Categories";

    // All of the information associated with each entry
    private int id;
    private String category;
    private String location;
    private String instructions;
    private String image;
    private String question;
    private String[] answer; // Since the answer needs to be exact, this is a list of possible correct ones
    private int questionValue;
    private boolean isAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Getting ready to store user info
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initialize correct answers array
        answer = new String[50];

        // Initialize database interaction
        mDatabaseHelper = new DatabaseHelper(this);

        // Initiating everything that can be seen on screen
        infoBtn = findViewById(R.id.infoBtn);
        backBtn = findViewById(R.id.backBtn);
        locationTextView = findViewById(R.id.locationTextView);
        instructionTextView = findViewById(R.id.instructionTextView);
        imageImageView = findViewById(R.id.imageImageView);
        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        checkImageView = findViewById(R.id.checkImageView);
        submitBtn = findViewById(R.id.submitBtn);
        pointsTextView = findViewById(R.id.pointsTextView);

        // The location value of the entry we want
        location = getIntent().getStringExtra("location");

        // Grabbing the user information and setting it
        numCoins = sharedPreferences.getInt(KEY_COINS, -1);

        // How many coins the question is worth
        questionValue = sharedPreferences.getInt(KEY_COINS, -1);

        // Retrieving the question from the database
        getQuestion();

        // Displaying the question for the user
        putQuestionOnScreen();

        // Checks to see if an answer has been entered before displaying the submit button
        answerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            /**
             * This method shows or hides the submit button depending on if there is an answer or
             * not that has been provided by they user.
             *
             * @param s         - The letters in the EditText view
             * @param start     - Where in the CharSequence the changes began
             * @param before    - The old CharSequence length
             * @param count     - The new lenght
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // It will only let the submit button be visible if there are letters in the textbox
                if(s.length() != 0 && s.toString().matches("[^\\s]+.*")) {
                    submitBtn.setVisibility(View.VISIBLE);
                } else {
                    submitBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * This method retrieves the question data from the database.
     */
    private void getQuestion() {

        // Log for debugging
        Log.d(TAG, "populateListView: Displaying data in the list view.");

        // The cursor used to look through the database
        Cursor data = mDatabaseHelper.getData();

        // The location we are currently on in the database
        String loc;

        // This loop searches the entire database
        while(data.moveToNext()) {

            // Set loc to the current location we are looking at in the database
            loc = data.getString(2);

            // If we are looking at the correct location in the database, stop here
            if(location.equals(loc)){
                break;
            }
        }

        // Set all of the question data to the current position in the database
        id = data.getInt(0);
        category = data.getString(1);
        instructions = data.getString(3);
        image = data.getString(4);
        question = data.getString(5);
        String Ans = data.getString(6); // Grab the raw answer string
        fillAnsArray(Ans); // Break the raw answer string into an array
        questionValue = data.getInt(7);
        isAnswered = data.getInt(8) == 1; // Booleans are stored as ints in the database; so, this converts it
    }

    /**
     * This method takes the raw answer string and converts it into an array for easy comparisons
     * later.
     *
     * @param Ans - The raw answer string. This is a string that contains every feasible correct
     *            answer the user may enter.
     */
    private void fillAnsArray(String Ans) {
        Scanner ansReader = new Scanner(Ans).useDelimiter("\\s\\|\\|\\s");

        // This loop goes through the string and breaks it apart at each " || " and puts each piece into its own spot in the array
        int i = 0;
        while(ansReader.hasNext()) {
            answer[i] = ansReader.next();
            i++;
        }
    }

    /**
     * This method displays the question on the screen for the user.
     */
    private void putQuestionOnScreen() {
        locationTextView.setText(location);

        instructionTextView.setText(instructions);

        // Get the image based on the reference in the database
        getImg();

        questionTextView.setText(question);

        // This will be used to tell the user the value of the question
        String tempPtsStr;

        // If the quesiton has been answered, don't let them edit the answer anymore
        if(isAnswered) {

            // Show them what the answer was and disable editing
            answerEditText.setText(answer[0]);
            answerEditText.setEnabled(false);

            // make text underline invisible
            answerEditText.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));


            // Put a checkmark by the answer to show it is correct
            checkImageView.setVisibility(View.VISIBLE);

            // Tell them how much they earned rather than how much it is worth
            tempPtsStr = "You earned " + Integer.toString(questionValue) + " coins for this question";
        } else {

            // Tell them how much it is worth
            tempPtsStr = "Question worth " + Integer.toString(questionValue) + " coins";
        }

        pointsTextView.setText(tempPtsStr);
    }

    /**
     * This method displays the image that matches the reference in the database.
     */
    private void getImg(){
        if(image == null) {
            imageImageView.setImageResource(0); // Shows no image if there is none in the database
        } else if(image.equals("altria_points")) {
            imageImageView.setImageResource(R.drawable.altria_points);
        } else if(image.equals("mp_map")) {
            imageImageView.setImageResource(R.drawable.mp_map);
        } else if(image.equals("shafer")) {
            imageImageView.setImageResource(R.drawable.shafer);
        } else if(image.equals("library_number")) {
            imageImageView.setImageResource(R.drawable.library_number);
        } else if(image.equals("parking_deck")) {
            imageImageView.setImageResource(R.drawable.parking_deck);
        } else if(image.equals("cathedral_roman_numerals")) {
            imageImageView.setImageResource(R.drawable.cathedral_roman_numerals);
        } else if(image.equals("church_door")) {
            imageImageView.setImageResource(R.drawable.church_door);
        } else if(image.equals("gym_words")) {
            imageImageView.setImageResource(R.drawable.gym_words);
        } else if(image.equals("residence_hall")) {
            imageImageView.setImageResource(R.drawable.residence_hall);
        } else if(image.equals("roof_garden")) {
            imageImageView.setImageResource(R.drawable.roof_garden);
        } else if(image.equals("ram_horns")){
            imageImageView.setImageResource(R.drawable.ram_horns);
        } else if(image.equals("bn_starbucks")){
            imageImageView.setImageResource(R.drawable.bn_starbucks);
        } else if(image.equals("chick_fil_a")){
            imageImageView.setImageResource(R.drawable.chick_fil_a);
        } else if(image.equals("christians_pizza")){
            imageImageView.setImageResource(R.drawable.christians_pizza);
        } else if(image.equals("hollywood_book")){
            imageImageView.setImageResource(R.drawable.hollywood_book);
        } else if(image.equals("insomnia_seating")){
            imageImageView.setImageResource(R.drawable.insomnia_seating);
        } else if(image.equals("jumbotron")){
            imageImageView.setImageResource(R.drawable.jumbotron);
        } else if(image.equals("learning_garden")){
            imageImageView.setImageResource(R.drawable.learning_garden);
        } else if(image.equals("metal_desks")){
            imageImageView.setImageResource(R.drawable.metal_desks);
        } else if(image.equals("president_house")){
            imageImageView.setImageResource(R.drawable.president_house);
        } else if(image.equals("pyramid")){
            imageImageView.setImageResource(R.drawable.pyramid);
        } else if(image.equals("ramtech_circles")){
            imageImageView.setImageResource(R.drawable.ramtech_circles);
        } else if(image.equals("redeye_doors")){
            imageImageView.setImageResource(R.drawable.redeye_doors);
        } else if(image.equals("spiderlady")){
            imageImageView.setImageResource(R.drawable.spiderlady);
        } else if(image.equals("rip_odd")){
            imageImageView.setImageResource(R.drawable.rip_odd);
        } else if(image.equals("vcu_biz")){
            imageImageView.setImageResource(R.drawable.vcu_biz);
        } else if(image.equals("chilis_booths")){
            imageImageView.setImageResource(R.drawable.chilis_booths);
        } else if(image.equals("trani_roof")){
            imageImageView.setImageResource(R.drawable.trani_roof);
        } else if(image.equals("temple_bridges")){
            imageImageView.setImageResource(R.drawable.temple_bridges);
        } else if(image.equals("harris_hall_art")){
            imageImageView.setImageResource(R.drawable.harris_hall_art);
        } else if(image.equals("writing_center")){
            imageImageView.setImageResource(R.drawable.writing_center);
        } else if(image.equals("hibbs_hall_sign")){
            imageImageView.setImageResource(R.drawable.hibbs_hall_sign);
        } else if(image.equals("jefferson")){
            imageImageView.setImageResource(R.drawable.jefferson);
        } else if(image.equals("milk")){
            imageImageView.setImageResource(R.drawable.milk);
        } else if(image.equals("cava_soda")){
            imageImageView.setImageResource(R.drawable.cava_soda);
        } else if(image.equals("panera_mex")){
            imageImageView.setImageResource(R.drawable.panera_mex);
        } else if(image.equals("dominos_bandw")){
            imageImageView.setImageResource(R.drawable.dominos_bandw);
        } else if(image.equals("jimmy_johns")){
            imageImageView.setImageResource(R.drawable.jimmy_johns);
        } else if(image.equals("honors_windows")){
            imageImageView.setImageResource(R.drawable.honors_windows);
        } else if(image.equals("art_inst_words")){
            imageImageView.setImageResource(R.drawable.art_inst_words);
        } else if(image.equals("rambikes_hidden_store")){
            imageImageView.setImageResource(R.drawable.rambikes_hidden_store);
        }
    }

    /**
     * This method checks the user's answer, when they submit, to see if they got it right or wrong.
     *
     * @param view - This current state of the app
     */
    public void onSubmit(View view) {

        // Get what the user typed in
        String userAns = answerEditText.getText().toString().trim();

        // Go through the answer array to see if it matches anything
        for(String ans : answer) {
            if(ans != null && ans.equals(userAns.toLowerCase())) {

                // If it does, then they are correct
                onCorrect(userAns);
                return;
            }
        }

        // Otherwise, they are wrong
        onQuestionFail();
    }

    /**
     * This method is called when the user gets the question correct. It adds the coins to their
     * account and sets the question as answered.
     */
    private void onCorrect(String userAns) {

        // Adds the coin value to the users current coin value
        numCoins += questionValue;

        // Stores the new coin value in shared memory
        editor.putInt(KEY_COINS, numCoins);
        editor.apply();

        // Sets the question to solved
        isAnswered = true;

        // Sets the raw answer string to the user's answer so it can be displayed if they click this location again
        answer[0] = userAns;

        // Puts the changes in the database
        applyDBChanges();

        // Tells the user what it means to be correct
        String message = "+" + questionValue;

        // Display the results on screen
        goToAnswered("correct", message);
    }

    /**
     * This method is called if the question is answered incorrectly.
     */
    private void onQuestionFail() {

        // Telling the user what it means to be incorrect
        String message;

        // If the question is worth coins, take 10 away
        // Otherwise, leave coins alone (we don't want to go negative)
        if(questionValue > 0) {
            questionValue -= 10;
            applyDBChanges();
            message = "Question value\n-10 coins.\n\nBe careful!";
        } else {
            message = "Be careful!";
        }

        // re-print the coin value
        String tmp = "Question worth " + Integer.toString(questionValue) + " coins";
        pointsTextView.setText(tmp);

        // Display the results on screen
        goToAnswered("Incorrect", message);
    }

    /**
     * This method changes the entry in the database to hold the new user info.
     */
    private void applyDBChanges() {
        mDatabaseHelper.updateData(id, category, location, instructions, image, question, answer[0], questionValue, isAnswered);
    }

    /**
     * This method takes the user to the page that shows them if they are right or wrong.
     *
     * @param grade     - Either "correct" or "incorrec"
     * @param message   - Tells the user what it means to be right or wrong
     */
    private void goToAnswered(String grade, String message) {
        Intent getJudgedIntent = new Intent(this, answeredQuestion.class);

        getJudgedIntent.putExtra("grade", grade);
        getJudgedIntent.putExtra("msg", message);

        final int result = 810;

        startActivityForResult(getJudgedIntent, result);
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

    /**
     * This method sends the user to the map page to see the map.
     *
     * @param view - this current state of the app
     */
    public void onClickMap(View view) {
        Intent mapIntent = new Intent(this, map.class);

        final int result = 513;

        startActivityForResult(mapIntent, result);
    }

    /**
     * This method sends the user to the info/help page to find out how to answer the questions.
     *
     * @param view - this current state of the app
     */
    public void onClickInfo(View view) {
        Intent infoIntent = new Intent(this, Info.class);

        infoIntent.putExtra("from", "Question");

        final int result = 20;

        startActivityForResult(infoIntent, result);
    }

    /**
     * This method handles returns from other pages.
     *
     * @param requestCode   - To identify what was just called and returned
     * @param resultCode    - Tells if everything went okay
     * @param data          - The information sent along from the other activity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 810) { // Returning from grade

                // Checks to see if the user was right or wrong
                String grade = data.getStringExtra("grade");
                if(grade.equals("correct")){

                    // If they were right, go back to locations screen
                    onClickBack(null);
                } else { // Otherwise, stay here

                    // reset the answer box to blank
                    answerEditText.setText("");
                }
            } else if (requestCode == 513) { // Returning from map
                if(data.getBooleanExtra("goHome", false)){
                    onClickHome(null);
                }
            } else if (requestCode == 20) { // Returning from info
                if(data.getBooleanExtra("goHome", false)){
                    onClickHome(null);
                }
            }
        }
    }

}
