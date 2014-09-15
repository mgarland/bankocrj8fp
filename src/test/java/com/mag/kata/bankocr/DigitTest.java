package com.mag.kata.bankocr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mag.kata.bankocr.Digit;

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
        assertEquals("expected 8", Integer.valueOf(8), Digit.get(eight).get());
        assertEquals("expected 5", Integer.valueOf(5), Digit.get(five).get());
    }
    
    @Test
    public void testInvalidDigits() {
        assertTrue("expected Optional.empty", !Digit.get(invalid1).isPresent());
        assertTrue("expected Optional.empty", !Digit.get(invalid2).isPresent());
    }
}
