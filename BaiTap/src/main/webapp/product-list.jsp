<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh Sách Sản Phẩm</h1>
<a href="products?action=create">Tạo sản phẩm mới</a>
<form action="products" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="searchQuery" placeholder="Tìm kiếm sản phẩm">
    <button type="submit">Tìm</button>
</form>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Nhà sản xuất</th>
        <th>Tình trạng</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>${product.producer}</td>
            <td>${product.status ? "Còn hàng" : "Hết hàng"}</td>
            <td>
                <a href="products?action=edit&id=${product.id}">Cập nhật</a>
                <a href="products?action=delete&id=${product.id}">Xóa</a>
                <a href="products?action=details&id=${product.id}">Xem chi tiết</a>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="hidden" value="${errorEmpty}" id="errorMessage">
<a href="index.jsp">Back</a>
</body>
<script>
    let check = document.getElementById("errorMessage");
    if (check.value == "empty"){
        alert("ko tim thay");
    }
</script>
</html>
