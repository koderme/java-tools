package com.gharat.recon.mapper;

import com.gharat.recon.common.Util;
import com.gharat.recon.model.ParsedRow;
import org.springframework.stereotype.Component;

import static com.gharat.recon.common.ReconConstants.ELEMENT_SEP;

/**
 * ParseRowMapper provides capability to map raw data (String) to ParsedRow.
 */
@Component("parsedRowMapper")
public class ParsedRowMapper {

    public static String elementSeparator = ELEMENT_SEP;

    public static RowMapper<ParsedRow> mapper = line -> {
        ParsedRow prow = new ParsedRow(0);
        String[] rowElements = line.split(elementSeparator);
        for (String element : rowElements) {
            prow.add(Util.convert(element));
        }
        return prow;
    };

    public static ParsedRow mapRow(String line) {
        return mapper.mapRow(line);
    }

}
