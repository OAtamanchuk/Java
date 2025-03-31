package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Product;

@Component
public class ExcelProductGenerator {
    private final static String[] HEADERS = { "Code", "Title", "Link", "Description", "Price" };
    private final static String SHEET_NAME = "Products";


    public byte[] generateExcelProductInBytes(List<Product> products, XSSFWorkbook workbook) {
    	
    	ExcelProductBuilder excelBuilder = new ExcelProductBuilder(workbook, SHEET_NAME);
    	
    	excelBuilder.addHeader(HEADERS);
    	excelBuilder.addBody(products);
    	
    	return getWorkBookInBytes(workbook);
    }
    
    private byte[] getWorkBookInBytes(XSSFWorkbook workbook) {
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        try {
            workbook.write(byteArr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArr.toByteArray();
    }
}
