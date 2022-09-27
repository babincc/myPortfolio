package test.models;

import main.models.Decoder;
import main.models.KeyDisc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests Decoder.java
 *
 * @author Christian Babin
 * @version 1.0.0
 * @see main.models.Decoder
 * @since 1.0.0
 */
class DecoderTest {

    // Using multiple decoders to make sure all tests pass in different
    // scenarios.
    static Decoder decoder1;
    static Decoder decoder2;
    static Decoder decoder3;
    static Decoder decoder4;
    static Decoder decoder5;

    @BeforeAll
    static void setUp() {
        decoder1 = new Decoder();
        decoder2 = new Decoder();
        decoder3 = new Decoder();
        decoder4 = new Decoder();
        decoder5 = new Decoder();

        decoder1.setKeyDisc(new KeyDisc(0));
        decoder2.setKeyDisc(new KeyDisc(3));
        decoder3.setKeyDisc(new KeyDisc(15));
        decoder4.setKeyDisc(new KeyDisc(236));
        decoder5.setKeyDisc(new KeyDisc(5674));

        decoder1.setShiftBy(8);
        decoder2.setShiftBy(54);
        decoder3.setShiftBy(0);
        decoder4.setShiftBy(236);
        decoder5.setShiftBy(8);
    }

    @Nested
    class decode {
        @Nested
        class testSingleWord {
            final String expected = "howdy";

            @Test
            void testSingleWord1() {
                final String message = "9m2v6";

                assertEquals(expected, decoder1.decode(message));
            }

            @Test
            void testSingleWord2() {
                final String message = "w0lyp";

                assertEquals(expected, decoder2.decode(message));
            }

            @Test
            void testSingleWord3() {
                final String message = "3kwvi";

                assertEquals(expected, decoder3.decode(message));
            }

            @Test
            void testSingleWord4() {
                final String message = "8tabs";

                assertEquals(expected, decoder4.decode(message));
            }

            @Test
            void testSingleWord5() {
                final String message = "9nudr";

                assertEquals(expected, decoder5.decode(message));
            }
        }

        @Nested
        class testMultipleWords {
            final String expected = "here is a string of multiple words";

            @Test
            void testMultipleWords1() {
                final String message = "9rsr au x uwsak7 m3 iygwaogr 2msvu";

                assertEquals(expected, decoder1.decode(message));
            }

            @Test
            void testMultipleWords2() {
                final String message = "w4e4 8d g dfe82k 0a qhuf8cu4 l0eyd";

                assertEquals(expected, decoder2.decode(message));
            }

            @Test
            void testMultipleWords3() {
                final String message = "3xyx 54 p 4ay5h1 kz egba5nbx wkyv4";

                assertEquals(expected, decoder3.decode(message));
            }

            @Test
            void testMultipleWords4() {
                final String message = "8c4c zq w qy4z6v ti gupyzxpc at4bq";

                assertEquals(expected, decoder4.decode(message));
            }

            @Test
            void testMultipleWords5() {
                final String message = "9lsl wx z xoswih nk q5bow6bl unsdx";

                assertEquals(expected, decoder5.decode(message));
            }
        }

        @Nested
        class testPunctuation {
            final String expected =
                    "howdy! this here message, the one you are reading now, " +
                            "contains punctuation. :)";

            @Test
            void testPunctuation1() {
                final String message =
                        "9m2v6! w9au 9rsr iruux7r, w9r mkr 6my xsr srxvak7 " +
                                "km2, jmkwxaku oykjwyxwamk. :)";

                assertEquals(expected, decoder1.decode(message));
            }

            @Test
            void testPunctuation2() {
                final String message =
                        "w0lyp! fw8d w4e4 q4ddgk4, fw4 024 p0h ge4 e4gy82k " +
                                "20l, s02fg82d ch2sfhgf802. :)";

                assertEquals(expected, decoder2.decode(message));
            }

            @Test
            void testPunctuation3() {
                final String message =
                        "3kwvi! a354 3xyx ex44p1x, a3x khx ikg pyx yxpv5h1 " +
                                "hkw, tkhap5h4 nghtagpa5kh. :)";

                assertEquals(expected, decoder3.decode(message));
            }

            @Test
            void testPunctuation4() {
                final String message =
                        "8tabs! y8zq 8c4c gcqqwvc, y8c t6c stu w4c 4cwbz6v " +
                                "6ta, ft6ywz6q xu6fyuwyzt6. :)";

                assertEquals(expected, decoder4.decode(message));
            }

            @Test
            void testPunctuation5() {
                final String message =
                        "9nudr! o9wx 9lsl qlxxzhl, o9l nil rn5 zsl slzdwih " +
                                "inu, pniozwix 65ipo5zowni. :)";

                assertEquals(expected, decoder5.decode(message));
            }
        }

        @Nested
        class testNumbers {
            final String expected = "here is a string with 36 characters.";

            @Test
            void testNumbers1() {
                final String message = "9rsr au x uwsak7 2aw9 td j9xsxjwrsu.";

                assertEquals(expected, decoder1.decode(message));
            }

            @Test
            void testNumbers2() {
                final String message = "w4e4 8d g dfe82k l8fw z5 swgegsf4ed.";

                assertEquals(expected, decoder2.decode(message));
            }

