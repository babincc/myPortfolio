package test.utils;

import main.utils.MyTools;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * This class tests MyTools.java
 *
 * @author Christian Babin
 * @version 1.0.0
 * @see MyTools
 * @since 1.0.0
 */
class MyToolsTest {

    @Nested
    class deleteElementAtIndex {
        @Test
        void testElementWithinArray() {
            char[] input = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
            char[] expectedOutput = {'a', 'b', 'c', 'e', 'f', 'g', 'h', 'i'};

            assertArrayEquals(
                    expectedOutput,
                    MyTools.deleteElementAtIndex(input, 3)
            );
        }

        @Test
        void testElementBeginningArray() {
            char[] input = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
            char[] expectedOutput = {'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

            assertArrayEquals(
                    expectedOutput,
                    MyTools.deleteElementAtIndex(input, 0)
            );
        }

        @Test
        void testElementEndArray() {
            char[] input = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
            char[] expectedOutput = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

            assertArrayEquals(
                    expectedOutput,
                    MyTools.deleteElementAtIndex(input, 8)
            );
        }

        @Test
        void testIndexOutOfBounds() {
            char[] input = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

            assertArrayEquals(
                    null,
                    MyTools.deleteElementAtIndex(input, -1)
            );
        }
    }

}