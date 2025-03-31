package com.example.demo.service.interfaces;

import org.jsoup.nodes.Element;

import com.example.demo.entity.Product;

public interface IItemParser {
	public Product parseItem(Element item);
}
