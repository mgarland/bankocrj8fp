package com.mag.kata.bankocr;

import org.junit.Ignore;
import org.junit.Test;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountNumberReportTest {

    private static final String valid = "    _  _     _  _  _  _  _ " +
            "  | _| _||_||_ |_   ||_||_|" +
            "  ||_  _|  | _||_|  ||_| _|";

    private static final String invalid = " _  _  _  _  _  _  _  _  _ " +
            "|_||_||_||_||_||_||_||_||_|" +
            "|_||_||_||_||_||_||_||_||_|";

    private static final String illegible = "    _  _  _  _  _  _  _  _ " +
            "| ||_||_||_||_||_||_||_||_|" +
            "|_||_||_||_||_||_||_||_||_|";

    private static final String useCase4 = "                           " +
            "  |  |  |  |  |  |  |  |  |" +
            "  |  |  |  |  |  |  |  |  |";


    @Ignore
    @Test
    public void accountReport() {
        List<AccountNumber> accountNumbers = Arrays.asList(
                new AccountNumber(valid),
                new AccountNumber(invalid),
                new AccountNumber(illegible));

        StringWriter writer = new StringWriter(500);
        AccountNumberReport.report(accountNumbers, writer::write);

        assertEquals("Report contents are incorrect",
                """
                        123456789
                        888888888 ERR
                        ?88888888 ILL
                        """,
                writer.toString());

    }

    @Test
    public void useCase4OneValid() {
        List<AccountNumber> accountNumbers = List.of(
                new AccountNumber(useCase4));
        StringWriter writer = new StringWriter(500);
        AccountNumberReport.report(accountNumbers, writer::write);

        assertEquals("Should be one alternative 711111111\n", "711111111\n",
                writer.toString());
    }

}
