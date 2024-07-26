<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/26/2024
  Time: 1:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Information</h1>
<p>ID: ${category.id}</p>
<p>Name: ${category.name}</p>
<p>Status: ${category.status ? "active" : "inactive"}</p>
<a href="category-controller">Back to CategoryList</a>
</body>
</html>
