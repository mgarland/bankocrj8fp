package com.mag.kata.bankocr;

import java.util.Arrays;
import java.util.List;

/**
 * Parse the three lines and construct the digit patterns from them.
 *
 * @author mark
 *
 */
public class OCR {
    
    private static final int DIGITS_PER_LINE = 9;
    private static final int DIGIT_PATTERNS_PER_NUMBER = 3;
    
    /**
     * Parse the three lines and return the digit pattern for each digit.
     * 
     * @param line1 first line of digit patterns
     * @param line2 second line of digit patterns
     * @param line3 third line of digit patterns
     * @return pattern for each digit
     */
    public static List<String> parse(String line1, String line2, String line3) {
        
        // Get the digit patterns from each line
        String[] line1DigitPatterns = getDigitPatterns(line1);
        String[] line2DigitPatterns = getDigitPatterns(line2);
        String[] line3DigitPatterns = getDigitPatterns(line3);
        
        // Combine the patterns so each digit is represented by its full pattern
        String[] result = new String[DIGITS_PER_LINE];
        for (int i=0; i<DIGITS_PER_LINE; i++) {
            result[i] = line1DigitPatterns[i] + line2DigitPatterns[i] + line3DigitPatterns[i];
        }
        return Arrays.asList(result);
    }
    
    /**
     * Split the line into its corresponding digit patterns.
     * 
     * @param line the line containing the digit patterns
     * @return the patterns for each digit
     */
    private static String[] getDigitPatterns(String line) {
        String[] digitPatterns = new String[DIGITS_PER_LINE];
        for (int i = 0; i < DIGITS_PER_LINE; i++) {
            digitPatterns[i] = line.substring(i * DIGIT_PATTERNS_PER_NUMBER,
                    DIGIT_PATTERNS_PER_NUMBER + (i * DIGIT_PATTERNS_PER_NUMBER));
        }
        return digitPatterns;
    }


}
