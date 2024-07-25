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
<h1>${product == null ? "Tạo Sản Phẩm Mới" : "Cập Nhật Sản Phẩm"}</h1>
<form action="products" method="post">
    <input type="hidden" name="action" value="${product == null ? 'save' : 'update'}">
    <c:if test="${product != null}">
        <input type="hidden" name="id" value="${product.id}">
    </c:if>
    <label for="name">Tên:</label>
    <input type="text" name="name" id="name" value="${product != null ? product.name : ''}" required>
    <label for="price">Giá:</label>
    <input type="number" step="0.01" name="price" id="price" value="${product != null ? product.price : ''}" required>
    <label for="description">Mô tả:</label>
    <input type="text" name="description" id="description" value="${product != null ? product.description : ''}">
    <label for="producer">Nhà Sản Xuất:</label>
    <input type="text" name="producer" id="producer" value="${product != null ? product.producer : ''}">
    <label for="status">Tình Trạng:</label>
    <input type="checkbox" name="status" id="status" ${product != null && product.status ? 'checked' : ''}>
    <button type="submit">Lưu</button>
</form>
</body>
</html>
