<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>x
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Catalog</title>
    <link rel="stylesheet" href="/css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <nav>
        <div class="container">
            <a href="/admin/" class="logo">Product Catalog</a>
            <div class="search-bar">
                <input type="text" id="searchInput" placeholder="Search products...">
                <button onclick="searchProducts()"><i class="fas fa-search"></i> Search</button>
            </div>
            <a href="/admin/add-product" class="btn"><i class="fas fa-plus"></i> Add Product</a>
            <a href="/admin/addWebsite" class="btn"><i class="fas fa-plus"></i> Add Website</a>
        </div>
    </nav>

    <main class="container">
        <table id="productTable">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id="productTableBody">
                <!-- Product rows will be dynamically inserted here -->
            </tbody>
        </table>

        <div id="pagination">
            <!-- Pagination controls will be dynamically inserted here -->
        </div>
    </main>

<script src="/js/homeScript.js"></script>
</body>
</html>