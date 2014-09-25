package com.mag.kata.bankocr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MainTest {

    static final Predicate<String> BLANK_LINE = s -> s.trim().isEmpty();

    static final String INPUT_FILE = "/accountnumbers.txt";
    static final String REPORT_FILE = "/accountnumbers.txt.report";
    static final String EXPECTED_REPORT_FILE = "/accountnumbers.txt.report.expected";
    static final String FAIL_EXPECTED_REPORT_FILE = "/accountnumbers.txt.report.expected.fail";
    
    @Before
    public void setup() {
        // Run main on the input file
        String inFile = getAbsoluteFile(INPUT_FILE);
        Main.main(new String[] { inFile });
    }

    @Test
    public void runMainSuccess() {
        try {

            // Slurp the report file
            Path rptFilePath = Paths.get(getAbsoluteFile(REPORT_FILE));
            List<String> rptLines = Files.readAllLines(rptFilePath);

            // Slurp the expected report file output
            Path expectedRptFilePath = 
                            Paths.get(getAbsoluteFile(EXPECTED_REPORT_FILE));
            List<String> expectedRptLines = Files
                            .readAllLines(expectedRptFilePath);

            assertEquals("Report output should be correct", 
                  expectedRptLines.stream().collect(Collectors.joining(", ")), 
                  rptLines.stream().collect(Collectors.joining(", ")));

        } catch (IOException ex) {
            fail("Caught exception: " + ex.getMessage());
        }

    }

    @Test
    public void runMainFail() {
        try {

            // Slurp the report file
            Path rptFilePath = Paths.get(getAbsoluteFile(REPORT_FILE));
            List<String> rptLines = Files.readAllLines(rptFilePath);

            // Slurp the failed expected report file output
            Path expectedRptFilePath = 
                            Paths.get(getAbsoluteFile(FAIL_EXPECTED_REPORT_FILE));
            List<String> expectedRptLines = Files
                            .readAllLines(expectedRptFilePath);

            assertNotEquals("Report output should be incorrect",
                  expectedRptLines.stream().collect(Collectors.joining(", ")), 
                  rptLines.stream().collect(Collectors.joining(", ")));

        } catch (IOException ex) {
            fail("Caught exception: " + ex.getMessage());
        }

    }
    
    
    private String getAbsoluteFile(String file) {
        URL rptUrl = this.getClass().getResource(file);
        return new File(rptUrl.getFile()).getAbsoluteFile().toString();
    }
}
