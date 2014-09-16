package com.mag.kata.bankocr;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountNumberReport {
                    
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
        
        Function<AccountNumber, String> formatOutput = a -> 
                    String.format("%s %s\n", a.accountNumber,
                                             a.getStatus().reportOutput);
        
        // write the report
        accountNumbers.stream().map(formatOutput).forEach(output);
    }
}
