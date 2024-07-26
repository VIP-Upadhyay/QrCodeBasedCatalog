<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Product ID: ${product.name}</h1>
        <div class="product-details">
            <br><br>
            <div class="form-group">
                <div class="button-group">
                    <button onclick="openMedia('/file/${product.imagePath}', 'image')">Show Image</button>
                    <button onclick="openMedia('/file/${product.videoPath}', 'video')">Show Video</button>
                </div>
            </div>
            <div class="form-group">
                <button onclick="window.open('${product.url}', '_blank')" class="website-button">Visit Product Website</button>
            </div>
        </div>
    </div>

    <script>
        function openMedia(url, type) {
            if (type === 'image') {
                window.open(url, '_blank');
            } else if (type === 'video') {
                let videoWindow = window.open('', '_blank');
                videoWindow.document.write('<html><body style="margin:0;padding:0;"><video width="100%" height="100%" controls autoplay><source src="' + url + '" type="video/mp4">Your browser does not support the video tag.</video></body></html>');
                videoWindow.document.close();
            }
        }
    </script>
</body>
</html>