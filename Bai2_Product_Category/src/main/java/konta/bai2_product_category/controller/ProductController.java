package konta.bai2_product_category.controller;

import konta.bai2_product_category.repository.impl.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProductController", value = "/product-controller")
public class ProductController extends HttpServlet {
    private final ProductRepository productRepository = new ProductRepository();
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
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "details":
                break;
            case "search":
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
                break;
            case "edit":
                break;
            default:
                listProducts(request,response);
                break;
        }

    }
    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        productRepository.findAll(ProductRepository.productList);
        request.setAttribute("products", ProductRepository.productList);
        request.getRequestDispatcher("product-list.jsp").forward(request,response);
    }

    public void destroy() {
    }
}