package utils.UserInputFetcher;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used to create custom scanners to gather certain types of
 * user input. The purpose of doing it this way is to create cleaner code
 * elsewhere.
 *
 * @author Christian Babin
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserInputFetcher {
    /**
     * These are the different types of UserInputFetchers that can be used.
     */
    public enum InputType {
        /**
         * For any keyboard input.
         */
        General,

        /**
         * For numerical keyboard input.
         */
        Number,

        /**
         * For specific words or phrases as keyboard inputs.
         */
        PredefinedTerms
    }

    public UserInputFetcher () {
        this(InputType.General);
    }

    public UserInputFetcher (InputType inputType) {
       this.inputType = inputType;

       userIn = SingletonSysInScanner.getInstance().getUserIn();
    }

    /// The singleton instance of the System.in scanner.
    private final Scanner userIn;

    /// The type of input this UserInputFetcher will be used to collect.
    private final InputType inputType;

    /// If the type is InputType.PredefinedTerms, these are the predefined
    /// terms that will be compared to the user input to make sure it is valid.
    private String[] predefinedTerms;

    public void setPredefinedTerms(String[] predefinedTerms) {
        this.predefinedTerms = predefinedTerms;
    }

    /**
     * This method collects the user's input.
     *
     * @return The user's input as a string.
     */
    public String fetchUserInput() {
        /// The user's input.
        String toReturn;

        // See which type of input we are looking for from the user.
        switch(inputType) {
            case General:
                toReturn = fetchGeneralUserInput();
                break;
            case Number:
                toReturn = fetchNumberUserInput();
                break;
            case PredefinedTerms:
                toReturn = fetchPredefinedUserInput();
                break;
            default:
                System.out.print("Warning: Using \"General\" input type!");
                toReturn = fetchGeneralUserInput();
        }

        // Return the user's input.
        return toReturn;
    }

    /**
     * This method fetches any keyboard input from the user.
     *
     * @return The user's input as a string.
     */
    private String fetchGeneralUserInput() {
        return userIn.nextLine().trim();
    }

    /**
     * This method fetches numerical input from the user.
     *
     * @return The user's input as a string.
     */
    private String fetchNumberUserInput() {
        /// The user's input number.
        int num;

        /// The user's input number as a string.
        String toReturn = null;

        // Keep asking the user for input until they enter a number.
        while(toReturn == null) {
            // Try for a number input, catch any other type of input.
            try {
                num = userIn.nextInt();
                userIn.nextLine(); // Eat up the rest of the input.
            } catch (InputMismatchException e) {
                // User entered something other than a number.
                System.out.print("Please enter a valid number: ");
                userIn.nextLine(); // Eat up the rest of the bad input.
                continue;
            }

            // Convert the user's number to a string.
            toReturn = String.valueOf(num);
        }

        return toReturn;
    }

    /**
     * This method fetches specific terms from the user.
     *
     * @return The user's input as a string.
     */
    private String fetchPredefinedUserInput() {
        // If there are no predefined terms, then it is impossible to ask the
        // user for one.
        if(predefinedTerms == null || predefinedTerms.length == 0) {
            return null;
        }

        /// The user's input. It is temporarily held here to be checked
        /// against the list of predefined terms.
        String temp;

        /// This is the user's input once it is approved as a match from the
        /// list of predefined terms.
        String toReturn = null;

        // Ask the user for a valid input until they give something that
        // matches the list of predefined terms.
        while(toReturn == null) {
            // Grab an input from the user.
            temp = userIn.nextLine().trim();

            // Test the input against every string in the list of predefined
            // terms.
            for(String term : predefinedTerms) {
                // If it matches one of the predefined terms, approve it and
                // move forward.
                if (temp.equalsIgnoreCase(term)) {
                    toReturn = temp;
                    break;
                }
            }

            // If the user's input has not been approved, ask them for another.
            if(toReturn == null) {
                System.out.print("Please enter a valid term: ");
            }
        }

        // Return the approved input.
        return toReturn;
    }
}
