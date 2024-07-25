<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/26/2024
  Time: 12:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${category==null ? "Create new category" : "Update Category"}</h1>
<form action="category-controller" method="post">
    <input type="hidden" name="action" value="${category == null ? 'save' : 'update'}">
    <c:if test="${category != null}">
        <input type="hidden" name="id" value="${category.id}">
    </c:if>
    <label for="name">Name: </label>
    <input type="text" name="name" id="name" value="${category != null ? category.name : ''}" required>
    <label for="status">Status</label>
    <input type="checkbox" name="status" id="status" ${category != null && category.status ? 'checked' : ''}>
    <button type="submit">Save</button>
</form>
</body>
</html>
