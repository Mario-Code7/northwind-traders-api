package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {

    @RequestMapping(path="categories")
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        return categories;
    }

    @RequestMapping(path="categories/{id}")
    public Category findId(@PathVariable int id) {
        List<Category> categories = getCategories();
        Category foundCategory = categories.stream()
                .filter(category -> category.getCategoryId() == id)
                .findFirst().orElse(null);
        return foundCategory;
    }

//    @RequestMapping(path="categories/{name}")
//    public Category findByName(@PathVariable String name) {
//        List<Category> categories = getCategories();
//        Category catName = categories.stream()
//                .filter(category -> category.getCategoryName().equals(name))
//                .findFirst().orElse(null);
//        return catName;
//    }

    private static List<Category> getCategories() {

        List<Category> categories = new ArrayList<>();

        categories.add(new Category(1, "Electronics"));
        categories.add(new Category(2, "Clothing"));
        categories.add(new Category(3, "Books"));
        categories.add(new Category(4, "Home & Garden"));
        categories.add(new Category(5, "Sports & Outdoors"));
        categories.add(new Category(6, "Food & Beverages"));
        categories.add(new Category(7, "Health & Beauty"));
        categories.add(new Category(8, "Toys & Games"));
        categories.add(new Category(9, "Automotive"));
        categories.add(new Category(10, "Office Supplies"));
        return categories;
    }
}
