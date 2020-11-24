package org.perscholas.SpringBootProject.exception;

import com.perscholas.SpringBootProject.models.Product;

public class EmptyStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public EmptyStockException() {
        super(DEFAULT_MESSAGE);
    }

    public EmptyStockException(Product product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getProduct_name(), product.getProduct_stock()));
    }

}
