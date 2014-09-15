package com.mag.kata.bankocr;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.codepoetics.protonpack.StreamUtils;

/**
 * Various account number validations.
 * 
 * @author mark
 *
 */
public class AccountNumberValidation {

    private static final int MOD_VALUE = 11;
    
      // Creates a list of di's for the checksum calculation.
      // d9, d8, d7, ... d1      
      private static final Function<Integer, List<Integer>> CREATE_DIs = i ->
              IntStream.rangeClosed(1, i)
                      .boxed()
                      .sorted( (l,r) -> Integer.compare(r, l) )
                      .collect(Collectors.toList());

    /**
     * Indicates if the the checksum is valid for the account number.
     * 
     * @param accountNumber the account number
     * @return indicates if checksum was valid
     */
    public static boolean isValidCheckSum(AccountNumber accountNumber) {

        // d9, d8, d7, ... d1
        List<Integer> ds = CREATE_DIs.apply((int)accountNumber.getDigits()
                        .stream()
                        .count());
        
        // account numbers a1, a2, ... a9
        List<Integer> digits = accountNumber.getDigits().stream()
                        .map( a -> a.get() )
                        .collect(Collectors.toList());
        
        // compute a1*d9 + a2*d8 + ... + a9*d1
        int value = StreamUtils.zip(ds.stream(), digits.stream(), 
                                    (a,b) -> a * b )
                        .reduce(0, (a, b) -> a + b);
        
        return value % MOD_VALUE == 0;
    }
    
    /**
     * Indicates if the account number is illegible (invalid digit).
     * 
     * @param accountNumber the account number
     * @return indicates if checksum was valid
     */
    public static boolean isIllegible(AccountNumber accountNumber) {
        return !accountNumber.getDigits().stream()
                        .allMatch( Optional::isPresent );
    }
    

}
