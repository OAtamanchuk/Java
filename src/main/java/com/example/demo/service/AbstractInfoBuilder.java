package com.example.demo.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class AbstractInfoBuilder {
    protected final String baseUrl;
	
    public AbstractInfoBuilder(String baseUrl) {
    	this.baseUrl = baseUrl;
    }
    
    protected String buildDescription(Document page, String selector) {
        return composeDescription(page.select(selector));
    }
    
    protected String buildName(Element item, String selector) {
    	return item.select(selector).text();
    }
    
    protected String buildURL(Element item, String selector) {
    	return baseUrl + item.select(selector).attr("href");
    }
    
    protected String composeDescription(Elements points) {
    	StringBuilder builder = new StringBuilder();
    	
    	for (Element point : points) {
    		buildDescriptionString(builder, point);
        }
    	
    	return builder.toString();
    }

    protected abstract void buildDescriptionString(StringBuilder builder, Element point);
    
    protected abstract String buildCode(Element item);

    protected abstract float buildPrice(Element item);
}
