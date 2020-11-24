package com.perscholas.SpringBootProject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perscholas.SpringBootProject.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	
	Optional<Product> findById(Integer id);

}
