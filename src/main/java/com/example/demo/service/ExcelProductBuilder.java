package com.example.demo.service;

import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.example.demo.entity.Product;


public class ExcelProductBuilder {
	private final XSSFSheet sheet;
	private int rowNum = 0;
	
	public ExcelProductBuilder(XSSFWorkbook workbook, String sheetName) {
		this.sheet = workbook.createSheet(sheetName);
	}

    public void addHeader(String[] headers) {
        XSSFRow row = sheet.createRow(rowNum++);
        int cellNum = 0;

        for (String column : headers) {
            XSSFCell cell = row.createCell(cellNum++);
            cell.setCellValue(column);
        }
    }

    public void addBody(List<Product> products) {
        for (Product product : products) {
            XSSFRow row = sheet.createRow(rowNum++);
            String[] data = product.getDataToString();

            for (int col = 0; col < data.length; col++) {
                XSSFCell cell = row.createCell(col);
                cell.setCellValue(data[col]);
            }
        }
    }
}
