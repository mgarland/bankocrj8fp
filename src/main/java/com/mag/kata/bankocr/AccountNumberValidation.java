package com.mag.kata.bankocr;

import com.codepoetics.protonpack.StreamUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mag.kata.bankocr.AccountNumber.Status;

/**
 * Various account number validations.
 *
 * @author mark
 */
public class AccountNumberValidation {

    private static final int MOD_VALUE = 11;

    // Creates a list of di's for the checksum calculation.
    // d9, d8, d7, ... d1      
    private static final Function<Integer, List<Integer>> CREATE_DIs = i ->
            IntStream.rangeClosed(1, i)
                    .boxed()
                    .sorted((l, r) -> Integer.compare(r, l))
                    .collect(Collectors.toList());

    /**
     * Returns the account number status.
     *
     * @param accountNumber account number to validate.
     * @return the status
     */
    public static AccountNumber.Status getStatus(AccountNumber accountNumber) {
        if (isIllegible(accountNumber)) {
            return Status.ILLEGIBLE;
        } else if (!isValidCheckSum(accountNumber)) {
            return Status.INVALID;
        } else {
            return Status.OK;
        }
    }

    /**
     * Indicates if the the checksum is valid for the account number.
     *
     * @param accountNumber the account number
     * @return indicates if checksum was valid
     */
    public static boolean isValidCheckSum(AccountNumber accountNumber) {

        // d9, d8, d7, ... d1
        List<Integer> ds = CREATE_DIs.apply(accountNumber.digits.size());

        // account numbers a1, a2, ... a9
        List<Integer> digits = accountNumber.digits
                .stream()
                .map(Optional::get)
                .toList();

        // compute a1*d9 + a2*d8 + ... + a9*d1
        int value = StreamUtils.zip(ds.stream(), digits.stream(),
                        (a, b) -> a * b)
                .reduce(0, Integer::sum);

        return value % MOD_VALUE == 0;
    }

    /**
     * Indicates if the account number is illegible (invalid digit).
     *
     * @param accountNumber the account number
     * @return indicates if checksum was valid
     */
    public static boolean isIllegible(AccountNumber accountNumber) {
        return !accountNumber.digits
                .stream()
                .allMatch(Optional::isPresent);
    }


}
