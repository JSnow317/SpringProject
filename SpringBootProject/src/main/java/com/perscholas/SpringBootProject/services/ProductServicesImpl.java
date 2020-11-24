package com.perscholas.SpringBootProject.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.perscholas.SpringBootProject.dao.ProductRepo;
import com.perscholas.SpringBootProject.models.Product;

@Service
public class ProductServicesImpl implements ProductServices {

	private ProductRepo productRepo;
	
	@Autowired
	public ProductServicesImpl(ProductRepo theProductRepo) {
		this.productRepo = theProductRepo;
	}

	@Override
	public Optional<Product> findById(Integer id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}
	
	


	
	
}