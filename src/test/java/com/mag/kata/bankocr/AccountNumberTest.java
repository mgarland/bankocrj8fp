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
                        accountNumber.getDigits().stream()
                             .map( a -> a.get().toString() )
                             .reduce("", (a,b) -> a + b));
    }
    
    @Test
    public void accountNumberHasInvalidDigit2() {

        AccountNumber accountNumber = new AccountNumber(invalid);
        
        assertFalse("account number contains an invalid digit",
                        accountNumber.getDigits().stream()
                             .allMatch( Optional::isPresent ));
        
        assertTrue("digit 2 is not valid", 
                        !accountNumber.getDigits().get(1).isPresent());
        assertEquals("digit 9 is valid",
                        Optional.of(9),
                        accountNumber.getDigits().get(8));
    }


}
