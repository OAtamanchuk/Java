package com.example.demo.service;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.example.demo.repository.IProductRepository;

@Service
public class ExcelService {

    private IProductRepository productRepository;
    private ExcelProductGenerator excelGenerator;

    public ExcelService(IProductRepository productRepository, ExcelProductGenerator excelGenerator) {
        this.productRepository = productRepository;
        this.excelGenerator = excelGenerator;
    }

    public byte[] makeFileFromProducts() {
    	return excelGenerator.generateExcelProductInBytes(productRepository.findAll(), new XSSFWorkbook());
    }

}