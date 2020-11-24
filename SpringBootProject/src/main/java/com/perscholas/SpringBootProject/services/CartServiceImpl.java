package com.perscholas.SpringBootProject.services;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.perscholas.SpringBootProject.exception.EmptyStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.perscholas.SpringBootProject.dao.ProductRepo;
import com.perscholas.SpringBootProject.models.Product;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartServiceImpl implements CartService{
	
	private CartService cartservice;
	private final ProductRepo productRepo;
	Map<Product, Integer> productMap= new HashMap<>();
	
	@Autowired
	public CartServiceImpl(ProductRepo productRepo) {
		// TODO Auto-generated constructor stub
		this.productRepo = productRepo;
	}

	// If product is in cart, increment value by 1 
	// If cart is not added in cart, set value too 1
	
//	@Override
//	public void addProduct(Product product) {
//		// TODO Auto-generated method stub
//		
//		Integer id = product.getProduct_id();
//		
//		System.out.println("Before list" + productMap);
//		if(productMap.isEmpty()) {
//			productMap.put(product, 1);
//			System.out.println("Initialization" + productMap);
//		} else {
//			productMap.forEach((key, value) -> {
//				if(key.getProduct_id() == id) {
//			    	productMap.replace(product, (productMap.get(product)) + 1);
//			    	System.out.println(" in if list" + productMap);
//			    }  else{
//					productMap.put(product, 1);
//					System.out.println("Adding new product to list" + productMap);
//				}
//			});
//		}
//		
//	}
	
	// My implementation of Adding Product
	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		System.out.println("product doubled");
		if(productMap.containsKey(product)) {
				productMap.replace(product, productMap.get(product) + 1);
		} else{
	        productMap.put(product, 1);
		}
        for(Map.Entry<Product, Integer> entry : productMap.entrySet())
        {
        //Check if i am getting correct product values
        System.out.println("Add Key : "+ entry.getKey().getProduct_name() + "   Value : "+ entry.getValue());
        }
		
	}

	// Check if product is in map, if so, check if amount in cart is greater than one
	// If products in cart > 1, replace current entry and decrement value by one.
	// If only one item in cart remove the one product	
	
	@Override
	public void removeProduct(Product product) {
		
		if(productMap.containsKey(product)) {
			if(productMap.get(product) > 1) {
				productMap.replace(product, productMap.get(product) -1);
			} else if(productMap.get(product) == 1) {
				productMap.remove(product);
			}
		}
	}

	// Returns current map (cant change it)
	
	@Override
	public Map<Product, Integer> getCart() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableMap(productMap);
	}

	// 
	
    @Override
    public void checkOut() throws EmptyStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepo.getOne(entry.getKey().getProduct_id());
            if (product.getProduct_stock() < entry.getValue())
                throw new EmptyStockException(product);
            entry.getKey().setProduct_stock(product.getProduct_stock() - entry.getValue());
        }
        productRepo.saveAll(productMap.keySet());
        productRepo.flush();
        productMap.clear();
    }

	
	public float getTotal() {
		
		float tot = 0;
        for(Map.Entry<Product, Integer> entry : productMap.entrySet())
        {
        	//Check if i am getting correct product values
//            System.out.println("Key : "+entry.getKey().getProduct_name() + "   Value : "+entry.getValue());
            
            tot += entry.getKey().getProduct_price()*entry.getValue();   
        }
        
        return tot;
	}



}
