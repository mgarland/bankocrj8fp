package com.mag.kata.bankocr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.codepoetics.protonpack.StreamUtils;

/**
 * Parse the account number input line pattern into its digits.
 * Returns Optional Integers so invalid digits can be represented
 * by an empty Optional.
 *
 * @author mark
 *
 */
public class OCR {

    private static final int DIGITS_PER_LINE = 9;
    private static final int DIGIT_PATTERNS_PER_NUMBER_PER_LINE = 3;

    // Partitions a String 's' into a list of Strings of length 'i'
    private static BiFunction<String, Integer, List<String>> 
        PARTITION_STRING = (s, i) -> { 
                   int len = s.length() / i;
                   List<String> result = new ArrayList<>();
                   for (int j = 0; j < i; j++) {
                       if (j + 1 < i)
                           result.add(s.substring(j * len, (j + 1) * len));
                       else
                           result.add(s.substring(j * len));
                   }
                   return result;
        };

    // Partitions each of the Strings in the list 'l' into
    // a list of Strings of length 'i'
    private static BiFunction<List<String>, Integer, List<List<String>>> 
        PARTITION_STRING_LIST = (l, i) ->
                             l.stream()
                              .map(s -> PARTITION_STRING.apply(s, i))
                              .collect(Collectors.toList()).stream()
                              .collect(Collectors.toList());
                             
    // Zip two Streams of Strings: (a, b, c) (d, e, f) -> ((ad), (be), (cf))
    private static BiFunction<Stream<String>, Stream<String>, Stream<String>>
        ZIP_TWO_STRING_LISTS = (l, r) ->  StreamUtils.zip(l, r, (a,b) -> a + b);

    /**
     * Returns the digits represented by the account number input line.
     * Returns Optional<Integer> for valid digits, Optional.empty if not valid.
     * 
     * @param accountNumberInputLine entire account number input line
     * @return Optional<Integer> for valid digits, empty Optional if invalid
     */
    public static List<Optional<Integer>> parse(String accountNumberInputLine) {
        
        // partition the input line into 3 lines
        List<String> lines = PARTITION_STRING
                                     .apply(accountNumberInputLine, 
                                            DIGIT_PATTERNS_PER_NUMBER_PER_LINE);
        
        // partition each line (3) into its digit patterns, 
        // 9 digits, 3 characters per digit pattern
        List<List<String>> digitPartitions = 
                        PARTITION_STRING_LIST.apply(lines, DIGITS_PER_LINE);

        // Now that each line is partitioned into its digit patterns,
        // Zip them so the result is 9 items with 9 characters per item
        // Each item is a full digit pattern
        List<String> digits =
                ZIP_TWO_STRING_LISTS.apply(digitPartitions.get(0).stream(),
                    ZIP_TWO_STRING_LISTS.apply(digitPartitions.get(1).stream(), 
                                               digitPartitions.get(2).stream()))
                                        .collect(Collectors.toList());
        
        // Convert the patterns to their number representation
        return digits.stream()
                     .map(Digit::get)
                     .collect(Collectors.toList());
     }

}
