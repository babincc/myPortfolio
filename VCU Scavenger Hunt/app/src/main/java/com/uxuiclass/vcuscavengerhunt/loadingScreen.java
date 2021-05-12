///////////////////////////////////////////////////////////////////////////////////////////////////
//
//                                       IMPORTANT NOTE!
//
//     When an update is made to the database, a new method will be added to insert the data.
//                  If it goes into an existing method, it will not be added.
//                          Each method will call to the next one.
//
//                    Also, the DB_NUM on the MainActivity page will go up
//                    to match the version number on the last method here.
//
//                 Also, the image will have to be added to the Question page
//                                   in the getImg() method.
//
//
///////////////////////////////////////////////////////////////////////////////////////////////////


package com.uxuiclass.vcuscavengerhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/**
 * This is the splash screen for the app. It initializes the application so it is
 * ready for the user once they get to the home screen.
 */
public class loadingScreen extends AppCompatActivity {

    // Keeps track of user information after app is closed
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DB_NUM = "dbNum";

    DatabaseHelper mDatabaseHelper;
    private static final String TAG = "loadingScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        // Getting ready to store user info
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mDatabaseHelper = new DatabaseHelper(this);
        mDatabaseHelper.getWritableDatabase();

        // The version of the database that the app is currently on before updating it
        int currDBver = sharedPreferences.getInt(KEY_DB_NUM, -1);

        // Start adding to the database based on what verion of the database was added last
        if(!sharedPreferences.contains(KEY_DB_NUM)) {
            populateDataBase_num1();
        } else if(currDBver == 1) {
            populateDataBase_num2();
        } else if(currDBver == 2) {
            populateDataBase_num3();
        } else if(currDBver == 3) {
            populateDataBase_num4();
        } else if(currDBver == 4) {
            populateDataBase_num5();
        } // Add to this if there is another database update

