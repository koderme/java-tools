package com.gharat.recon.model;

import com.gharat.recon.common.ReconConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ComparedResult stores ParsedRow to be compared and comparison result.
 */
public class AllParsedRowComparisonResult {

    private ParsedRow lhsParsedRow;
    private List<ParsedRow> rhsParsedRowList = new ArrayList<>(ReconConstants.DEFAULT_STREAM_COUNT);
    private List<ParsedRowComparisonResult> rhsComparisonResult = new ArrayList<>(ReconConstants.DEFAULT_STREAM_COUNT);

    public AllParsedRowComparisonResult(ParsedRow lhsParsedRow) {
        this.lhsParsedRow = lhsParsedRow;
    }

    public String getKey() {
        return lhsParsedRow.getKey();
    }

    public ParsedRow getLhsRow() {
        return lhsParsedRow;
    }

    public Iterator<ParsedRow> getRhsRowIterator() {
        return rhsParsedRowList.iterator();
    }

    public Iterator<ParsedRowComparisonResult> getRhsComparisonResultIterator() {
        return rhsComparisonResult.iterator();
    }

    public void addComparisonResult(ParsedRow rhsRow, ParsedRowComparisonResult rhsComparisonList) {
        rhsParsedRowList.add(rhsRow);
        rhsComparisonResult.add(rhsComparisonList);
    }

    @Override
    public String toString() {
        return "AllParsedRowComparisonResult{" +
                "lhsParsedRow=" + lhsParsedRow +
                ", rhsParsedRowList=" + rhsParsedRowList +
                ", rhsComparisonResult=" + rhsComparisonResult +
                '}';
    }

    public int rhsCount() {
        return Integer.min(rhsParsedRowList.size(), rhsComparisonResult.size());
    }
}
