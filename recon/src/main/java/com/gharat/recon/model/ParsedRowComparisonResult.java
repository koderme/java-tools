package com.gharat.recon.model;

import com.gharat.recon.common.ReconConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores results of comparison of ParsedRow.
 */
public class ParsedRowComparisonResult {
    private List<Boolean> list = new ArrayList<>(ReconConstants.DEFAULT_COLUMN_COUNT);

    public void add(Boolean value) {
        list.add(value);
    }

    public boolean getValueAt(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        return "ParsedRowComparisonResult{" +
                "list=" + list +
                '}';
    }

}