            @Test
            void testNumbers3() {
                final String message = "3xyx 54 p 4ay5h1 w5a3 j6 t3pyptaxy4.";

                assertEquals(expected, decoder3.decode(message));
            }

            @Test
            void testNumbers4() {
                final String message = "8c4c zq w qy4z6v azy8 em f8w4wfyc4q.";

                assertEquals(expected, decoder4.decode(message));
            }

            @Test
            void testNumbers5() {
                final String message = "9lsl wx z xoswih uwo9 y0 p9zszpolsx.";

                assertEquals(expected, decoder5.decode(message));
            }
        }
    }

    @Nested
    class encode {
        @Nested
        class testSingleWord {
            final String message = "howdy";

            @Test
            void testSingleWord1() {
                final String expected = "9m2v6";

                assertEquals(expected, decoder1.encode(message));
            }

            @Test
            void testSingleWord2() {
                final String expected = "w0lyp";

                assertEquals(expected, decoder2.encode(message));
            }

            @Test
            void testSingleWord3() {
                final String expected = "3kwvi";

                assertEquals(expected, decoder3.encode(message));
            }

            @Test
            void testSingleWord4() {
                final String expected = "8tabs";

                assertEquals(expected, decoder4.encode(message));
            }

            @Test
            void testSingleWord5() {
                final String expected = "9nudr";

                assertEquals(expected, decoder5.encode(message));
            }
        }

        @Nested
        class testMultipleWords {
            final String message = "here is a string of multiple words";

            @Test
            void testMultipleWords1() {
                final String expected = "9rsr au x uwsak7 m3 iygwaogr 2msvu";

                assertEquals(expected, decoder1.encode(message));
            }

            @Test
            void testMultipleWords2() {
                final String expected = "w4e4 8d g dfe82k 0a qhuf8cu4 l0eyd";

                assertEquals(expected, decoder2.encode(message));
            }

            @Test
            void testMultipleWords3() {
                final String expected = "3xyx 54 p 4ay5h1 kz egba5nbx wkyv4";

                assertEquals(expected, decoder3.encode(message));
            }

            @Test
            void testMultipleWords4() {
                final String expected = "8c4c zq w qy4z6v ti gupyzxpc at4bq";

                assertEquals(expected, decoder4.encode(message));
            }

            @Test
            void testMultipleWords5() {
                final String expected = "9lsl wx z xoswih nk q5bow6bl unsdx";

                assertEquals(expected, decoder5.encode(message));
            }
        }

        @Nested
        class testPunctuation {
            final String message =
                    "howdy! this here message, the one you are reading now, " +
                            "contains punctuation. :)";

            @Test
            void testPunctuation1() {
                final String expected =
                        "9m2v6! w9au 9rsr iruux7r, w9r mkr 6my xsr srxvak7 " +
                                "km2, jmkwxaku oykjwyxwamk. :)";

                assertEquals(expected, decoder1.encode(message));
            }

            @Test
            void testPunctuation2() {
                final String expected =
                        "w0lyp! fw8d w4e4 q4ddgk4, fw4 024 p0h ge4 e4gy82k " +
                                "20l, s02fg82d ch2sfhgf802. :)";

                assertEquals(expected, decoder2.encode(message));
            }

            @Test
            void testPunctuation3() {
                final String expected =
                        "3kwvi! a354 3xyx ex44p1x, a3x khx ikg pyx yxpv5h1 " +
                                "hkw, tkhap5h4 nghtagpa5kh. :)";

                assertEquals(expected, decoder3.encode(message));
            }

            @Test
            void testPunctuation4() {
                final String expected =
                        "8tabs! y8zq 8c4c gcqqwvc, y8c t6c stu w4c 4cwbz6v " +
                                "6ta, ft6ywz6q xu6fyuwyzt6. :)";

                assertEquals(expected, decoder4.encode(message));
            }

            @Test
            void testPunctuation5() {
                final String expected =
                        "9nudr! o9wx 9lsl qlxxzhl, o9l nil rn5 zsl slzdwih " +
                                "inu, pniozwix 65ipo5zowni. :)";

                assertEquals(expected, decoder5.encode(message));
            }
        }

        @Nested
        class testNumbers {
            final String message = "here is a string with 36 characters.";

            @Test
            void testNumbers1() {
                final String expected = "9rsr au x uwsak7 2aw9 td j9xsxjwrsu.";

                assertEquals(expected, decoder1.encode(message));
            }

            @Test
            void testNumbers2() {
                final String expected = "w4e4 8d g dfe82k l8fw z5 swgegsf4ed.";

                assertEquals(expected, decoder2.encode(message));
            }

            @Test
            void testNumbers3() {
                final String expected = "3xyx 54 p 4ay5h1 w5a3 j6 t3pyptaxy4.";

                assertEquals(expected, decoder3.encode(message));
            }

            @Test
            void testNumbers4() {
                final String expected = "8c4c zq w qy4z6v azy8 em f8w4wfyc4q.";

                assertEquals(expected, decoder4.encode(message));
            }

            @Test
            void testNumbers5() {
                final String expected = "9lsl wx z xoswih uwo9 y0 p9zszpolsx.";

                assertEquals(expected, decoder5.encode(message));
            }
        }
    }

}