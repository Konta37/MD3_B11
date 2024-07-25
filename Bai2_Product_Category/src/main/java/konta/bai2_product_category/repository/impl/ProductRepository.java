package konta.bai2_product_category.repository.impl;

import konta.bai2_product_category.model.Product;
import konta.bai2_product_category.repository.IProductRespository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRespository {
    public static List<Product> productList = new ArrayList<Product>();

    static {
        productList.add(new Product(Product.inputProductId(),"White Pants",12000,25,CategoryRespository.categoryList.get(0),true) );
        productList.add(new Product(Product.inputProductId(),"Black Pants",12000,20,CategoryRespository.categoryList.get(0),true) );
        productList.add(new Product(Product.inputProductId(),"White Shirts",12000,23,CategoryRespository.categoryList.get(1),true) );
    }

    @Override
    public void findAll(List<Product> products) {
        if (productList.isEmpty()) {
            System.out.println("Product list is empty");
        }else {
            for (Product product : productList) {
                System.out.println(product.getName());
            }
        }
    }

    @Override
    public void save(Product product) {
        productList.add(product);
        System.out.println("Product added");
    }

    @Override
    public void deleteById(int id) {
        int indexDelete = findIndexById(id);
        if (indexDelete >= 0) {
            productList.remove(indexDelete);
            System.out.println("Delete successfully");
        } else {
            System.err.println("Can't find order with id = " + id);
        }
    }


    @Override
    public void updateProduct(int id, Product product) {
        int index = findIndexById(id);
        if (index >= 0) {
            productList.set(index, product);
        }
    }

    @Override
    public  int findIndexById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}
