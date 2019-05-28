package com.gharat.recon.common;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

public class XlsxUtil {

    public static XSSFWorkbook createWorkBook() {
        return new XSSFWorkbook();
    }

    public static XSSFSheet createSheet(XSSFWorkbook wb, String sheetName) {
        return wb.createSheet(sheetName);
    }

    public static XSSFTable createTable(XSSFWorkbook wb, XSSFSheet sheet, String tableName, int totalCols, int totalRows) {

        // Set auto filter on table
        AreaReference dataRegion = new AreaReference(new CellReference(0, 0), new CellReference(totalRows - 1, totalCols - 1));
        //sheet.setAutoFilter(CellRangeAddress.valueOf(dataRegion.formatAsString()));

        // Create
        XSSFTable table = sheet.createTable();

        // Create CTTable
        CTTable cttable = table.getCTTable();

        // Define styles
        CTTableStyleInfo tableStyle = cttable.addNewTableStyleInfo();
        tableStyle.setName("TableStyleMedium9");

        // Define Style Options
        tableStyle.setShowColumnStripes(false); //showColumnStripes=0
        tableStyle.setShowRowStripes(true); //showRowStripes=1

        // Define the data range including headers
        cttable.setRef(dataRegion.formatAsString());


        // Other attributes
        cttable.setName("name");
        cttable.setDisplayName("disp-name");
        cttable.setId(10L);

        // Add header cols
        CTTableColumns columns = cttable.addNewTableColumns();
        columns.setCount(totalCols); //define number of columns

        // Add column header
        for (int i = 0; i < totalCols; i++) {
            CTTableColumn column = columns.addNewTableColumn();
            column.setName("Column" + i);
            column.setId(i + 1);
        }

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

    public static XSSFCellStyle createCellStyle(XSSFWorkbook wb, IndexedColors fgColor, IndexedColors bgColor) {

        XSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(fgColor.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setFillBackgroundColor(bgColor.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

}
