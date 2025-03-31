package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.repository.IProductRepository;

@Controller()
@RequestMapping("/table")
public class TableController {
    @Autowired
    private IProductRepository productRepository;
	
	@GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
		return "table/index";
    }
}
