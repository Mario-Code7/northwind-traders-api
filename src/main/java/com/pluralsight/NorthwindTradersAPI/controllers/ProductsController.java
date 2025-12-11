package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.data.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    ProductDao productDao;

    @RequestMapping(path="products")
    public List<Product> getAll() {
        List<Product> products = productDao.getAll();
        return products;
    }

    @RequestMapping(path="products/{id}")
    public Product findId(@PathVariable int id) {
        return productDao.getById(id);
    }

//    @RequestMapping(path="products/{id}")
//    public Product findById(@PathVariable int id) {
//        List<Product> products = getProducts();
//        Product foundProduct = products.stream()
//                .filter(product -> product.getProductId() == id)
//                .findFirst().orElse(null);
//        return foundProduct;
//    }

//    @RequestMapping(path="products/{name}")
//    public Product findByName(@PathVariable String name) {
//        List<Product> products = getProducts();
//        Product productName = products.stream()
//                .filter(product -> product.getProductName() .equals(name))
//                .findFirst().orElse(null);
//        return productName;
//    }


//    private static List<Product> getProducts() {
//        List<Product> products = new ArrayList<>();
//
//        products.add(new Product(1, "Laptop", 10, 999.99));
//        products.add(new Product(2, "Headphones", 20, 49.99));
//        products.add(new Product(3, "Mouse", 20, 19.99));
//        products.add(new Product(4, "Desk Chair", 30, 129.99));
//        return products;
//    }
}
