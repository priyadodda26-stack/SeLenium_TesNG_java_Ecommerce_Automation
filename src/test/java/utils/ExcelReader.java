package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {

    public static Object[][] getLoginData() throws Exception {

        FileInputStream fis = new FileInputStream(
                "src/test/resources/testdata.xlsx");

        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet("ValidLogin");

        int rows = sheet.getLastRowNum(); // data rows (excluding header)
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= rows; i++) {

            Row row = sheet.getRow(i);

            for (int j = 0; j < cols; j++) {

                Cell cell = (row == null) ? null : row.getCell(j);

                data[i - 1][j] =
                        (cell == null) ? "" : formatter.formatCellValue(cell).trim();
            }
        }

        wb.close();
        fis.close();

        return data;
    }
}