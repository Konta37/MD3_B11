<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/26/2024
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Category List</h1>
<a href="category-controller?action=create">Tạo sản phẩm mới</a>
<form action="category-controller" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="searchQuery" placeholder="Tìm kiếm sản phẩm">
    <button type="submit">Tìm</button>
</form>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.status ? "active" : "inactive"}</td>
            <td>
                <a href="category-controller?action=edit&id=${category.id}">Update</a>
                <a href="category-controller?action=delete&id=${category.id}">Delete</a>
                <a href="category-controller?action=details&id=${category.id}">Show Details</a>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="hidden" value="${errorEmpty}" id="errorMessage">
<a href="index.jsp">Back</a>
</body>
</html>
