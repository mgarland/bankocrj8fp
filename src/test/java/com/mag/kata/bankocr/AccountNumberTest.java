package com.mag.kata.bankocr;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AccountNumberTest {

    private static final String valid  = "    _  _     _  _  _  _  _ " +
                                         "  | _| _||_||_ |_   ||_||_|" +
                                         "  ||_  _|  | _||_|  ||_| _|";

    private static final String invalid  = "    _  _     _  _  _  _  _ " +
                                           "  | _  _||_||_ |_   ||_||_|" +
                                           "  ||_  _|  | _||_|  ||_| _|";

    @Test
    public void accountNumberHasValidDigits() {

        AccountNumber accountNumber = new AccountNumber(valid);

        assertEquals("123456789",
                accountNumber.accountNumber,
                "Expected account number = 123456789");
    }

    @Test
    public void accountNumberHasInvalidDigit2() {

        AccountNumber accountNumber = new AccountNumber(invalid);

        assertFalse(accountNumber.digits.stream()
                        .allMatch(Optional::isPresent),
                "account number contains an invalid digit");

        assertFalse(accountNumber.digits.get(1).isPresent(), "digit 2 should not be present");
        assertEquals(Optional.of(9),
                accountNumber.digits.get(8),
                "digit 9 should be present");
    }

    @Test
    public void generateAlternatives() {
        AccountNumber accountNumber = new AccountNumber(valid);

        assertEquals('|',
                accountNumber.generateAlternatives().get(0).inputLine.charAt(0),
                "The first character is incorrect");

        assertEquals(valid.substring(1),
                accountNumber.generateAlternatives().get(0).inputLine.substring(1),
                "The rest is incorrect");
    }

}
