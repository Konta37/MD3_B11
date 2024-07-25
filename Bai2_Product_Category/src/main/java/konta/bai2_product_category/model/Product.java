package konta.bai2_product_category.model;

import konta.bai2_product_category.repository.impl.ProductRepository;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private Category category;
    private boolean status;

    public Product() {
    }

    public Product(int id, String name, double price, int stock, Category category, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static int inputProductId(){
        int max = 0;
        if(ProductRepository.productList.isEmpty()){
            return 0;
        }
        for(int i=0;i<ProductRepository.productList.size();i++){
            if (ProductRepository.productList.get(i).getId() >= max){
                max = ProductRepository.productList.get(i).getId();
            }
        }
        return max+1;
    }

}
