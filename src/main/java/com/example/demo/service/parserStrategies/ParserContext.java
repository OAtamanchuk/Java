package com.example.demo.service.parserStrategies;

import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.service.interfaces.IParserStrategy;

public class ParserContext {
	
	private IParserStrategy parserStrategy;
	
	public ParserContext(IParserStrategy parserStrategy) {
		this.parserStrategy = parserStrategy;
	}
	
	public List<Product> executeStrategy() {
		return parserStrategy.execute();
	}
}
