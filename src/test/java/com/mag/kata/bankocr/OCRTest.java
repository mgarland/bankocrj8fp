package com.mag.kata.bankocr;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.mag.kata.bankocr.OCR;

/**
 * Tests for the Digit class.
 * 
 * @author mark
 *
 */
public class OCRTest {

    private static final String valid = "    _  _     _  _  _  _  _ " +
                                        "  | _| _||_||_ |_   ||_||_|" +
                                        "  ||_  _|  | _||_|  ||_| _|";

    private static final String invalid = "    _  _     _  _  _  _  _ " +
                                          "  | _  _||_||_ |_   ||_||_|" +
                                          "  ||_  _|  | _||_|  ||_| _|";

    @Test
    public void accountNumberInputLineAllValidDigits() {
        
        List<Optional<Integer>> result = OCR.parse(valid);
        
        assertTrue("All digits should be valid Optionals",
                   result.stream().allMatch(Optional::isPresent));

        assertEquals("Expected account number = 123456789", "123456789",
                     result.stream()
                           .map( d -> d.isPresent() ?  d.get().toString() : "?" )
                          .reduce("", (a,b) -> a + b));
    }

    @Test
    public void accountNumberInputLineHasInvalidDigit2() {

        List<Optional<Integer>> result = OCR.parse(invalid); 
        
        assertFalse("account number contains an invalid digit",
                    result.stream().allMatch(Optional::isPresent));

        assertTrue("digit 2 should not be present", !result.get(1).isPresent());
        
        assertEquals("digit 9 should be present", Optional.of(9), result.get(8));
    }

}
