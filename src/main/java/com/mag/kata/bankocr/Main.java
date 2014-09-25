package com.mag.kata.bankocr;

import static com.mag.kata.bankocr.AccountNumberReport.report;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.codepoetics.protonpack.Indexed;
import com.codepoetics.protonpack.StreamUtils;

/**
 * Application main.  Input is a list of file paths.
 *
 */
public class Main {
    
    private static final String REPORT_EXTENSION = ".report";

    private static void processFile(String inputFile) {
        
        String outputFile = inputFile + REPORT_EXTENSION;
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            // Slurp the input file, add line numbers, create the accounts
            AccountNumberCollector collector =
                    StreamUtils.zipWithIndex(Files.readAllLines(Paths.get(inputFile))
                               .stream())
                               .collect(AccountNumberCollector::new,
                                        AccountNumberCollector::accept,
                                        AccountNumberCollector::combine);

            // Report on the account numbers
            report(collector.getAccountNumbers(), writer::write);

        } catch (IOException ex) {
            System.out.println("Error reading file: " + inputFile +
                            " " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        for (String fileName : args) {
            processFile(fileName);
        }
    }

}
