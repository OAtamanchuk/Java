package com.example.demo.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.demo.entity.Product;
import com.example.demo.service.interfaces.IItemParser;

public class EpicentrProductParser extends AbstractInfoBuilder implements IItemParser{
	public EpicentrProductParser(){
		super("https://epicentrk.ua/ua");
	}
	
	@Override
    protected void buildDescriptionString(StringBuilder builder, Element point) {
		builder.append(point.select("span").first().text())
			   .append(" ")
			   .append(point.select("span").last().text())
			   .append("; ");
    }
    
	@Override
    protected String buildCode(Element item) {
        try {
        	
            return Jsoup.connect(baseUrl+item.select("h2 a").attr("href")).get().select("._UGXcgc").text().substring(4).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

	@Override
    protected float buildPrice(Element item) {
        String price = item.select("data").first().attr("value");
        return Float.parseFloat(price);
    }
	
	public Product parseItem(Element item) {        
        return new Product(buildCode(item), buildName(item, "h2 a"),  
        			       buildURL(item, "h2 a"), buildDescription(getItemPage(item), "._fOohcu:first-child + li ._px1e3Z"), 
        			       buildPrice(item));
	}
	
	private Document getItemPage(Element item) {
		try {
			return Jsoup.connect(baseUrl+item.select("h2 a").attr("href")).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
