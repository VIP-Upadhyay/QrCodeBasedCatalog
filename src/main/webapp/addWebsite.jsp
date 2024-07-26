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
    <h1>Website</h1>

    <form id="productForm" action="/admin/api/products/addWebsite" method="POST" enctype="multipart/form-data">

        <div class="form-group">
            <input type="text" id="url" name="url" placeholder="Add Website URL" value="${website.url}">
        </div>


        <button type="submit">Update Website</button>
    </form>
</div>

</body>
</html>