package com.mag.kata.bankocr;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Maps a digit pattern to a digit.
 * Returns an empty Optional if the digit is invalid.
 * 
 * @author mark
 *
 */
public class Digit {
    
    /*
     * Map digit string pattern to their numeric values.
     */
    private static final Map<String, Integer> digitMap = createDigitMap();
    
    private static Map<String, Integer> createDigitMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put(" _ " +
                "| |" +
                "|_|", 0);
        map.put("   " +
                "  |" +
                "  |", 1);
        map.put(" _ " +
                " _|" + 
                "|_ ", 2);
        map.put(" _ " + 
                " _|" + 
                " _|", 3);
        map.put("   " + 
                "|_|" + 
                "  |", 4);
        map.put(" _ " + 
                "|_ " + 
                " _|", 5);
        map.put(" _ " + 
                "|_ " + 
                "|_|", 6);
        map.put(" _ " + 
                "  |" + 
                "  |", 7);
        map.put(" _ " + 
                "|_|" + 
                "|_|", 8);
        map.put(" _ " + 
                "|_|" + 
                " _|", 9);
        return map;
    }
    
    /**
     * Returns the digit represented by the pattern.
     * If the pattern is invalid, an empty Optional is returned.
     * 
     * @param digitPattern digit pattern
     * @return the digits
     */
    public static Optional<Integer> get(String digitPattern) {
        return digitMap.containsKey(digitPattern) ? Optional.of(digitMap.get(digitPattern)) : Optional.empty();
    }
    
}
