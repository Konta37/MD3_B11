package konta.bai2_product_category.model;

import konta.bai2_product_category.repository.impl.CategoryRespository;

public class Category {
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int inputCategoryId(){
        int max = 0;
        if(CategoryRespository.categoryList.isEmpty()){
            return 0;
        }
        for(int i=0;i<CategoryRespository.categoryList.size();i++){
            if (CategoryRespository.categoryList.get(i).getId() >= max){
                max = CategoryRespository.categoryList.get(i).getId();
            }
        }
        return max+1;
    }
}
