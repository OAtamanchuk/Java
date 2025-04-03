package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;
    private String link;
    
    @Column(name = "description", length=1000)
    private String description;

    private float price;

    public Product() {}
    
    public Product(String id, String title, String link, String description, float price) {
    	this.id = id;
    	this.title = title;
    	this.link = link;
    	this.description = description; 
    	this.price = price;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public String[] getDataToString() {
        return new String[] { id, title, link, description, String.valueOf(price) };
    }
}
