package com.mag.kata.bankocr;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.codepoetics.protonpack.Indexed;

/**
 * Partition lines by the size of an account number entry.
 * 
 * @author mark
 *
 */
public class AccountNumberCollector implements Consumer<Indexed<String>> {

    private static final int NUM_LINES_PER_ENTRY = 4;
    private StringBuilder currentLine = new StringBuilder();
    private List<AccountNumber> accountNumbers = new ArrayList<>();

    /* 
     * Processes the current line.  An Indexed<String> contains
     * the line number and the line.
     * 
     * @see java.util.function.Consumer#accept(java.lang.Object)
     */
    @Override
    public void accept(Indexed<String> line) {

        // check if last line of entry
        if ((line.getIndex() + 1) % NUM_LINES_PER_ENTRY == 0) {
            // last line, create the account number and reset current line
            accountNumbers.add(new AccountNumber(currentLine.toString()));
            currentLine.setLength(0);
        } else {
            // add this line to the current line
            currentLine.append(line.getValue());
        }
    }

    /**
     * Combines two collector objects.
     * 
     * @param other
     *            PartitionLines object to combine
     */
    public void combine(AccountNumberCollector other) {
        accountNumbers.addAll(other.accountNumbers);
        currentLine.setLength(0);
    }

    /**
     * Returns the list of account numbers.
     * 
     * @return account numbers
     */
    public List<AccountNumber> getAccountNumbers() {
        return accountNumbers;
    }

}
