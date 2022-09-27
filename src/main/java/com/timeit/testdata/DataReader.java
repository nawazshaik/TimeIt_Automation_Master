package com.timeit.testdata;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.timeit.base.TestBase;
import com.timeit.pages.IOS_SignUpPage;

public class DataReader extends TestBase {

	private static Logger logger = Logger.getLogger(DataReader.class.getName());
	
	// Data Driven Testing

	public static FileInputStream fis;
	public static Workbook wb;
	public static Sheet sh;
	public static Cell cell;
	public static Map<String, Integer> columns = new HashMap<>();

	public static String getCellData(String columnName, int rownum) {
		logger.info("Getting "+columnName +" from Excel File");
		String testDataExcelPath = null;
		String testDataExcelFileName = prop.getProperty("TestDataExcelFileName");
		String sheetName = prop.getProperty("TestDataSheetName");
		testDataExcelPath = System.getProperty("user.dir") + "//" + testDataExcelFileName;
		setExcelFile(testDataExcelPath, sheetName);
		return getCellData(rownum, columns.get(columnName));
	}

	public static void setExcelFile(String ExcelPath, String SheetName) {
		try {
			File f = new File(ExcelPath);

			if (!f.exists()) {
				f.createNewFile();
				System.out.println("File doesn't exist, so created!");
			}

			fis = new FileInputStream(ExcelPath);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(SheetName);
			if (sh == null) {
				sh = wb.createSheet(SheetName);
			}

			// adding all the column header names to the map 'columns'
			sh.getRow(0).forEach(cell -> {
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String getCellData(int rownum, int colnum) {
		try {
			cell = sh.getRow(rownum).getCell(colnum);
			String CellData = null;
			switch (cell.getCellType()) {
			case STRING:
				CellData = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					CellData = String.valueOf(cell.getDateCellValue());
				} else {
					CellData = String.valueOf((long) cell.getNumericCellValue());
				}
				break;
			case BOOLEAN:
				CellData = Boolean.toString(cell.getBooleanCellValue());
				break;
			case BLANK:
				CellData = "";
				break;
			default:
				break;
			}
			return CellData;
		} catch (Exception e) {
			return "";
		}
		
	}
	
	public static void setCellData(String Data) {
		logger.info("Adding data to Excel File");
		try {
			String testDataExcelPath = null;
			String testDataExcelFileName = prop.getProperty("TestDataExcelFileName");
			String sheetName = prop.getProperty("TestDataSheetName");
			testDataExcelPath = System.getProperty("user.dir") + "//" + testDataExcelFileName;
			File file = new File(testDataExcelPath);
			FileInputStream inputStream = new FileInputStream(file);

			// creating workbook instance that refers to .xls file
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);

			// creating a Sheet object using the sheet Name
			XSSFSheet sheet = wb.getSheet(sheetName);

			// Create a row object to retrieve row at index 3
			XSSFRow row = sheet.getRow(10);

			// create a cell object to enter value in it using cell Index
			row.createCell(1).setCellValue(Data);

			// write the data in excel using output stream
			FileOutputStream outputStream = new FileOutputStream(testDataExcelPath);
			wb.write(outputStream);
			wb.close();
			logger.info("Added data to Excel File Successfuly");
		} catch (Exception e) {

		}

	}

}