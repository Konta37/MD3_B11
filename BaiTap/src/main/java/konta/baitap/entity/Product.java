package konta.baitap.entity;

import konta.baitap.services.ProductService;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String producer; //nha san xuat
    private boolean status;

    public Product() {
    }

    public Product(int id, String name, double price, String description, String producer, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.producer = producer;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static int inputProductId(){
        int max = 0;
        if(ProductService.productList.isEmpty()){
            return 0;
        }
        for(int i=0;i<ProductService.productList.size();i++){
            if (ProductService.productList.get(i).getId() >= max){
                max = ProductService.productList.get(i).getId();
            }
        }
        return max+1;
    }
}
