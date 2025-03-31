package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.entity.Product;

public interface IProductRegistry {
	public List<Product> getProducts();
	public void add(Product product); 
}
