package com.gharat.recon.common;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxUtil {

    public static XSSFWorkbook createWorkBook() {
        return new XSSFWorkbook();
    }

    public static XSSFSheet createSheet(XSSFWorkbook wb, String sheetName) {
        return wb.createSheet(sheetName);
    }

    public static XSSFTable createTable(XSSFWorkbook wb, XSSFSheet sheet, String tableName) {

        // Create
        XSSFTable table = sheet.createTable();
        table.setName(tableName);
        table.setDisplayName(tableName);

        // Set some style
        // For now, create the initial style in a low-level way
        table.getCTTable().addNewTableStyleInfo();
        table.getCTTable().getTableStyleInfo().setName("TableStyleMedium2");

        return table;
    }

    public static void createStle() {
        // Style the table
//        XSSFTableStyleInfo style = (XSSFTableStyleInfo) table.getStyle();
//        style.setName("TableStyleMedium2");
//        style.setShowColumnStripes(false);
//        style.setShowRowStripes(true);
//        style.setFirstColumn(false);
//        style.setLastColumn(false);
//        style.setShowRowStripes(true);
//        style.setShowColumnStripes(true);
    }

    public static XSSFCellStyle createCellSyle(XSSFWorkbook wb, IndexedColors fgColor, IndexedColors bgColor) {

        XSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(fgColor.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setFillBackgroundColor(bgColor.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

}
