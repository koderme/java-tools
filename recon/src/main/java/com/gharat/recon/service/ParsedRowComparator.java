package com.gharat.recon.service;

import com.gharat.recon.model.ParsedRow;
import com.gharat.recon.model.ParsedRowComparisonResult;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Compare lhs and rhs of ParseRow.
 */

@Component("parsedRowComparator")
public class ParsedRowComparator {

    /**
     * Compare each element in lhs and rhs.
     *
     * @param lhs ParsedRow representing lhs.
     * @param rhs ParsedRow representing rhs.
     * @return ParsedRowComparisonResult containing result of comparison.
     */
    ParsedRowComparisonResult compare(ParsedRow lhs, ParsedRow rhs) {

        Iterator<Object> lhsElementItr = lhs.iterator();
        Iterator<Object> rhsElementItr = rhs.iterator();
        ParsedRowComparisonResult prcResult = new ParsedRowComparisonResult();
        while (lhsElementItr.hasNext()) {
            prcResult.add(
                    lhsElementItr.next().equals(rhsElementItr.next()));

        }

        return prcResult;
    }
}
