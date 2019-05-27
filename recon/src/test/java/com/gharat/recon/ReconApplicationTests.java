package com.gharat.recon;

import com.gharat.recon.common.XlsxUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReconApplicationTests {
    private static final String FILE_NAME1 = "/c/temp/MyFirstExcel.xlsx";

    private static final String FILE_NAME = "src/test/resources/test.xlsx";

    @Test
    public void test1() throws IOException {
        /* Read the input file that contains the data to be converted to table */
        FileInputStream input_document = new FileInputStream(new File("src/test/resources/test2.xlsx"));

        /* Create Workbook */
        XSSFWorkbook my_xlsx_workbook = new XSSFWorkbook(input_document);
        /* Read worksheet */
        XSSFSheet sheet = my_xlsx_workbook.getSheetAt(0);
        /* Create Table into Existing Worksheet */
        XSSFTable my_table = sheet.createTable();
        /* get CTTable object*/
        CTTable cttable = my_table.getCTTable();
        /* Define Styles */
        CTTableStyleInfo table_style = cttable.addNewTableStyleInfo();
        table_style.setName("TableStyleMedium9");
        /* Define Style Options */
        table_style.setShowColumnStripes(false); //showColumnStripes=0
        table_style.setShowRowStripes(true); //showRowStripes=1
        /* Define the data range including headers */
        AreaReference my_data_range = new AreaReference(new CellReference(0, 0), new CellReference(5, 2));
        /* Set Range to the Table */
        cttable.setRef(my_data_range.formatAsString());
        cttable.setDisplayName("MYTABLE");      /* this is the display name of the table */
        cttable.setName("Test");    /* This maps to "displayName" attribute in &lt;table&gt;, OOXML */
        cttable.setId(1L); //id attribute against table as long value
        /* Add header columns */
        CTTableColumns columns = cttable.addNewTableColumns();
        columns.setCount(3L); //define number of columns
        /* Define Header Information for the Table */
        for (int i = 0; i < 3; i++) {
            CTTableColumn column = columns.addNewTableColumn();
            column.setName("Column" + i);
            column.setId(i + 1);

        }
        /* Write output as File */
        FileOutputStream fileOut = new FileOutputStream("src/test/resources/test3.xlsx");
        my_xlsx_workbook.write(fileOut);
        fileOut.close();
    }

    @Test
    public void test33() throws IOException {

        /* Create Workbook */
        XSSFWorkbook my_xlsx_workbook = new XSSFWorkbook();
        /* Read worksheet */
        XSSFSheet sheet = my_xlsx_workbook.createSheet();
        /* Create Table into Existing Worksheet */
        XSSFTable my_table = sheet.createTable();
        /* get CTTable object*/
        CTTable cttable = my_table.getCTTable();
        /* Define Styles */
        CTTableStyleInfo table_style = cttable.addNewTableStyleInfo();
        table_style.setName("TableStyleMedium9");
        /* Define Style Options */
        table_style.setShowColumnStripes(false); //showColumnStripes=0
        table_style.setShowRowStripes(true); //showRowStripes=1
        /* Define the data range including headers */
        int totalCol = 5;
        AreaReference my_data_range = new AreaReference(new CellReference(0, 0), new CellReference(10, totalCol - 1));
        /* Set Range to the Table */
        cttable.setRef(my_data_range.formatAsString());
        cttable.setDisplayName("MYTABLE");      /* this is the display name of the table */
        cttable.setName("Test");    /* This maps to "displayName" attribute in &lt;table&gt;, OOXML */
        cttable.setId(1L); //id attribute against table as long value
        /* Add header columns */
        CTTableColumns columns = cttable.addNewTableColumns();
        columns.setCount(totalCol); //define number of columns
        /* Define Header Information for the Table */
        for (int i = 0; i < totalCol; i++) {
            CTTableColumn column = columns.addNewTableColumn();
            column.setName("Column-33-" + i);
            column.setId(i + 1);

        }



        /* Write output as File */
        FileOutputStream fileOut = new FileOutputStream("src/test/resources/test33.xlsx");
        my_xlsx_workbook.write(fileOut);
        fileOut.close();
    }

}
