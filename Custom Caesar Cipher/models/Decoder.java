package models;

/**
 * This is equivalent to a physical Caesar Cipher device.
 *
 * @author Christian Babin
 * @version 1.0.0
 * @since 1.0.0
 */
public class Decoder {

    /// This is equivalent to the inner rotating disc on a physical Caesar
    /// Cipher device.
    private KeyDisc keyDisc;

    /// On a physical Caesar Cipher decoder, this would be equivalent to how
    /// many spaces the alphabet ring gets rotated.
    private int shiftBy;

    public void setKeyDisc(KeyDisc keyDisc) {
        this.keyDisc = keyDisc;
    }

    public void setShiftBy(int shiftBy) {
        // Since this represents a circle with a finite number or spaces, we
        // must keep the number within range.
        this.shiftBy = shiftBy % KeyDisc.getDefaultAlphabet().length();
    }

    /**
     * This method decrypts the user's message.
     *
     * @param message The string being decrypted.
     * @return The decrypted message.
     */
    public String decode(String message) {
        /// As each character in the message gets decoded, it will go into
        /// this array until there is a completely decoded message.
        char[] decodedMsg = new char[message.length()];

        // Go through every character in the encoded message and decode it.
        for(int i = 0; i < decodedMsg.length; i++) {
            // Right now, this program only handles letters and numbers; so,
            // just send everything else through as plain text.
            if(!Character.toString(message.charAt(i)).matches("[a-zA-Z0-9]*")) {
                decodedMsg[i] = message.charAt(i);
                continue;
            }

            /// This is where the current character is on the inner wheel or
            /// key disc of the Caesar Cipher.
            int keyIndex =
                    keyDisc.getAlphabet().indexOf(message.charAt(i));

            // Find the corresponding character on the outer ring of letters
            // on the Caesar Cipher device and place it in the final message
            // array.
            decodedMsg[i] =
                    KeyDisc.getDefaultAlphabet().charAt((keyIndex + shiftBy) %
                            keyDisc.getAlphabet().length());
        }

        // Convert the character array into a string, and return it.
        return String.valueOf(decodedMsg);
    }

    /**
     * This method encrypts the user's message.
     *
     * @param message The string being encrypted.
     * @return The encrypted message.
     */
    public String encode(String message) {
        /// As each character in the message gets encoded, it will go into
        /// this array until there is a completely encoded message.
        char[] encodedMsg = new char[message.length()];

        // Go through every character in the decoded message and encode it.
        for(int i = 0; i < encodedMsg.length; i++) {
            // Right now, this program only handles letters and numbers; so,
            // just send everything else through as plain text.
            if(!Character.toString(message.charAt(i)).matches("[a-zA-Z0-9]*")) {
                encodedMsg[i] = message.charAt(i);
                continue;
            }

            /// This is where the current character is on the outer ring of
            /// the Caesar Cipher.
            int stdIndex =
                    KeyDisc.getDefaultAlphabet().indexOf(message.charAt(i));

            /// This is where the encoded version of the current character is
            /// on the inner wheel or key disc of the Caesar Cipher.
            int keyIndex = stdIndex - shiftBy;

            // If the keyIndex is negative, loop back around to the end of
            // the circle and count down from there.
            if(keyIndex < 0) {
                keyIndex = keyDisc.getAlphabet().length() + keyIndex;
            }

            // Set the encoded character.
            encodedMsg[i] =
                    keyDisc.getAlphabet().charAt(keyIndex);
        }

        // Convert the character array into a string, and return it.
        return String.valueOf(encodedMsg);
    }

}