        goBack();
    }

    /**
     * This method is the first population of the database. It should only ever run once or it will
     * override user data. That is what DB_NUM is for on MainActivity.
     */
    public void populateDataBase_num1() {
        Log.d(TAG, "populateDataBase_version1: Putting data into the database.");

        // Now we add everything
        mDatabaseHelper.addData("Educational Buildings", "School of Business", "Go to the School of Business on West Main Street.", "vcu_biz", "What do the large letters beside the electronic marquee in the atrium say?", "vcubusiness || vcu business || nothing || empty || blank", 100, false);
        mDatabaseHelper.addData("Historical", "Hollywood Cemetery", "Go to Hollywood Cemetery on South Cherry Street. Go to Jefferson Davis\' grave.", "hollywood_book", "What are the three letters on the back of the book that the lady is standing in?", "pax || p a x", 100, false);
        mDatabaseHelper.addData("Activities", "Cary St. Gym", "Go to the Cary Street Gym on Cary Street by the tennis court.", "gym_words", "What do the faded letters at the top of the building say?", "city auditorium || city-auditorium || city - auditorium", 100, false);
        mDatabaseHelper.addData("Food", "Chili\'s", "Go to Chili\'s Grill & Bar on West Cary Street.", "chilis_booths", "What color are the back cushions in the booths (ask someone near you if you are color blind)?", "blue || blue-green || azure || beryl || cerulean || cobalt || indigo || navy || royal || royal blue || navy blue || sapphire || teal || turquoise || ultramarine || blue-gray || light blue || dark blue || light-blue || dark-blue || blue gray || blue grey || blue-grey || navy-blue || royal-blue || blue green || bright blue || bright-blue", 100, false);
        mDatabaseHelper.addData("Notable Places", "Residence Hall", "Go to the Gladding Residence Center on West Main Street.", "residence_hall", "How many floors are there?", "12 || twelve || 12 floors || 12 stories || twelve floors || twelve stories || 12-floors || 12-floor || 12 - floors || 12 - floor || 12-stories || 12-story || 12 - stories || 12 - story || twelve-floors || twelve-floor || twelve-stories || twelve-story || twelve - floors || twelve - floor || twelve - stories || twelve - story || 12 levels || 12-levels || 12-level || 12 - levels || 12 - level || 12 level || twelve levels || twelve-levels || twelve-level || twelve - levels || twelve - level || twelve level", 100, false);
        mDatabaseHelper.addData("Miscellaneous", "Rest in Pieces", "Go to Rest in Pieces on South Laurel Street.", "rip_odd", "What is written under \"Rest in Pieces\" on the outside wall?", "oddities & curiosities || oddities and curiosities || oddities curiosities || oddities &amp; curiosities", 100, false);

        // Add the next update's items to the database
        populateDataBase_num2();
    }

    /**
     * This method is the second population of the database. Once new data was added, it could not
     * go into the previous method because it had already run; so, it gets its own, new method. It
     * should only ever run once or it will override user data. That is what DB_NUM is for on
     * MainActivity.
     */
    public void populateDataBase_num2() {
        Log.d(TAG, "populateDataBase_version2: Putting data into the database.");

        // Now we add everything
        mDatabaseHelper.addData("Educational Buildings", "School of Engineering", "Go to the fourth floor of the Engineering West building. Go to the end of the hall facing west. Look out the window, down and to the left.", "pyramid", "How many layers does the pyramid have?", "3 || 4 || 3 layers || 4 layers || three || four || three layers || four layers", 100, false);
        mDatabaseHelper.addData("Historical", "The Jefferson", "Go to the main entrance of The Jefferson Hotel on West Franklin Street.", "jefferson", "What animal is relaxing by the entrance?", "alligator || an alligator || a gator || gator", 100, false);
        mDatabaseHelper.addData("Historical", "Altria Theater", "Go to the main entrance of the Altria Theater on the Monroe Park side of the building.", "altria_points", "How many points are on the roof ledge above the door?", "13 || thirteen || 11 || eleven || 13 points || thirteen points || 11 points || eleven points", 100, false);
        mDatabaseHelper.addData("Historical", "Episcopal Church", "Go to the Grace & Holy Trinity Episcopal Church on North Laurel Street.", "church_door", "What color are the doors (ask someone near you if you are color blind)?", "red || bright red || fire engine red || firetruck red || fire truck red || fire-engine red || fire-truck red || cardinal || coral || crimson || flaming red || flaming-red || maroon || wine || blush || brick || burgundy || carmine || cerise || cherry || chestnut || claret || copper || dahlia || fuchsia || garnet || geranium || ruby || russet || rust || salmon || sanguine || scarlet || titian || vermilion || bloodshot || blood || blood red || blood-red || crimson-red || bright crimson || bright-crimson", 100, false);
        mDatabaseHelper.addData("Historical", "Cathedral", "Go to the Cathedral of the Sacred Heart on North Laurel Street.", "cathedral_roman_numerals", "What do the Roman numerals to the left of the entry stairs say?", "mcmiii || 1903", 100, false);
        mDatabaseHelper.addData("Activities", "Monroe Park", "Go to the middle of Monroe Park.", "mp_map", "What is in the middle of the park?", "a fountain || a water fountain || fountain || water fountain || the fountain || the water fountain || water", 100, false);

        // Add the next update's items to the database
        populateDataBase_num3();
    }

    /**
     * This method is the third population of the database. Once new data was added, it could not
     * go into the previous method because it had already run; so, it gets its own, new method. It
     * should only ever run once or it will override user data. That is what DB_NUM is for on
     * MainActivity.
     */
    public void populateDataBase_num3() {
        Log.d(TAG, "populateDataBase_version2: Putting data into the database.");

        // Now we add everything
        mDatabaseHelper.addData("Notable Places", "Linden Walkway", "Go to the big open area between Harris Hall and the Student Commons and look for the giant, metal ram horns.", "ram_horns", "What is at the end of the ram horns?", "hands || human hands || creepy hands || fingers || open hands || a hand || palms || hand || cupped hands || people hands || peoples hands || people\'s hands || peoples\' hands || person\'s hands || persons hands || some hands || two hands || four hands || 2 hands || 4 hands || cupped hand", 100, false);
        mDatabaseHelper.addData("Notable Places", "Library", "Go to the James Branch Cabell Library on Floyd Avenue and stand on the W of the compass.", "library_number", "What is the gold number to the right of the emergency call box?", "901 || nine hundred one || nine hundred and one || nine-hundred one || nine-hundred and one || nine - hundred one || nine - hundred and one || 9 0 1 || nine zero one", 100, false);
        mDatabaseHelper.addData("Miscellaneous", "Learning Gardens", "Go to the VCU Learning Gardens on Parkwood Avenue.", "learning_garden", "When is the space open?", "dawn dusk || dawn to dusk || this space is open from dawn to dusk || from dawn to dusk", 100, false);

        // Add the next update's items to the database
        populateDataBase_num4();
    }

    /**
     * This method is the fourth population of the database. Once new data was added, it could not
     * go into the previous method because it had already run; so, it gets its own, new method. It
     * should only ever run once or it will override user data. That is what DB_NUM is for on
     * MainActivity.
     */
    public void populateDataBase_num4() {
        Log.d(TAG, "populateDataBase_version2: Putting data into the database.");

        // Now we add everything
        mDatabaseHelper.addData("Historical", "Henry Hibbs Monument", "Go to the Henry H. Hibbs monument behind Hibbs Hall.", "metal_desks", "How many books are at the monument?", "4 || 4 books || four || four books", 100, false);
        mDatabaseHelper.addData("Miscellaneous", "West Main St. Deck", "Go to the West Main Street parking deck.", "parking_deck", "How many levels are there?", "4 || four || 4 levels || four levels || 4 floors || four floors || 4 stories || four stories || 4 level || four level || 4-levels || four-levels || 4-floors || four-floors || 4-stories || four-stories || 4-level || four-level || 4 - levels || four - levels || 4 - floors || four - floors || 4 - stories || four - stories || 4 - level || four - level", 100, false);
        mDatabaseHelper.addData("Miscellaneous", "Rooftop Garden", "Go to the Pollak Building on North Harrison Street, and make your way up to the garden on the rooftop terrace.", "roof_garden", "How many of these circular, metal flower beds are there?", "16 || sixteen", 100, false);

        // Add the next update's items to the database
        populateDataBase_num5();
    }

    /**
     * This method is the fifth  population of the database. Once new data was added, it could not
     * go into the previous method because it had already run; so, it gets its own, new method. It
     * should only ever run once or it will override user data. That is what DB_NUM is for on
     * MainActivity.
     */
    public void populateDataBase_num5() {
        Log.d(TAG, "populateDataBase_version2: Putting data into the database.");

        // Now we add everything
        mDatabaseHelper.addData("Educational Buildings", "Trani Center", "Go to the Eugene P. and Lois E. Trani Center for Life Sciences.", "trani_roof", "What is the roof on the east side of the building made of?", "glass || plastic || green house || green-house || greenhouse || green house glass || green-house glass || greenhouse glass || green house plastic || green-house plastic || greenhouse plastic || glass and metal || metal and glass || metal and plastic || plastic and metal", 100, false);
        mDatabaseHelper.addData("Educational Buildings", "Temple Building", "Go to the T Edward Temple Building.", "temple_bridges", "What building do these bridges lead to from the Temple Building?", "vcu department of chemistry || department of chemistry || chemistry department || vcu chemistry department || oliver hall || oliver || physical science wing", 100, false);
        mDatabaseHelper.addData("Educational Buildings", "Harris Hall", "Go to Grace E. Harris Hall.", "harris_hall_art", "What is the name of this art piece by Craig R. Wedderspoon?", "soft", 100, false);
        mDatabaseHelper.addData("Educational Buildings", "Learning Commons", "Go to the Academic Learning Commons and ride this elevator to the fourth floor.", "writing_center", "When you step out of this elevator on the fourth floor, which side is the Writing Center on (left or right)?", "left || left side || to the left || to the left of you || to the left of me || left of me || left of you || out and left || out and to the left || out and to the left of you || out and to the left of me || lefthand || on the left || on the lefthand side || lefthand side", 100, false);
        mDatabaseHelper.addData("Educational Buildings", "Hibbs", "Go to Hibbs Hall and find this sign.", "hibbs_hall_sign", "What does the third line from the bottom say (the line between New Student… and University...)?", "transfer center", 100, false);
        mDatabaseHelper.addData("Historical", "Office of the President", "Go to the Office of the President on West Franklin Street.", "president_house", "What is the shape of the door knocker?", "eagle || bald eagle || baldeagle || american eagle || bird || pheonix || an eagle || a bald eagle || a baldeagle || an american eagle || a bird || a pheonix", 100, false);
        mDatabaseHelper.addData("Historical", "The Richmond Dairy", "Go to The Richmond Dairy on West Marshall Street.", "milk", "How many giant milk bottles are on the building?", "3 || three || 3 giant milk bottles || three giant milk bottles || 3 milk bottles || three milk bottles || 3 bottles || three bottles", 100, false);
        mDatabaseHelper.addData("Food", "821 Cafe", "Go to 821 Cafe on West Cary Street.", "spiderlady", "What is the name of the artist that painted this?", "mickael || thenightowl || @thenightowl || mickael thenightowl || mickael @thenightowl || the night owl || mickael the night owl || mickael broth || mickael broth thenightowl || mickael broth @thenightowl || mickael broth the night owl", 100, false);
        mDatabaseHelper.addData("Food", "Chick-fil-A", "Go to Chick-fil-A in the Student Commons.", "chick_fil_a", "How many of these lights are above the counter?", "4 || four lights || 4 lights || four", 100, false);
        mDatabaseHelper.addData("Food", "Shafer", "Go to the entrance of Shafer Court Dining Center next to The Compass.", "shafer", "What do the large, 3D letters standing on the ground out front say?", "vcu || v c u", 100, false);
        mDatabaseHelper.addData("Food", "Christian\'s Pizza", "Go to Christian\'s Pizza on North Harrison Street.", "christians_pizza", "What does the doormat say?", "christian\'s pizza || christians pizza", 100, false);
        mDatabaseHelper.addData("Food", "Red Eye", "Go to Red Eye Cookie Co. on West Grace Street.", "redeye_doors", "What is the last thing written on the doors?", "no smoking on patio", 100, false);
        mDatabaseHelper.addData("Food", "Insomnia", "Go to Insomnia Cookies on West Grace Street.", "insomnia_seating", "What color is the back wall of the seating area (ask someone near you if you are color blind)?", "purple || lavender || lilac || mauve || periwinkle || plum || violet || amethyst || heliotrope || magenta || mulberry || orchid || pomegranate || wine || amaranthine || blue-violet || bluish red || perse || reddish blue || violaceous || blue violet || bluish-red || reddish-blue", 100, false);
        mDatabaseHelper.addData("Food", "Cava", "Go to Cava on West Grace Street.", "cava_soda", "What brand of soft drink does Cava serve from their soda machine?", "maine root. || maine root || maine root handcrafted beverages || maine root. handcrafted beverages || maine root sodas || maine root. sodas || www.maineroot.com || maineroot.com || maine root beverages || maine root. beverages", 100, false);
        mDatabaseHelper.addData("Food", "Panera", "Go to Panera Bread on West Grace Street.", "panera_mex", "What restaurant is to the left of Panera?", "chipotle || chipotle mexican grill || chipotle grill", 100, false);
        mDatabaseHelper.addData("Food", "Domino\'s", "Go to Domino\'s Pizza on North Belvidere Street.", "dominos_bandw", "What is the main color of the building (ask someone near you if you are color blind)?", "blue || blue-green || azure || beryl || cerulean || cobalt || indigo || navy || royal || royal blue || navy blue || sapphire || teal || turquoise || ultramarine || blue-gray || light blue || dark blue || light-blue || dark-blue || blue gray || blue grey || blue-grey || navy-blue || royal-blue || blue green || bright blue || bright-blue || domino\'s blue", 100, false);
        mDatabaseHelper.addData("Food", "Jimmy John\'s", "Go to Jimmy John\'s on West Franklin Street.", "jimmy_johns", "What establishment is on top of Jimmy John\'s?", "the monroe park towers apartments || the monroe park towers || monroe park towers apartments || monroe park towers || apartments || apartment buildings || the monroe park towers apartment buildings || monroe park towers apartment buildings || apartment building || the monroe park towers apartment building || monroe park towers apartment building || apartments building || the monroe park towers apartments building || monroe park towers apartments building || an apartment building", 100, false);
        mDatabaseHelper.addData("Notable Places", "Siegel Center", "Go to the main auditorium of the Stuart C. Siegel Center on West Broad Street.", "jumbotron", "What is the hashtag on the jumbo screen?", "#letsgovcu || letsgovcu || lets go vcu || let's go vcu || #lets go vcu || #let's go vcu || #let'sgovcu", 100, false);
        mDatabaseHelper.addData("Notable Places", "Barnes & Noble", "Go to Barnes & Noble @ VCU on West Broad Street.", "bn_starbucks", "Who is between Hardy and Orwell on the painting above the Starbucks Counter?", "dickinson || emily || emily dickinson || dickinson, emily || emily elizabeth dickinson || emily e dickinson || dickinson, emily e || emily e. dickinson || dickinson, emily e.", 100, false);
        mDatabaseHelper.addData("Notable Places", "RamTech", "Go to RamTech on West Grace Street.", "ramtech_circles", "How many circles are in the word “RAM”?", "12 || 12 circles || twelve || twelve circles", 100, false);
        mDatabaseHelper.addData("Notable Places", "Honors College", "Go to the VCU Honors College on West Grace Street.", "honors_windows", "How many glass panels are in one of these windows?", "3 || three || 3 panels || three panels || 3 glass panels || three glass panels || 3 pieces || three pieces || 3 pieces of glass || three pieces of glass || 3 per window || three per window || 3 panels per window || three panels per window || 3 glass panels per window || three glass panels per window || 3 pieces per window || three pieces per window || 3 pieces of glass per window || three pieces of glass per window", 100, false);
        mDatabaseHelper.addData("Notable Places", "Art Institute", "Go to the VCU Institute for Contemporary Art on West Broad Street.", "art_inst_words", "What is written on the outer wall of the second level?", "the royall forum", 100, false);
        mDatabaseHelper.addData("Notable Places", "RamBikes", "Go to VCU RamBikes on North Belvidere Street.", "rambikes_hidden_store", "What store is located inside of the RamBikes store?", "free store || the free store || freestore || the freestore || free-store || the free-store || vcu free store || the vcu free store || vcu freestore || the vcu freestore || vcu free-store || the vcu free-store", 100, false);
    }

    // Add another method if there is another database update

    /**
     * This method sends the user back to wherever they were before.
     */
    private void goBack() {
        Intent goBack = new Intent();

        setResult(RESULT_OK, goBack);

        finish();
    }

}
