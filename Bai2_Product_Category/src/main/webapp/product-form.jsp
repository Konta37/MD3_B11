<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/26/2024
  Time: 8:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${product == null ? "Create new product" : "Update product"}</h1>
<form action="product-controller" method="post">
    <input type="hidden" name="action" value="${product == null ? "save" : "update"}">
    <c:if test="${product != null}">
        <input type="hidden" name="id" value="${product.id}">
    </c:if>
    <label for="name">Name: </label>
    <input type="text" name="name" id="name" value="${product!=null ? product.name : ''}" required>
    <label for="price">Price: </label>
    <input type="number" step="0.01" name="price" id="price" value="${product !=null ? product.price : ''}">
    <label for="stock">Stock: </label>
    <input type="number" step="0.01" name="stock" id="stock" value="${product != null ? product.stock : ''}">
    <label for="category">Category</label>
    <select name="categoryId" id="category">
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
    <input type="checkbox" name="status" id="status" ${product != null && product.status ? 'checked' : ''}>
    <button type="submit">Save</button>
</form>
</body>
</html>
