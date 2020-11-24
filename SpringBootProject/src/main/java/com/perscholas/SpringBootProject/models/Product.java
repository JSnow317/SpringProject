package com.perscholas.SpringBootProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int product_id;
	private String product_name;
	private String product_type;
	private int product_stock;
	private float product_price;
	

	public Product() {
		// TODO Auto-generated constructor stub
	}


	public Product(String product_name, String product_type, int product_stock, float product_price) {
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_stock = product_stock;
		this.product_price = product_price;
	}
	
	public Product(int product_id, String product_name, String product_type, int product_stock, float product_price) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_stock = product_stock;
		this.product_price = product_price;
	}


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getProduct_type() {
		return product_type;
	}


	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}


	public int getProduct_stock() {
		return product_stock;
	}


	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}


	public float getProduct_price() {
		return product_price;
	}


	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + product_id;
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		result = prime * result + Float.floatToIntBits(product_price);
		result = prime * result + product_stock;
		result = prime * result + ((product_type == null) ? 0 : product_type.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (product_id != other.product_id)
			return false;
		if (product_name == null) {
			if (other.product_name != null)
				return false;
		} else if (!product_name.equals(other.product_name))
			return false;
		if (Float.floatToIntBits(product_price) != Float.floatToIntBits(other.product_price))
			return false;
		if (product_stock != other.product_stock)
			return false;
		if (product_type == null) {
			if (other.product_type != null)
				return false;
		} else if (!product_type.equals(other.product_type))
			return false;
		return true;
	}
	
	
	
	
	
	

}
