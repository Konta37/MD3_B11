package konta.bai2_product_category.controller;

import konta.bai2_product_category.model.Category;
import konta.bai2_product_category.repository.impl.CategoryRespository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/category-controller")
public class CategoryController extends HttpServlet {
    private final CategoryRespository categoryRespository = new CategoryRespository();

    private Category categoryModel = new Category();
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
                deleteCategory(request,response);
                break;
            case "details":
                showDetail(request,response);
                break;
            case "search":
                searchCategories(request,response);
                break;
            default:
                listCategories(request,response);
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
                saveCategory(request,response);
                break;
            case "update":
                updateCategory(request,response);
                break;
            default:
                listCategories(request,response);
                break;
        }

    }
    private void listCategories(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("categories", CategoryRespository.categoryList);
        request.getRequestDispatcher("category-list.jsp").forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("category-form.jsp").forward(request,response);
    }

    private void saveCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = categoryModel.inputCategoryId();
        String name = request.getParameter("name");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Category newCategory = new Category(id, name, status);
        categoryRespository.save(newCategory);

        response.sendRedirect("category-controller");
    }
    //show category information that u want to edit
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category existCate = CategoryRespository.categoryList.get(categoryRespository.findIndexById(id));
        request.setAttribute("category", existCate);
        request.getRequestDispatcher("category-form.jsp").forward(request,response);
    }
    //now update it
    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String status = request.getParameter("status");

        boolean cateStatus = true;
        if (status == null){
            cateStatus = false;
        }
        System.out.println("1" + request.getParameter("status"));

        Category newCategory = new Category(id, name, cateStatus);
        categoryRespository.updateCategory(id, newCategory);

        response.sendRedirect("category-controller");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryRespository.deleteById(id);
        response.sendRedirect("category-controller");
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = CategoryRespository.categoryList.get(categoryRespository.findIndexById(id));
        request.setAttribute("category", category);
        request.getRequestDispatcher("category-details.jsp").forward(request, response);
    }

    private void searchCategories(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchQuery = request.getParameter("searchQuery");
        List<Category> searchResults = categoryRespository.searchCategoriesByName(searchQuery);
        if (searchResults.isEmpty()){
            message= "empty";
            request.setAttribute("errorEmpty",message);
        }else {
            message= "";
            request.setAttribute("errorEmpty",message);
        }
        System.out.println("hihi" + searchResults.size());
        request.setAttribute("categories", searchResults);
        request.getRequestDispatcher("category-list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}