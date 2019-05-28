package com.gharat.recon.streams;

import com.gharat.recon.common.XlsxUtil;
import com.gharat.recon.model.AllParsedRowComparisonResult;
import com.gharat.recon.model.ParsedRow;
import com.gharat.recon.model.ParsedRowComparisonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

/**
 * Provides implementation for writing data to xlsx file.
 */
public class XlsFileOutputStream extends OutputStream {

    private static final Logger logger = LogManager.getLogger(XlsFileOutputStream.class);

    String outputFilepath;

    int rowIndex = -1;
    int maxColCount = 0;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    private CellStyle cellStyleForUnMatched;

    public XlsFileOutputStream(String outputFilepath) {
        this.outputFilepath = outputFilepath;
    }

    @Override
    public void preHook() {
        workbook = XlsxUtil.createWorkBook();
        sheet = XlsxUtil.createSheet(this.workbook, "details");
        this.cellStyleForUnMatched =
                XlsxUtil.createCellStyle(this.workbook, IndexedColors.LIGHT_YELLOW, IndexedColors.AUTOMATIC);
    }

    @Override
    public void write(AllParsedRowComparisonResult compareResult) {

        Iterator<ParsedRow> rhsRowIterator = compareResult.getRhsRowIterator();
        Iterator<ParsedRowComparisonResult> rhsComparisonResultIterator = compareResult.getRhsComparisonResultIterator();

        // Print only LHS header
        if (this.rowIndex == -1) {
            writeRow(compareResult.getLhsRow(), null, "lhs");
            return;
        }

        // Print LHS
        writeRow(compareResult.getLhsRow(), null, "lhs");

        // Write RHS
        while (rhsRowIterator.hasNext() && rhsComparisonResultIterator.hasNext()) {
            ParsedRow rhsParsedRow = rhsRowIterator.next();
            ParsedRowComparisonResult parsedRowComparisonResult = rhsComparisonResultIterator.next();

            writeRow(rhsParsedRow, parsedRowComparisonResult, "rhs");
        }
    }

    /**
     * Write the ParsedRow to output stream.
     *
     * @param parsedRow to be written.
     * @param source    String representing source.
     */
    private void writeRow(ParsedRow parsedRow, ParsedRowComparisonResult matchedResult, String source) {
        this.rowIndex++;

        // Set the values for the table
        XSSFRow xlsRow = this.sheet.createRow(this.rowIndex);

        Iterator<Object> objectIterator = parsedRow.iterator();
        int colIndex = 0;
        xlsRow.createCell(colIndex++).setCellValue(source);
        while (objectIterator.hasNext()) {

            Object object = objectIterator.next();

            XSSFCell xlsCell = xlsRow.createCell(colIndex++);
            if (object instanceof String) {
                xlsCell.setCellValue((String) object);
            } else if (object instanceof Integer) {
                xlsCell.setCellValue((Integer) object);
            } else if (object instanceof Boolean) {
                xlsCell.setCellValue((Boolean) object);
            } else if (object instanceof Double) {
                xlsCell.setCellValue((Double) object);
            } else if (object instanceof Date) {
                xlsCell.setCellValue((Date) object);
            } else {
                xlsCell.setCellValue("unknown-type:" + object.toString());
            }

            if (matchedResult != null) {
                if (!matchedResult.getValueAt(colIndex - 2)) {
                    logger.info("setting unmatched value");
                    xlsCell.setCellStyle(this.cellStyleForUnMatched);
                } else {
                    logger.info("setting matched value");
                }
            }

        }

        // Set the maxColCount
        this.maxColCount = Integer.max(this.maxColCount, colIndex);
    }

    @Override
    public void postHook() throws IOException {

        XSSFTable table = XlsxUtil.createTable(this.workbook, this.sheet, "comparison-table", maxColCount, this.rowIndex + 1);

        // Save
        try (FileOutputStream fileOut = new FileOutputStream(this.outputFilepath)) {
            this.workbook.write(fileOut);
            this.workbook.close();
        }
    }

}
