package models;

import utils.MyTools;

/**
 * This class represents the inner spinning disc of a physical Caesar Cipher
 * device.
 *
 * @author Christian Babin
 * @version 1.0.0
 * @since 1.0.0
 */
public class KeyDisc {
    public KeyDisc (int alphabetNumber) {
        this.alphabetNumber = alphabetNumber;

        calcAlphabet();
    }

    /// All of the characters this program is designed to handle.
    private static final String DEFAULT_ALPHABET =
            "abcdefghijklmnopqrstuvwxyz0123456789";

    /// This will be used as the seed to calculate a unique alphabet.
    private final int alphabetNumber;

    /// This is the unique alphabet that is calculated from the
    /// alphabetNumber seed.
    private String alphabet;

    public static String getDefaultAlphabet() {
        return DEFAULT_ALPHABET;
    }

    public String getAlphabet() {
        return alphabet;
    }

    /**
     * This method calculates the unique alphabet.
     *
     * @return The unique alphabet.
     */
    public String calcAlphabet() {
        /// The default alphabet as a char array for easy coding.
        char[] defAlphabetArr = DEFAULT_ALPHABET.toCharArray();

        /// The new alphabet as a char array for easy coding.
        char[] newAlphabet = new char[DEFAULT_ALPHABET.length()];

        /// Go through every character of the default alphabet and place it in a
        /// seemingly random spot based on the alphabet number seed.
        for(int i = 0; i < DEFAULT_ALPHABET.length(); i++) {
            int index = (i + alphabetNumber) % defAlphabetArr.length;

            newAlphabet[i] = defAlphabetArr[index];

            // Delete each character as we go so there are no duplicates.
            defAlphabetArr = MyTools.deleteElementAtIndex(defAlphabetArr,
                    index);
        }

        // Convert the new alphabet array into a string, and set it as this
        // object's alphabet.
        alphabet = String.valueOf(newAlphabet);

        return alphabet;
    }
}
