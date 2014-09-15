package com.mag.kata.bankocr;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class AccountNumberReportTest {
    
    private static final String valid = "    _  _     _  _  _  _  _ " +
                                        "  | _| _||_||_ |_   ||_||_|" +
                                        "  ||_  _|  | _||_|  ||_| _|";

    private static final String invalid =  " _  _  _  _  _  _  _  _  _ " +
                                           "|_||_||_||_||_||_||_||_||_|" +
                                           "|_||_||_||_||_||_||_||_||_|";

    private static final String illegible =  "    _  _  _  _  _  _  _  _ " +
                                             "| ||_||_||_||_||_||_||_||_|" +
                                             "|_||_||_||_||_||_||_||_||_|";

    @Test
    public void accountReport() {
        List<AccountNumber> accountNumbers = Arrays.asList(
                        new AccountNumber(valid),
                        new AccountNumber(invalid),
                        new AccountNumber(illegible));
        
        StringWriter writer = new StringWriter(500);
        AccountNumberReport.report(accountNumbers, writer::write);
        
        assertEquals("Report contents are incorrect", 
                        "123456789 \n"    +
                        "888888888 ERR\n" +
                        "?88888888 ILL\n",  writer.toString());
                        
    }

}
