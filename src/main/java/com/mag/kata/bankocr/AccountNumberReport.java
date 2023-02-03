package com.mag.kata.bankocr;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.mag.kata.bankocr.AccountNumber.Status;
import static java.lang.String.format;

public class AccountNumberReport {
                    
    /**
     * Write the account number report to the supplied output.
     * 
     * @param accountNumbers the account number to report on
     * @param output where to write the output
     */
    public static void report(List<AccountNumber> accountNumbers,
                              Consumer<String> output) {
        
        BiFunction<AccountNumber, List<AccountNumber>, String> formatOutput = (a, b) ->
                format("%s %s %s", a.accountNumber,
                        (b == null) ? a.getStatus().reportOutput : Status.AMBIGUOUS.reportOutput,
                        (b == null) ? "" : b.toString())
                .trim().concat("\n");

        // process each account number
        for (AccountNumber accountNumber : accountNumbers) {
            if (accountNumber.getStatus() == Status.OK) {
                // account number is valid, output it
                output.accept(formatOutput.apply(accountNumber, null));
            } else {
                // generate all alternative account numbers for this one
                List<AccountNumber> validAlternatives =
                        accountNumber.generateAlternatives()
                                     .stream()
                                     .filter(a -> a.getStatus() == Status.OK)
                                     .collect(Collectors.toList());

                // process the alternatives
                if (validAlternatives.size() == 1) {
                    // one valid alternative, use it
                    output.accept(formatOutput.apply(validAlternatives.get(0), null));
                } else if (validAlternatives.size() == 0) {
                    // no valid alternatives, print original with status
                    output.accept(formatOutput.apply(accountNumber, null));
                } else {
                    // multiple alternatives, output original with alternatives
                    output.accept(formatOutput.apply(accountNumber, validAlternatives));
                }

            }
        }
    }
}
