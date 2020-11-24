package com.perscholas.SpringBootProject.services;

import java.util.Map;

import org.perscholas.SpringBootProject.exception.EmptyStockException;

import com.perscholas.SpringBootProject.models.Product;



public interface CartService {
	
	void addProduct(Product product);
	void removeProduct(Product product);
	Map<Product, Integer> getCart();
	void checkOut() throws EmptyStockException;
	
	float getTotal();

}
