package konta.baitap.services;

import konta.baitap.entity.Product;
import konta.baitap.impl.IProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProduct {

    public static List<Product> productList = new ArrayList<Product>();

    static {
        productList.add(new Product(Product.inputProductId(),"Konta",12000,"hihi","hiha",true) );
        productList.add(new Product(Product.inputProductId(),"Konta2",12000,"hihi","hiha",true) );
        productList.add(new Product(Product.inputProductId(),"Konta3",12000,"hihi","hiha",true) );
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
    public int findById(int id) {
        return 0;
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
    public static int findIndexById2(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    public static List<Product> searchProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}
