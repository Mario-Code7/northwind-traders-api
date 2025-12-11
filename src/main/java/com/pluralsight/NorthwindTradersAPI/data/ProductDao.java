package com.pluralsight.NorthwindTradersAPI.data;


import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();
    Product getById(int id);
}
