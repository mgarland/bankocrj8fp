package com.mag.kata.bankocr;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An account number contains a list of digits.
 * 
 * @author mark
 *
 */
/**
 * @author mark
 *
 */
public class AccountNumber {
    
    private static final int LINE_LENGTH = 27;
    
    private static Function<Optional<Integer>, String> DIGIT_TO_STRING = d ->
                                d.isPresent() ?  d.get().toString() : "?";

    private String accountNumberString;

    public AccountNumber(String accountNumberString) {
        this.accountNumberString = accountNumberString;
    }


    /**
     * Returns the account number as a list of digits.
     * Invalid digits are represented by Optional.empty().
     * 
     * @return list of optional numbers
     */
    public List<Optional<Integer>> getDigits() {
        return OCR.parse(accountNumberString.substring(0,LINE_LENGTH),
                         accountNumberString.substring(LINE_LENGTH,LINE_LENGTH*2),
                         accountNumberString.substring(LINE_LENGTH*2)).stream()
                  .map( Digit::get )
                  .collect(Collectors.toList());
    }
    
    /**
     * Returns the account number as a string.
     * 
     * @return the account number
     */
    public String getAccountNumber() {
        return getDigits().stream()
                  .map( DIGIT_TO_STRING )
                  .reduce("", (a,b) -> a + b);
    }

    @Override
    public String toString() {
        return "AccountNumber [accountNumberString=" + accountNumberString
                        + "]" + "--> " + getDigits();
    }

}
