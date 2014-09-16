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
    
    // Converts a digit to its numeric value, "?" if the digit is invalid
    private static Function<Optional<Integer>, String> DIGIT_TO_STRING =
                    d ->  d.isPresent() ?  d.get().toString() : "?";

    private String accountNumberString;

    /**
     * Construct from a full account number string - 3 lines combined.
     * 
     * @param accountNumberString the full account number string
     */
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
        return OCR.parse(accountNumberString);
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
