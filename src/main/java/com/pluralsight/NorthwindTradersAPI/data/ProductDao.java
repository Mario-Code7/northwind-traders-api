package com.pluralsight.NorthwindTradersAPI.data;


import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();
    Product getById(int id);
    Product insert(Product product);
    void update(int productId,  Product product);
    void delete(int id);
}
