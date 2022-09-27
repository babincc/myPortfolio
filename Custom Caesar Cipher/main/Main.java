package main;

import main.models.Decoder;
import main.models.KeyDisc;
import main.utils.userInputFetcher.UserInputFetcher;

/**
 * @author Christian Babin
 * @version 1.0.0
 * @see
 * <a href="https://github.com/babincc/myPortfolio/blob/main/Custom%20Caesar%20Cipher/README.md">README on GitHub</a>
 * @since 1.0.0
 */
public class Main {

    /// A custom scanner that takes in a number from the user.
    private static final UserInputFetcher myNumberInputFetcher =
            new UserInputFetcher(UserInputFetcher.InputType.Number);

    /// A custom scanner that takes in a specific term from the user.
    private static final UserInputFetcher myPredefinedInputFetcher =
            new UserInputFetcher(UserInputFetcher.InputType.PredefinedTerms);

    /// A custom scanner that takes in any keyboard input from the user.
    private static final UserInputFetcher myGeneralInputFetcher =
            new UserInputFetcher(UserInputFetcher.InputType.General);

    public static void main(String[] args) {
        // Have the user select which alphabet they want to use as the key in
        // the Caesar Cipher.
        int alphabetNumber = chooseAlphabet();

        /// The alphabet that will be used to encrypt/decrypt the user's
        /// message.
        KeyDisc keyDisc = buildKeyDisc(alphabetNumber);

        /// On a physical Caesar Cipher decoder, this would be equivalent to
        /// how many spaces the alphabet ring gets rotated.
        int shiftBy = chooseShift();

        /// This is equivalent to a physical Caesar Cipher device.
        Decoder decoder = new Decoder();

        // Initialize the decoder device.
        decoder.setKeyDisc(keyDisc);
        decoder.setShiftBy(shiftBy);

        // Have the user select whether they are encoding or decoding a message.
        String action = chooseAction();

        // Encode or decode the user's message.
        runAction(decoder, action);
    }

    /**
     * This method asks the user to select which alphabet they want to use as
     * the key in the Caesar Cipher.
     *
     * @return The number the user inputs.
     */
    private static int chooseAlphabet() {
        System.out.print("Please enter the number of the alphabet you would " +
                "like to use: ");

        // Convert the user's number from a string to an int.
        String number = myNumberInputFetcher.fetchUserInput();
        return Math.abs(Integer.parseInt(number));
    }

    /**
     * This method builds the equivalent to the inner rotating disc on a
     * physical Caesar Cipher.
     *
     * @param alphabetNumber The number for the alphabet that the user has
     *                       selected to use in their Caesar Cipher.
     * @return Essentially the inner rotating disc on a physical Caesar Cipher.
     */
    private static KeyDisc buildKeyDisc(int alphabetNumber) {
        // Let the user know the program is thinking.
        System.out.println("Accessing key alphabet...");

        /// The alphabet that will be used to encrypt/decrypt the user's
        /// message.
        KeyDisc keyDisc = new KeyDisc(alphabetNumber);

        // Let the use know the background process is complete.
        System.out.println("Done");

        return keyDisc;
    }

    /**
     * This method asks the user to select the shift value for their Caesar
     * Cipher. On a physical device, this is how many places the inner
     * alphabet ring (or keyDisc in this case) would be rotated.
     *
     * @return The number the user inputs.
     */
    private static int chooseShift() {
        System.out.print("Input the number of characters to shift the key " +
                "alphabet by: ");

        // Convert the user's number from a string to an int.
        String shiftByStr = myNumberInputFetcher.fetchUserInput();
        return Math.abs(Integer.parseInt(shiftByStr));
    }

    /**
     * This method asks the user to select whether they want to encode or
     * decode their message.
     *
     * @return The user's answer.
     */
    private static String chooseAction() {
        /// A set of predefined terms to ask the user for.
        String[] predefinedTerms = {"e", "encrypt", "d", "decrypt"};

        // Set the predefined terms in the custom scanner.
        myPredefinedInputFetcher.setPredefinedTerms(predefinedTerms);

        System.out.print(
                "Do you want to (e)ncrypt or (d)ecrypt your message?" + " ");

        return myPredefinedInputFetcher.fetchUserInput();
    }

    /**
     * This method encodes or decodes the user's message.
     *
     * @param decoder The device that will be used to encode or decode the
     *                message.
     * @param action  Encode or decode.
     */
    private static void runAction(Decoder decoder, String action) {
        // If action is corrupt, let the user know.
        if(action == null || action.isBlank()) {
            System.out.println("ERROR: No action given!");
        } else {
            // Convert to lowercase for coding convenience.
            action = action.toLowerCase();

            if(action.startsWith("e")) { // User selected "encrypt"
                System.out.print(
                        "Please enter the message you want to " + "encrypt: ");
            } else if(action.startsWith("d")) { // User selected "decrypt"
                System.out.print(
                        "Please enter the message you want to " + "decrypt: ");
            } else { // Action has been corrupted.
                System.out.println("ERROR: Invalid action given!");
                return;
            }

            /// The message the user wants to encrypt or decrypt.
            String message =
                    myGeneralInputFetcher.fetchUserInput().toLowerCase();

            /// The message after it has been encrypted or decrypted.
            String output;

            if(action.startsWith("e")) { // User selected "encrypt"
                System.out.println("Encrypting message...");

                // Encrypt the message.
                output = decoder.encode(message);
            } else { // User selected "decrypt"
                System.out.println("Decrypting message...");

                // Decrypt the message.
                output = decoder.decode(message);
            }

            System.out.println("Done");

            // Show the user their encrypted/decrypted message.
            System.out.println(output);
        }
    }

}
