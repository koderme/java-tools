package com.gharat.recon.service;

import com.gharat.recon.mapper.ParsedRowMapper;
import com.gharat.recon.model.AllComparisonResult;
import com.gharat.recon.model.ParsedRow;
import com.gharat.recon.model.ParsedRowComparisonResult;
import com.gharat.recon.streams.FileInputStream;
import com.gharat.recon.streams.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.gharat.recon.common.ReconConstants.DEFAULT_STREAM_COUNT;

/**
 * Recon service
 */
@Component
public class ReconServiceImpl implements ReconService {

    @Autowired
    private ParsedRowComparator parsedRowComparator;


    @Override
    public void recon(String[] filePaths) throws IOException {

        List<Map<String, ParsedRow>> parsedRowMapPerStream = new ArrayList<>(DEFAULT_STREAM_COUNT);

        for (String filePath : filePaths) {
            InputStream inputStream = new FileInputStream(filePath);
            inputStream.load();

            String line;
            List<ParsedRow> parsedRowList = new LinkedList<>();
            while ((line = inputStream.readLine()) != null) {
                ParsedRow pr = ParsedRowMapper.mapRow(line);
                parsedRowList.add(pr);
            }

            Map<String, ParsedRow> parsedRowMap =
                    parsedRowList.stream().collect(
                            Collectors.toMap(ParsedRow::getKey, x -> x));

            parsedRowMapPerStream.add(parsedRowMap);
        }

        Map<String, AllComparisonResult> result = reconInternal(parsedRowMapPerStream);
        System.out.println("result : " + result);
    }

    /**
     * Perform the reconciliation
     */
    private Map<String, AllComparisonResult> reconInternal(List<Map<String, ParsedRow>> paredRowMapPerStream) {
        // LHS
        Map<String, ParsedRow> lhsMap = paredRowMapPerStream.get(0);

        Map<String, AllComparisonResult> allComparisonResultMap =
                new HashMap<>();

        // For each row in lhsMap
        //  Find the key in rhsMap
        lhsMap.forEach((key, lhsParsedRow) -> {

            AllComparisonResult acResult = new AllComparisonResult(lhsParsedRow);
            for (int i = 1; i < paredRowMapPerStream.size(); ++i) {
                Map<String, ParsedRow> rhsParsedRowMap = paredRowMapPerStream.get(i);
                ParsedRow rhsParsedRow = rhsParsedRowMap.get(key);
                ParsedRowComparisonResult prcResult = parsedRowComparator.compare(lhsParsedRow, rhsParsedRow);
                acResult.addComparisonResult(rhsParsedRow, prcResult);
            }
            allComparisonResultMap.put(key, acResult);
        });

        return allComparisonResultMap;
    }
}
