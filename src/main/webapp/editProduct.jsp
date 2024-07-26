<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Edit Product</h1>

    <form id="productForm" action="/admin/api/products/edit/${product.name}" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" id="newName" name="newName" placeholder="New Product Name" value="${product.name}">
        </div>



        <div class="form-group">
            <button type="button" class="custom-file-input" data-for="newImage">Choose New Image</button>
            <input type="file" id="newImage" name="newImage" accept="image/*">
            <div id="imagePreview" class="preview">
                <img src="/file/${product.imagePath}" alt="Current Product Image">
            </div>
        </div>

        <div class="form-group">
            <button type="button" class="custom-file-input" data-for="newVideo">Choose New Video</button>
            <input type="file" id="newVideo" name="newVideo" accept="video/*">
            <div id="videoPreview" class="preview">
                <video src="/file/${product.videoPath}" controls>Your browser does not support the video tag.</video>
            </div>
        </div>

        <button type="submit">Update Product</button>
    </form>
</div>

<script src="/js/editScript.js"></script>
</body>
</html>