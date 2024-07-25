package konta.baitap.impl;

import konta.baitap.entity.Product;

import java.util.List;

public interface IProduct {
    void findAll(List<Product> products);
    void save(Product product);
    void deleteById(int id);
    int findById(int id);
    void updateProduct(int id, Product product);
    int findIndexById(int id);
}
