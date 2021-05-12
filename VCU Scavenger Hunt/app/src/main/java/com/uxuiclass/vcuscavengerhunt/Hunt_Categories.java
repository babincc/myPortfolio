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
import java.util.List;

/**
 * This page shows all of the scavenger hunt categories. The user can scroll through and click 
 * one to start hunting in that category.
 */
public class Hunt_Categories extends AppCompatActivity {

    // Declare everything that can be seen on screen
    private Button backBtn;
    private ListView categoryList;

    // These allow interaction with the database
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "Hunt_Categories";

    // Helps build the ListView
    private List<String> categories;
    private MyAdapter myAdapter;
    private String[] tempCats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunt__categories);

        // Initiating everything that can be seen on screen
        backBtn = findViewById(R.id.backBtn);
        categoryList = findViewById(R.id.categoryList);

        // Initialize database interaction
        mDatabaseHelper = new DatabaseHelper(this);

        // All of the categories will go in here
        categories = new ArrayList<String>();

        // Put the categories into the ListView
        populateListView();

        // Watch the ListView to see what is clicked on
        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Go to the location that was clicked on
                goToLocations(i);
            }
        });
    }

    /**
     * This method sends the user to the category they clicked on.
     *
     * @param i - The ID of the category they clicked on
     */
    private void goToLocations(int i) {
        Intent goToLocationListIntent = new Intent(this, Specific_Category.class);

        // Send over the category
        String cat = categories.get(i);
        goToLocationListIntent.putExtra("cat", cat);

        final int result = 922;

        startActivityForResult(goToLocationListIntent, result);
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
        String temp;
        while(data.moveToNext()) {

            // The current category we are looking at in the database
            temp = data.getString(1);

            // If this category hasn't been listed yet, list it
            if(!categories.contains(temp)){
                categories.add(temp);
            }
        }

        // Convert the array list into a regular array so it can be displayed
        tempCats = new String[categories.size()];
        tempCats = categories.toArray(tempCats);

        // Set the adapter so the info can be displayed
        myAdapter = new MyAdapter(this, tempCats);
        categoryList.setAdapter(myAdapter);
    }

    /**
     * This method sends the user back to wherever they were before
     *
     * @param view - this current state of the app
     */
    public void onClickBack(View view) {
        Intent goBack = new Intent();

        setResult(RESULT_OK, goBack);

        finish();
    }

    /**
     * This method sends the user to the info/help page to find out what the categories mean.
     *
     * @param view - this current state of the app
     */
    public void onClickInfo(View view) {
        Intent infoIntent = new Intent(this, Info.class);

        infoIntent.putExtra("from", "Hunt_Categories");

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
            if (requestCode == 922) { // Returning from locations
                if(data.getBooleanExtra("goHome", false)){
                    onClickBack(null);
                }

                // Re-draw the list in case there are any new checkmarks
                populateListView();
            } else if (requestCode == 513) { // Returning from map
                if(data.getBooleanExtra("goHome", false)){
                    onClickBack(null);
                }
            } else if (requestCode == 20) { // Returning from info
                if(data.getBooleanExtra("goHome", false)){
                    onClickBack(null);
                }
            }
        }
    }

    /**
     * This class designs the template for each item in the ListView.
     */
    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String cat[];

        MyAdapter(Context c, String tempCats[]) {
            super(c, R.layout.custom_listview_layout, tempCats);
            this.context = c;
            this.cat = tempCats;
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
            if(cat[position].equals("Historical")) {
                icon.setImageResource(R.drawable.historical_icon);
            } else if(cat[position].equals("Activities")) {
                icon.setImageResource(R.drawable.activities_icon);
            } else if(cat[position].equals("Notable Places")) {
                icon.setImageResource(R.drawable.notable_places_icon);
            } else if(cat[position].equals("Educational Buildings")) {
                icon.setImageResource(R.drawable.educational_buildings_icon);
            } else if(cat[position].equals("Miscellaneous")) {
                icon.setImageResource(R.drawable.miscellaneous_icon);
            } else if(cat[position].equals("Food")) {
                icon.setImageResource(R.drawable.food_icon);
            }

            // If every question in the category has been answered, put a checkmark on the category
            if(catIsComplete(cat[position])) {
                myRelativeLayout.setBackgroundResource(R.drawable.answered_button);
            }

            // Put the category name in this list item
            categoryTextView.setText(cat[position]);

            return row;
        }

        /**
         * This method checks to see if the category has been completed.
         *
         * @param cat - The category we are checking on
         * @return True if the category has had all of its questions answered
         */
        private boolean catIsComplete(String cat){

            // The cursor used to look through the database
            Cursor data = mDatabaseHelper.getData();

            // The category we are looking at in the database at the moment
            String tempCat;

            // The answer to whether or not the question we are on in the database has been solved
            int temp;

            // This loop searches the entire database
            while(data.moveToNext()) {
                tempCat = data.getString(1);
                temp = data.getInt(8);

                // If we come across a question in this category that has not been answered, go ahead and return false
                if(tempCat.equals(cat) && temp != 1){
                    return false;
                }
            }

            // Otherwise, return true
            return true;
        }

    }

}
