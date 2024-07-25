package konta.bai2_product_category.repository;

import konta.bai2_product_category.model.Product;

import java.util.List;

public interface IProductRespository {
    void findAll(List<Product> products);
    void save(Product product);
    void deleteById(int id);
    void updateProduct(int id, Product product);
    int findIndexById(int id);
    List<Product> searchProductsByName(String name);
}
