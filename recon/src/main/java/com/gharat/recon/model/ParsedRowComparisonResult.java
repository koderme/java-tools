package com.gharat.recon.model;

import com.gharat.recon.common.ReconConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores results of comparison of ParsedRow.
 */
public class ParsedRowComparisonResult {
    private List<Boolean> list = new ArrayList<>(ReconConstants.DEFAULT_COLUMN_COUNT);
    private int mistMatchCount = 0;

    /**
     * Add the value to the list.
     * If added value is false, then increment the mismatchCount
     *
     * @param value
     */
    public void add(Boolean value) {
        if (!value)
            this.mistMatchCount++;
        list.add(value);
    }

    public boolean getValueAt(int index) {
        return list.get(index);
    }

    public int getMistMatchCount() {
        return this.mistMatchCount; }

    @Override
    public String toString() {
        return "ParsedRowComparisonResult{" +
                "list=" + list +
                '}';
    }

}
