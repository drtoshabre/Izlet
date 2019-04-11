package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import resources.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIUtility {
	private static HSSFWorkbook workbookXLS;
	private static HSSFSheet workSheetXLS;
	private static HSSFRow rowXLS;
	private static HSSFCell cellXLS;
	private static XSSFWorkbook workbookXLSX;
	private static XSSFSheet workSheetXLSX;
	private static XSSFRow rowXLSX;
	private static XSSFCell cellXLSX;
	private static DataFormatter formatter;

	// Sets excel XLS file and sheet
	public static void setXLSFile(String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream(FinalVariables.PATH + FinalVariables.FILE_NAME);
		workbookXLS = new HSSFWorkbook(fis);
		workSheetXLS = workbookXLS.getSheet(sheetName);
		formatter = new DataFormatter();

	}

	// Returns work sheet
	public static HSSFSheet getCurrentSheet() {
		return workSheetXLS;
	}

	public static int getNUmberOfCellsInCurrentRow() {
		return rowXLS.getLastCellNum();
	}

	// Sets excel XLSX file and sheet
	public static void setXLSXFile(String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream(FinalVariables.PATH + FinalVariables.FILE_NAME);

		workbookXLSX = new XSSFWorkbook(fis);
		workSheetXLSX = workbookXLSX.getSheet(sheetName);
		formatter = new DataFormatter();
	}

	// Gets data from specified cell and format it to String
	public static String getDataXLSCell(int rowNumber, int columnNumber) {
		cellXLS = workSheetXLS.getRow(rowNumber).getCell(columnNumber);

		return formatter.formatCellValue(cellXLS);
	}

	// Gets data from specified cell and format it to String
	public static String getDataXLSXCell(int rowNumber, int columnNumber) {
		cellXLSX = workSheetXLSX.getRow(rowNumber).getCell(columnNumber);

		return formatter.formatCellValue(cellXLSX);
	}

	// Enters specified data in specified cell
	public static void setXLSCellData(int rowNumber, int columnNumber, String dataToInput) throws Exception {
		rowXLS = workSheetXLS.getRow(rowNumber);
		cellXLS = rowXLS.getCell(columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if (cellXLS == null) {
			cellXLS = rowXLS.createCell(columnNumber);
			cellXLS.setCellValue(dataToInput);
		} else {
			cellXLS.setCellValue(dataToInput);
		}
		FileOutputStream fos = new FileOutputStream(FinalVariables.PATH + FinalVariables.FILE_NAME);
		workbookXLS.write(fos);
		fos.flush();
		fos.close();
	}

	// Enters specified data in specified cell
	public static void setXLSXCellData(int rowNumber, int columnNumber, String dataToInput) throws Exception {
		rowXLSX = workSheetXLSX.getRow(rowNumber);
		cellXLSX = rowXLSX.getCell(columnNumber, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

		if (cellXLSX == null) {
			cellXLSX = rowXLSX.createCell(columnNumber);
			cellXLSX.setCellValue(dataToInput);
		} else {
			cellXLSX.setCellValue(dataToInput);
		}
		FileOutputStream fos = new FileOutputStream(FinalVariables.PATH + FinalVariables.FILE_NAME);
		workbookXLSX.write(fos);
		fos.flush();
		fos.close();
	}

}
