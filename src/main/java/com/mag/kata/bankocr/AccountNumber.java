package com.mag.kata.bankocr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    
    /**
     * AccountNumber statuses.
     * 
     * @author mark
     *
     */
    public static enum Status {
        OK(""), ILLEGIBLE("ILL"), INVALID("ERR"), AMBIGUOUS("AMB");
        
        public final String reportOutput;
        
        Status(String reportOutput) {
            this.reportOutput = reportOutput;
        }
    }
    
    // Converts a digit to its numeric value, "?" if the digit is invalid
    private static Function<Optional<Integer>, String> DIGIT_TO_STRING =
                    d ->  d.isPresent() ?  d.get().toString() : "?";

    public final String inputLine;
    public final String accountNumber;
    public final List<Optional<Integer>> digits;

    /**
     * Construct from a full account number input line - 3 lines combined.
     * 
     * @param inputLine the full input line representing the account number
     */
    public AccountNumber(String inputLine) {
        this.inputLine = inputLine;
        this.digits = OCR.parse(inputLine);
        this.accountNumber = digits.stream()
                                   .map( DIGIT_TO_STRING )
                                   .reduce("", (a,b) -> a + b);
    }
    
    /**
     * Returns the account number status
     * 
     * @return account number status
     */
    public AccountNumber.Status getStatus() {
        return AccountNumberValidation.getStatus(this);
    }
    
    public List<AccountNumber> generateAlternatives() {
        List<AccountNumber> result = new ArrayList<>();
        for (int pos=0; pos < inputLine.length(); pos++) {
            if (inputLine.charAt(pos) == ' ') {
                result.add(new AccountNumber(replaceAt(inputLine, pos, '|')));
                result.add(new AccountNumber(replaceAt(inputLine, pos, '_')));
            } else if (inputLine.charAt(pos) == '|' || 
                            inputLine.charAt(pos) == '_') {
                result.add(new AccountNumber(replaceAt(inputLine, pos, ' ')));
            }
        }
        return result;
    }
    
    private String replaceAt(String s, int pos, char c) {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }

    @Override
    public String toString() {
        return accountNumber;
    }

}
