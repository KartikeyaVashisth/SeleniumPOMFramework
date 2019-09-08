package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\kartikeya.v\\eclipse-workspace4\\SeleniumFrameworkPractise\\src\\main\\java"
			+ "\\com\\qa\\hubspot\\testdata\\apptestdata.xlsx";

	public static Object[][] getTestData(String sheetName) {
		//Fetch the data from Excel Sheet.
		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file); 
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; //getLastRowNum will get last row where data is filled.
		
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i+1).getCell(k).toString(); // getRow(i+1) because actual data will be on first row.
			}
		}
		
		return data;
	}
}
