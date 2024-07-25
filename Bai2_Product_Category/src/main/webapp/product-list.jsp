<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/26/2024
  Time: 12:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Product List</h1>
<a href="product-controller?action=create">Tạo sản phẩm mới</a>
<form action="product-controller" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="searchQuery" placeholder="Tìm kiếm sản phẩm">
    <button type="submit">Tìm</button>
</form>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Category</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.stock}</td>
            <td>${product.category.getName()}</td>
            <td>${product.status ? "active" : "inactive"}</td>
            <td>
                <a href="product-controller?action=edit&id=${product.id}">Update</a>
                <a href="product-controller?action=delete&id=${product.id}">Delete</a>
                <a href="product-controller?action=details&id=${product.id}">Show Details</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%--<input type="hidden" value="${errorEmpty}" id="errorMessage">--%>
<a href="index.jsp">Back</a>
</body>
</html>
