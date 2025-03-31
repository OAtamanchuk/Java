package com.example.demo.service.parserStrategies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.entity.Product;
import com.example.demo.service.AbstractInfoBuilder;
import com.example.demo.service.EpicentrProductParser;
import com.example.demo.service.ProductRegistry;
import com.example.demo.service.interfaces.IItemParser;
import com.example.demo.service.interfaces.IParserStrategy;
import com.example.demo.service.interfaces.IProductRegistry;

public class EpicentrParserStrategy implements IParserStrategy{
    private String pageUrl;
    private final IProductRegistry productRegistry;
    private final IItemParser itemParser;
    
	public EpicentrParserStrategy(String pageUrl, IItemParser itemParser) {
        this.pageUrl = pageUrl;
        this.productRegistry = new ProductRegistry();
        this.itemParser = itemParser;
	}
	
	public List<Product> execute() {
	  parseItemsInfoAndRegister(getItemsFromPage());
      return productRegistry.getProducts();
	}
	
	private Elements getItemsFromPage() {
		try {
			return Jsoup.connect(pageUrl).get().select("._eVPXSX");
		} catch (IOException e) {
			e.printStackTrace();
		}

        return new Elements();
	}
	
	private void parseItemsInfoAndRegister(Elements items) {
	    for (Element item : items) {
        	productRegistry.add(itemParser.parseItem(item));
        }	
	}
}
