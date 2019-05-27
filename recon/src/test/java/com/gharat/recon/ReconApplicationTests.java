package com.gharat.recon;

import com.gharat.recon.common.XlsxUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReconApplicationTests {
    private static final String FILE_NAME1 = "/c/temp/MyFirstExcel.xlsx";

    private static final String FILE_NAME = "src/test/resources/test.xlsx";


    @Test
	public void contextLoads() {


        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle cellStyleForMatched =
                XlsxUtil.createCellSyle(workbook, IndexedColors.BLACK, IndexedColors.GREEN);
        CellStyle cellStyleForUnMatched =
                XlsxUtil.createCellSyle(workbook, IndexedColors.BLACK, IndexedColors.RED);

        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        int rowNum = 0;
        System.out.println("Creating excel");

        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        row.getCell(0).setCellStyle(style);


        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                    cell.setCellStyle(style);
                } else if (field instanceof Integer) {
                    cell.setCellStyle(style);
                    cell.setCellValue((Integer) field);
                }
            }


        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

}
