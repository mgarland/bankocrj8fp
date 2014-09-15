package com.mag.kata.bankocr;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.codepoetics.protonpack.StreamUtils;

public class AccountNumberReport {
    
    private static final String STATUS_OK = "";
    private static final String STATUS_ILLEGIBLE = "ILL";
    private static final String STATUS_INVALID = "ERR";
                    
    // Get the status string for the account number
    private static Function<AccountNumber, String> GET_STATUS = a -> {
        if (AccountNumberValidation.isIllegible(a)) {
            return STATUS_ILLEGIBLE;
        } else if (!AccountNumberValidation.isValidCheckSum(a)) {
            return STATUS_INVALID;
        } else {
            return STATUS_OK;
        }
    };
                    
    /**
     * Write the account number report to the supplied output.
     * 
     * @param accountNumbers the account number to report on
     * @param output where to write the output
     */
    /**
     * @param accountNumbers
     * @param output
     */
    public static void report(List<AccountNumber> accountNumbers,
                              Consumer<String> output) {

        // Get the status for each account number
        List<String> statuses = accountNumbers.stream()
                                            .map( GET_STATUS )
                                            .collect(Collectors.toList());
        
        // Ouptut the report line
        StreamUtils.zip(accountNumbers.stream(), statuses.stream(), 
                        (a,s) -> a.getAccountNumber() + " " + s + "\n")
                .forEach(output);

        
    }
}
