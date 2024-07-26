<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Add New Product</h1>


    <form id="productForm" action="/admin/api/products/add" method="POST" enctype="multipart/form-data">
        <div class="form-group" >
            <input type="text" id="name" name="name" placeholder="Product Name" required>
            <c:if test="${not empty error}">
                        <div class="error-message">
                            ${error}
                        </div>
                    </c:if>
        </div>



        <div class="form-group">
            <button type="button" class="custom-file-input" data-for="image">Choose Image</button>
            <input type="file" id="image" name="image" accept="image/*" required>
            <div id="imagePreview" class="preview"></div>
        </div>

        <div class="form-group">
            <button type="button" class="custom-file-input" data-for="video">Choose Video</button>
            <input type="file" id="video" name="video" accept="video/*">
            <div id="videoPreview" class="preview"></div>
        </div>

        <button type="submit">Add Product</button>
    </form>
</div>

<script src="/js/script.js"></script>
</body>
</html>