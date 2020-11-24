package com.perscholas.SpringBootProject.controllers;



import org.perscholas.SpringBootProject.exception.EmptyStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.perscholas.SpringBootProject.services.CartService;
import com.perscholas.SpringBootProject.services.ProductServices;


@Controller
public class ShoppingCartController {


    private CartService shoppingCartService;

    private ProductServices productService;

    @Autowired
    public ShoppingCartController(CartService shoppingCartService, ProductServices productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }
    
//  @RequestMapping("/shoppingCart")
//  public String getshoppingCart(Model model) {
//  	model.addAttribute("products", shoppingCartService.getCart());
//  	model.addAttribute("total", shoppingCartService.getTotal());
//  	return "shoppingCart";
//  }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getCart());
        System.out.println("Cart items" + shoppingCartService.getCart());
        modelAndView.addObject("total", shoppingCartService.getTotal());
        System.out.println("Total for cart items" + shoppingCartService.getTotal());
        return modelAndView;
    }


    @GetMapping("/shoppingCart/addProduct/{product_id}")
    public ModelAndView addProductToCart(@PathVariable("product_id") Integer productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{product_id}")
    public ModelAndView removeProductFromCart(@PathVariable("product_id") Integer productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkOut();
        } catch (EmptyStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}