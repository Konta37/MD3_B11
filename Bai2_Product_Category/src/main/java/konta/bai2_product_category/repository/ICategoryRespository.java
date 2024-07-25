package konta.bai2_product_category.repository;

import konta.bai2_product_category.model.Category;

import java.util.List;

public interface ICategoryRespository {
    void findAll(List<Category> categoryList);
    void save(Category category);
    void deleteById(int id);
    void updateCategory(int id, Category category);
    int findIndexById(int id);
    List<Category> searchCategoriesByName(String name);
}
