package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UrlRequest;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.EpicentrProductParser;
import com.example.demo.service.ExcelService;

import com.example.demo.service.parserStrategies.EpicentrParserStrategy;
import com.example.demo.service.parserStrategies.ParserContext;

import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/list")
public class ListController {

	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private IProductRepository productRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<byte[]> getProducts() {

        byte[] excelBytes = excelService.makeFileFromProducts();

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=products.xlsx")
                .contentLength(excelBytes.length)
                .body(excelBytes);
    }

    @PostMapping
    public ResponseEntity<String> createProductsList(@RequestBody UrlRequest request) {  	
    	ParserContext context = new ParserContext(new EpicentrParserStrategy(request.getUrl(), new EpicentrProductParser()));
        
    	productRepository.saveAll(context.executeStrategy());

        return ResponseEntity.ok("List successfully added");
    }
}
