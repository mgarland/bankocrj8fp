package com.mag.kata.bankocr;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Digit class.
 *
 * @author mark
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

        assertTrue(result.stream().allMatch(Optional::isPresent),
                "All digits should be valid Optionals");

        assertEquals("123456789",
                result.stream()
                        .map(d -> d.map(Object::toString).orElse("?"))
                        .reduce("", (a, b) -> a + b),
                "Expected account number = 123456789");
    }

    @Test
    public void accountNumberInputLineHasInvalidDigit2() {

        List<Optional<Integer>> result = OCR.parse(invalid);

        assertFalse(result.stream().allMatch(Optional::isPresent),
                "account number contains an invalid digit");

        assertTrue(result.get(1).isEmpty(),
                "digit 2 should not be present");

        assertEquals(Optional.of(9), result.get(8),
                "digit 9 should be present");
    }

}
