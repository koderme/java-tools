package com.gharat.recon.model;

import com.gharat.recon.common.ReconConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * ComparedResult stores ParsedRow to be compared and comparison result.
 */
public class AllComparisonResult {

    private ParsedRow lhsParsedRow;
    private List<ParsedRow> rhsParsedRowList = new ArrayList<>(ReconConstants.DEFAULT_STREAM_COUNT);
    private List<ParsedRowComparisonResult> rhsComparisonResult = new ArrayList<>(ReconConstants.DEFAULT_STREAM_COUNT);

    public AllComparisonResult(ParsedRow lhsParsedRow) {
        this.lhsParsedRow = lhsParsedRow;
    }

    public String getKey() {
        return lhsParsedRow.getKey();
    }

    public ParsedRow getLhsRow() {
        return lhsParsedRow;
    }

    public void addComparisonResult(ParsedRow rhsRow, ParsedRowComparisonResult rhsComparisonList) {
        rhsParsedRowList.add(rhsRow);
        rhsComparisonResult.add(rhsComparisonList);
    }

    @Override
    public String toString() {
        return "AllComparisonResult{" +
                "lhsParsedRow=" + lhsParsedRow +
                ", rhsParsedRowList=" + rhsParsedRowList +
                ", rhsComparisonResult=" + rhsComparisonResult +
                '}';
    }
}
