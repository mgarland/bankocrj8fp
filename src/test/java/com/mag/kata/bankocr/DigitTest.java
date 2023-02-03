package com.mag.kata.bankocr;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Digit class.
 * 
 * @author mark
 *
 */
public class DigitTest {
    
    private static final String five = " _ " + 
                                       "|_ " +
                                       " _|";

    private static final String eight = " _ " + 
                                        "|_|" +
                                        "|_|";

    private static final String invalid1 = "   " + 
                                           "|_|" +
                                           "|_|";

    private static final String invalid2 = " _ " + 
                                           "|_|" +
                                           "  |";


    @Test
    public void testValidDigits() {
        assertEquals(Integer.valueOf(8), Digit.get(eight).get(), "expected 8");
        assertEquals(Integer.valueOf(5), Digit.get(five).get(), "expected 5");
    }
    
    @Test
    public void testInvalidDigits() {
        assertFalse(Digit.get(invalid1).isPresent(), "expected Optional.empty");
        assertFalse(Digit.get(invalid2).isPresent(), "expected Optional.empty");
    }
}
