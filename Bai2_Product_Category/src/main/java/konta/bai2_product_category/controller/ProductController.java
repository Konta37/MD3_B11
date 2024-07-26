package konta.bai2_product_category.controller;

import konta.bai2_product_category.model.Category;
import konta.bai2_product_category.model.Product;
import konta.bai2_product_category.repository.impl.CategoryRespository;
import konta.bai2_product_category.repository.impl.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product-controller")
public class ProductController extends HttpServlet {
    private final ProductRepository productRepository = new ProductRepository();
    private Product productModel = new Product();
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "details":
                showDetail(request,response);
                break;
            case "search":
                searchProductsByName(request,response);
                break;
            default:
                listProducts(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "save":
                saveProduct(request,response);
                break;
            case "update":
                updateProduct(request,response);
                break;
            default:
                listProducts(request,response);
                break;
        }

    }
    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("----");
        for (int i = 0; i < ProductRepository.productList.size(); i++) {
            System.out.println(ProductRepository.productList.get(i).isStatus());
        }

        request.setAttribute("products", ProductRepository.productList);
        request.getRequestDispatcher("product-list.jsp").forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("categories", CategoryRespository.categoryList);

        request.getRequestDispatcher("product-form.jsp").forward(request,response);
    }

    private void saveProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = productModel.inputProductId();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String status = request.getParameter("status");
        boolean productStatus = true;

        Category category = new Category();

        //find category
        try {
            for (int i = 0; i < CategoryRespository.categoryList.size(); i++){
                if (CategoryRespository.categoryList.get(i).getId() == categoryId){
                    category = CategoryRespository.categoryList.get(i);
                    break;
                }
            }
        }catch (NullPointerException e){
            System.out.println("NullPointerException");
        }

        if (status == null){
            productStatus = false;
        }

        Product product = new Product(id,name,price,stock,category,productStatus);
        productRepository.save(product);

        response.sendRedirect("product-controller");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existProduct = ProductRepository.productList.get(productRepository.findIndexById(id));
        request.setAttribute("product", existProduct);
        request.setAttribute("categories", CategoryRespository.categoryList);
        request.getRequestDispatcher("product-form.jsp").forward(request,response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String status = request.getParameter("status");

        Category category = new Category();

        //find category
        try {
            for (int i = 0; i < CategoryRespository.categoryList.size(); i++){
                if (CategoryRespository.categoryList.get(i).getId() == categoryId){
                    category = CategoryRespository.categoryList.get(i);
                    break;
                }
            }
        }catch (NullPointerException e){
            System.out.println("NullPointerException");
        }

        boolean cateStatus = true;
        if (status == null){
            cateStatus = false;
        }


        Product newCategory = new Product(id,name,price,stock,category,cateStatus);
        productRepository.updateProduct(id, newCategory);

        response.sendRedirect("product-controller");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productRepository.deleteById(id);
        response.sendRedirect("product-controller");
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product category = ProductRepository.productList.get(productRepository.findIndexById(id));
        request.setAttribute("product", category);
        request.getRequestDispatcher("product-details.jsp").forward(request, response);
    }

    private void searchProductsByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchQuery = request.getParameter("searchQuery");
        List<Product> searchResults = productRepository.searchProductsByName(searchQuery);
        if (searchResults.isEmpty()){
            message= "empty";
            request.setAttribute("errorEmpty",message);
        }else {
            message= "";
            request.setAttribute("errorEmpty",message);
        }
        System.out.println("hihi" + searchResults.size());
        request.setAttribute("products", searchResults);
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}