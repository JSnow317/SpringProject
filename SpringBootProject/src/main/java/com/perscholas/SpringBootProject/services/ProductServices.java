package com.perscholas.SpringBootProject.services;

import java.util.List;
import java.util.Optional;

import com.perscholas.SpringBootProject.models.Product;

public interface ProductServices {

		Optional<Product> findById(Integer id);
		List<Product> findAll();


		
		
}