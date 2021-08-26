package com.uxuiclass.vcuscavengerhunt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class is a page that shows all of the questions for a specific category.
 */
public class Specific_Category extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button backBtn;
    private ListView locationList;

    // These allow interaction with the database
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "Specific_Category";

    // Helps build the ListView
    private String cat;
    private ArrayList<String> locations;
    private MyLocAdapter myAdapter;
    private String[] tempLocs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific__category);

        // Initiating everything that can be seen on screen
        backBtn = findViewById(R.id.backBtn);
        locationList = findViewById(R.id.locationList);

        // Initialize database interaction
        mDatabaseHelper = new DatabaseHelper(this);

        // The category that was passed along
        cat = getIntent().getStringExtra("cat");

        // All of the locations will go in here
        locations = new ArrayList<>();

        // Put the locations into the ListView
        populateListView();

        // Watch the ListView to see what is clicked on
        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToQuestion(i);
            }
        });

    }

    /**
     * This method sends the user to the location they clicked on.
     *
     * @param i - The ID of the location they clicked on
     */
    private void goToQuestion(int i) {
        Intent goToQuestionIntent = new Intent(this, Question.class);

        // Send over the location
        String location = locations.get(i);
        goToQuestionIntent.putExtra("location", location);

        final int result = 729;

        startActivityForResult(goToQuestionIntent, result);
    }

    /**
     * This method displays the categories in the ListView.
     */
    private void populateListView() {

        // Log for debugging
        Log.d(TAG, "populateListView: Displaying data in the list view.");

        // The cursor used to look through the database
        Cursor data = mDatabaseHelper.getData();

        // This loop searches the entire database
        while(data.moveToNext()) {

            // The current category we are looking at in the database
            String tempCat = data.getString(1);

            // The current location we are looking at in the database
            String tempLocation = data.getString(2);

            // Only get the locations from the correct category
            if(tempCat.equals(cat) && !locations.contains(tempLocation)) {
                locations.add(tempLocation);
            }
        }

        // Convert the array list into a regular array so it can be displayed
        tempLocs = new String[locations.size()];
        tempLocs = locations.toArray(tempLocs);

        // Set the adapter so the info can be displayed
        myAdapter = new MyLocAdapter(this, tempLocs);
        locationList.setAdapter(myAdapter);
    }

    /**
     * This method sends the user back to wherever they were before
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
     * This method sends the user to the info/help page to find out what the locations mean.
     *
     * @param view - this current state of the app
     */
    public void onClickInfo(View view) {
        Intent infoIntent = new Intent(this, Info.class);

        infoIntent.putExtra("from", "Specific_Category");

        final int result = 20;

        startActivityForResult(infoIntent, result);
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
            if (requestCode == 729) { // Returning from question
                if(data.getBooleanExtra("goHome", false)){
                    onClickHome(null);
                }

                // Re-draw the list in case there are any new checkmarks
                populateListView();
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

    /**
     * This class designs the template for each item in the ListView.
     */
    class MyLocAdapter extends ArrayAdapter<String> {

        Context context;
        String location[];

        MyLocAdapter(Context c, String tempLocations[]) {
            super(c, R.layout.custom_listview_layout, tempLocations);
            this.context = c;
            this.location = tempLocations;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.custom_listview_layout, parent, false);

            // Declare and initialize everyting that will be seen on screen
            RelativeLayout myRelativeLayout = row.findViewById(R.id.myRelativeLayout);
            TextView categoryTextView = row.findViewById(R.id.categoryTextView);
            ImageView icon = row.findViewById(R.id.icon);

            // Display the proper icon
            if(cat.equals("Historical")) {
                icon.setImageResource(R.drawable.historical_icon);
            } else if(cat.equals("Activities")) {
                icon.setImageResource(R.drawable.activities_icon);
            } else if(cat.equals("Notable Places")) {
                icon.setImageResource(R.drawable.notable_places_icon);
            } else if(cat.equals("Educational Buildings")) {
                icon.setImageResource(R.drawable.educational_buildings_icon);
            } else if(cat.equals("Miscellaneous")) {
                icon.setImageResource(R.drawable.miscellaneous_icon);
            } else if(cat.equals("Food")) {
                icon.setImageResource(R.drawable.food_icon);
            }

            // If this question has been answered, put a checkmark on it
            if(questionIsAnswered(location[position])) {
                myRelativeLayout.setBackgroundResource(R.drawable.answered_button);
            }

            // Put the location name in this list item
            categoryTextView.setText(location[position]);

            return row;
        }

        /**
         * This method checks to see if the location question has been answered.
         *
         * @param loc - The location we are checking on
         * @return True if the category has had all of its questions answered
         */
        private boolean questionIsAnswered(String loc){

            // The cursor used to look through the database
            Cursor data = mDatabaseHelper.getData();

            // The location we are looking at in the database at the moment
            String tempLoc;

            // The answer to whether or not the question we are on in the database has been solved
            int temp;

            // This loop searches the entire database
            while(data.moveToNext()) {
                tempLoc = data.getString(2);
                temp = data.getInt(8);

                // If the question has been solved, return true
                if(tempLoc.equals(loc) && temp == 1){
                    return true;
                }
            }

            // Otherwise, return false
            return false;
        }

    }

}
