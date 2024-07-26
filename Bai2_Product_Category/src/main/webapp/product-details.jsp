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
<h1>Information</h1>
<p>ID: ${product.id}</p>
<p>Name: ${product.name}</p>
<p>Price: ${product.price}</p>
<p>Stock: ${product.stock}</p>
<p>Category Name: ${product.category.getName()}</p>
<p>Status: ${product.status ? "active" : "inactive"}</p>
<a href="product-controller">Back to Product List</a>
</body>
</html>
