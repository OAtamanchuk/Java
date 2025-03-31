package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.service.interfaces.IProductRegistry;

public class ProductRegistry implements IProductRegistry{
	
	private final List<Product> products = new ArrayList<Product>();
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void add(Product product) {
		
		if(!isValidId(product.getId())) {
			return;
		}
		
		product.setPrice(changePriceToUSD(product.getPrice())); 
		
		products.add(product);
	}
	
	private boolean isValidId(String id) {
		if(id == null || id.isEmpty()) {
			System.err.print("Can't add product with empty id field!");
			return false;
		}
		
		return true;
	}
	
	private float changePriceToUSD(float price) {
		ProductPriceConverter converter = new ProductPriceConverter();
		 
		return converter.convertByUSDRate(price);
	}
}
