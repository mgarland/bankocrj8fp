package com.mag.kata.bankocr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

import com.mag.kata.bankocr.AccountNumber;

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
        
        assertEquals("Expected account number = 123456789",
                        "123456789", 
                        accountNumber.accountNumber);
    }
    
    @Test
    public void accountNumberHasInvalidDigit2() {

        AccountNumber accountNumber = new AccountNumber(invalid);
        
        assertFalse("account number contains an invalid digit",
                        accountNumber.digits.stream()
                             .allMatch( Optional::isPresent ));
        
        assertTrue("digit 2 should not be present", 
                        !accountNumber.digits.get(1).isPresent());
        assertEquals("digit 9 should be present",
                        Optional.of(9),
                        accountNumber.digits.get(8));
    }
    
    @Test
    public void generateAlternatives() {
        AccountNumber accountNumber = new AccountNumber(valid);
       
        assertEquals("The first character is incorrect",
                        '|',
                        accountNumber.generateAlternatives().get(0).inputLine.charAt(0));
        
        assertEquals("The rest is incorrect",
                        valid.substring(1),
                        accountNumber.generateAlternatives().get(0).inputLine.substring(1));
    }

}
