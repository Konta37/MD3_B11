<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/25/2024
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Chi Tiết Sản Phẩm</h1>
<p>ID: ${product.id}</p>
<p>Tên: ${product.name}</p>
<p>Giá: ${product.price}</p>
<p>Mô tả: ${product.description}</p>
<p>Nhà sản xuất: ${product.producer}</p>
<p>Tình trạng: ${product.status ? "Còn hàng" : "Hết hàng"}</p>
<a href="products">Quay lại danh sách</a>
</body>
</html>
