package com.mag.kata.bankocr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.codepoetics.protonpack.Indexed;
import com.mag.kata.bankocr.AccountNumberCollector;

public class AccountNumberCollectorTest {
    
    static Indexed<String> line1 = Indexed.index(0, "    _  _     _  _  _  _  _ ");
    static Indexed<String> line2 = Indexed.index(1, "  | _| _||_||_ |_   ||_||_|");
    static Indexed<String> line3 = Indexed.index(2, "  ||_  _|  | _||_|  ||_| _|");
    static Indexed<String> line4 = Indexed.index(3, "                           ");

    @Test
    public void accountNumberCollectorAccept() {

        AccountNumberCollector collector = new AccountNumberCollector();
        collector.accept(line1);
        collector.accept(line2);
        collector.accept(line3);
        collector.accept(line4);
        
        assertEquals("Should be 1 account number", 1,
                        collector.getAccountNumbers().stream().count());
        
        assertEquals("Expected account number = 123456789",
                        "123456789", 
                        collector.getAccountNumbers().get(0).getDigits().stream()
                             .map( a -> a.get().toString() )
                             .reduce("", (a,b) -> a + b));
    }
    
    @Test
    public void accountNumberCollectorCombine() {
        AccountNumberCollector collector1 = new AccountNumberCollector();
        collector1.accept(line1);
        collector1.accept(line2);
        collector1.accept(line3);
        collector1.accept(line4);
        AccountNumberCollector collector2 = new AccountNumberCollector();
        collector2.accept(line1);
        collector2.accept(line2);
        collector2.accept(line3);
        collector2.accept(line4);
        
        collector1.combine(collector2);
        
        assertEquals("Should be 1 account number", 2,
                        collector1.getAccountNumbers().stream().count());
        
        assertEquals("Expected account number = 123456789",
                        "123456789", 
                        collector1.getAccountNumbers().get(1).getDigits().stream()
                             .map( a -> a.get().toString() )
                             .reduce("", (a,b) -> a + b));
    }

}
