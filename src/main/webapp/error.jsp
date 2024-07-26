<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Error</h1>
    <div class="error-message">
        <c:if test="${not empty error}">
            ${error}
        </c:if>
        <c:if test="${empty error}">
            An unexpected error occurred. Please try again later.
        </c:if>
    </div>
    <a href="/admin/" class="button">Back to Home</a>
</div>
</body>
</html>