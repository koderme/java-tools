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
    XSSFWorkbook workbook = XlsxUtil.createWorkBook();
    XSSFSheet sheet = XlsxUtil.createSheet(this.workbook, "details");
    XSSFTable table = XlsxUtil.createTable(this.workbook, this.sheet, "comparison-table");
    int rowIndex = 0;

    //private CellStyle cellStyleForMatched =
    //XlsxUtil.createCellSyle(this.workbook, IndexedColors.LIGHT_GREEN, IndexedColors.AUTOMATIC);
    private CellStyle cellStyleForUnMatched =
            XlsxUtil.createCellSyle(this.workbook, IndexedColors.LIGHT_YELLOW, IndexedColors.AUTOMATIC);

    public XlsFileOutputStream(String outputFilepath) {
        this.outputFilepath = outputFilepath;
    }

    @Override
    public void write(AllParsedRowComparisonResult compareResult) {

        Iterator<ParsedRow> rhsRowIterator = compareResult.getRhsRowIterator();
        Iterator<ParsedRowComparisonResult> rhsComparisonResultIterator = compareResult.getRhsComparisonResultIterator();

        // Write LHS
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

        // Set the values for the table
        XSSFRow xlsRow = this.sheet.createRow(this.rowIndex++);

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
                    //xlsCell.setCellStyle(this.cellStyleForMatched);
                }
            }

        }
    }

    @Override
    public void flush() throws IOException {
        // Save
        try (FileOutputStream fileOut = new FileOutputStream(this.outputFilepath)) {
            this.workbook.write(fileOut);
            this.workbook.close();
        }
    }

}
