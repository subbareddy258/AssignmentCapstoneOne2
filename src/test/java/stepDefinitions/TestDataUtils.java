package stepDefinitions;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class TestDataUtils {
        public static Object[][] readTestData(String filePath, String sheetName) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rowCount][colCount];


            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    Object cellValue;
                    if (cell.getCellType() == CellType.STRING) {
                        cellValue = cell.getStringCellValue();
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        cellValue = cell.getNumericCellValue();
                    } else if(cell.getCellType() == CellType.BLANK)
                    {
                        cellValue = cell.getBooleanCellValue();
                    }

                    else {
                        cellValue = null;
                    }
                    data[i - 1][j] = cellValue;
                }
            }

            workbook.close();
            fileInputStream.close();

            return data;
        }
    }


