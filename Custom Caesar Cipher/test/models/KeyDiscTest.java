package test.models;

import main.models.KeyDisc;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class tests KeyDisc.java
 *
 * @author Christian Babin
 * @version 1.0.0
 * @see KeyDisc
 * @since 1.0.0
 */
class KeyDiscTest {

    @Nested
    class calcAlphabet {
        @Test
        void testPositiveAlphabet() {
            KeyDisc keyDisc = new KeyDisc(12);
            String expected = "moqsuwy02468adgjntz5bhp1cl7ke9fxirv3";

            assertEquals(expected, keyDisc.getAlphabet());
        }

        @Test
        void testZeroAlphabet() {
            KeyDisc keyDisc = new KeyDisc(0);
            String expected = "acegikmoqsuwy02468bhntz5dp1fxljvr379";

            assertEquals(expected, keyDisc.getAlphabet());
        }

        @Test
        void testNegativeAlphabet() {
            KeyDisc keyDisc = new KeyDisc(-3);

            assertNull(keyDisc.getAlphabet());
        }

        @Test
        void testLargeAlphabet() {
            KeyDisc keyDisc = new KeyDisc(956);
            String expected = "umgcabflt3j1qihp0okwdr8x5svye724n9z6";

            assertEquals(expected, keyDisc.getAlphabet());
        }
    }
}