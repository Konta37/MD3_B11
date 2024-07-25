package konta.baitap.servlet;

import konta.baitap.entity.Product;
import konta.baitap.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "productServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private String message;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "details":
                showDetails(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "save":
                saveProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }

    }
    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        productService.findAll(ProductService.productList);
        request.setAttribute("products", ProductService.productList);
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("product-form.jsp").forward(request, response);
    }

    private void saveProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int id = Product.inputProductId();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String producer = request.getParameter("producer");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Product newProduct = new Product(id, name, price, description, producer, status);
        productService.save(newProduct);

        response.sendRedirect("products");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = ProductService.productList.get(productService.findIndexById(id));
        request.setAttribute("product", existingProduct);
        request.getRequestDispatcher("product-form.jsp").forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String producer = request.getParameter("producer");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Product updatedProduct = new Product(id, name, price, description, producer, status);
        productService.updateProduct(id, updatedProduct);

        response.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteById(id);
        response.sendRedirect("products");
    }

    private void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = ProductService.productList.get(ProductService.findIndexById2(id));
        request.setAttribute("product", product);
        request.getRequestDispatcher("product-details.jsp").forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        List<Product> searchResults = ProductService.searchProductsByName(searchQuery);
        if (searchResults.isEmpty()){
            message= "empty";
            request.setAttribute("errorEmpty",message);
        }else {
            message= "";
            request.setAttribute("errorEmpty",message);
        }
        request.setAttribute("products", searchResults);
        request.getRequestDispatcher("product-list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}