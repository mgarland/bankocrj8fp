package com.mag.kata.bankocr;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.mag.kata.bankocr.OCR;

/**
 * Tests for the Digit class.
 * 
 * @author mark
 *
 */
public class OCRTest {
    
    private static final String line1 = "    _  _     _  _  _  _  _ "; 
    private static final String line2 = "  | _| _||_||_ |_   ||_||_|";
    private static final String line3 = "  ||_  _|  | _||_|  ||_| _|";

    @Test
    public void testParse() {
        List<String> digitPatterns = OCR.parse(line1, line2, line3);
        
        assertEquals("expected digit pattern for 8", 
                        "   " +
                        "  |" +
                        "  |", digitPatterns.get(0));
        
        assertEquals("expected digit pattern for 8", 
                        " _ " +
                        "|_|" +
                        " _|", digitPatterns.get(8));
    }

}
