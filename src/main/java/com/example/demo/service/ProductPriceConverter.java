package com.example.demo.service;

import org.json.JSONArray;
import org.springframework.web.client.RestTemplate;

public class ProductPriceConverter {
	private final static String API_LINK = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
	
    public float convertByUSDRate(float priceUAH) {
        String response = getRateRequest();
        return response == null ? -1 : (priceUAH / Float.parseFloat(new JSONArray(response).getJSONObject(1).getString("buy")));
    }
    
    private String getRateRequest() {
    	RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.getForObject(API_LINK, String.class);
    }
}
