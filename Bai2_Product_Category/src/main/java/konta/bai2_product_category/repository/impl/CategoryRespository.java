package konta.bai2_product_category.repository.impl;

import konta.bai2_product_category.model.Category;
import konta.bai2_product_category.repository.ICategoryRespository;

import java.util.ArrayList;
import java.util.List;

public class CategoryRespository implements ICategoryRespository {

    public static List<Category> categoryList = new ArrayList<>();
    private static Category category = new Category();
    
    static {
        categoryList.add(new Category(category.inputCategoryId(),"Pants",true));
        categoryList.add(new Category(category.inputCategoryId(),"Shirts",true));
        categoryList.add(new Category(category.inputCategoryId(),"Hats",true));
    }
    
    @Override
    public void findAll(List<Category> categoryList) {
        if (categoryList.isEmpty()) {
            System.out.println("Product list is empty");
        }else {
            for (Category product : categoryList) {
                System.out.println(product.getName());
            }
        }
    }

    @Override
    public void save(Category category) {
        categoryList.add(category);
        System.out.println("Category added");
    }

    @Override
    public void deleteById(int id) {
        int indexDelete = findIndexById(id);
        if (indexDelete >= 0) {
            categoryList.remove(indexDelete);
            System.out.println("Delete successfully");
        } else {
            System.err.println("Can't find order with id = " + id);
        }
    }

    @Override
    public void updateCategory(int id, Category category) {
        int index = findIndexById(id);
        if (index >= 0) {
            categoryList.set(index, category);
        }
    }

    @Override
    public List<Category> searchCategoriesByName(String name) {
        List<Category> result = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(category);
            }
        }
        return result;
    }

    @Override
    public int findIndexById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
