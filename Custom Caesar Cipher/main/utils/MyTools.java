package main.utils;

/**
 * This class contains general tools that can be used throughout the program.
 *
 * @author Christian Babin
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyTools {

    /**
     * This method deletes an element from a char array.
     *
     * @param original The char array that needs an element deleted.
     * @param index    The index of the element that needs to be deleted.
     * @return The char array with the selected element deleted. Null if
     * original is null or index is invalid.
     */
    public static char[] deleteElementAtIndex(char[] original, int index) {
        if (original == null) {
            return null;
        } else if (index < 0 || index >= original.length) {
            return null;
        }

        /// The new array without the deleted element.
        char[] toReturn = new char[original.length - 1];

        /// This will keep track of where we are in the toReturn array. This
        // is needed because once we get past the index of the element we are
        // deleting, this will no longer be equal to i in the loop.
        int counter = 0;

        // Go through every element in the original array, and add it to the
        // toReturn array (except for the deleted one).
        for (int i = 0; i < original.length; i++) {
            // If the current element is the one that is being deleted, skip it.
            if (i == index) {
                continue;
            }

            // Add the element from the original array to the toReturn array.
            toReturn[counter] = original[i];

            // Move the counter up to keep our place in the toReturn array.
            counter++;
        }

        // Return the array that no longer has the deleted element.
        return toReturn;
    }

}
