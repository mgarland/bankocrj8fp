package com.mag.kata.bankocr;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountNumberValidationTest {

    private static final String valid = "    _  _     _  _  _  _  _ " +
                                        "  | _| _||_||_ |_   ||_||_|" +
                                        "  ||_  _|  | _||_|  ||_| _|";

    private static final String invalid =
            " _  _  _  _  _  _  _  _  _ " +
                                           "|_||_||_||_||_||_||_||_||_|" +
                                           "|_||_||_||_||_||_||_||_||_|";

    private static final String illegible =
            "    _  _  _  _  _  _  _  _ " +
                                             "| ||_||_||_||_||_||_||_||_|" +
                                             "|_||_||_||_||_||_||_||_||_|";

    @Test
    public void validCheckSum() {
        AccountNumber accountNumber = new AccountNumber(valid);
        assertTrue("Checksum should be valid",
                AccountNumberValidation.isValidCheckSum(accountNumber));
    }

    @Test
    public void invalidCheckSum() {
        AccountNumber accountNumber = new AccountNumber(invalid);
        assertFalse("Checksum should be invalid",
                AccountNumberValidation.isValidCheckSum(accountNumber));

    }

    @Test
    public void illegible() {
        AccountNumber accountNumber = new AccountNumber(illegible);
        assertTrue("Account number should be illegible",
                AccountNumberValidation.isIllegible(accountNumber));
    }

}
