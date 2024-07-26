let currentPage = 0;
        const pageSize = 10;

        function loadProducts(page = 0, query = '') {
            console.log(page)
            let url = query
                ? `/admin/api/products/search/${query}?page=${page}&size=${pageSize}`
                : `/admin/api/products?page=${page}&size=${pageSize}`;

            fetch(url)
                .then(response => response.json())
                .then(data => {
                    updateTable(data.content);
                    updatePagination(data);
                })
                .catch(error => console.error('Error:', error));
        }

        function updateTable(products) {
            let tbody = document.getElementById('productTableBody');
            tbody.innerHTML = '';
            products.forEach(product => {
                let row = `
                    <tr>
                        <td>${product.name}</td>
                        <td>
                            <a href="/admin/edit/${product.name}" class="btn btn-small"><i class="fas fa-edit"></i> Edit</a>
                            <button onclick="deleteProduct('${product.name}')" class="btn btn-small btn-danger"><i class="fas fa-trash"></i> Delete</button>
                            <a href="/admin/qrcode/${product.name}" class="btn btn-small" download><i class="fas fa-qrcode"></i> Download QR</a>
                            <button onclick="shareOnWhatsApp('${product.name}')" class="btn btn-small btn-whatsapp"><i class="fab fa-whatsapp"></i> Share</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += row;
            });
        }

        function updatePagination(data) {
            let pagination = document.getElementById('pagination');
            pagination.innerHTML = '';

            if (data.totalPages > 1) {
                for (let i = 0; i < data.totalPages; i++) {
                    let button = document.createElement('button');
                    button.innerText = i + 1;
                    button.onclick = () => loadProducts(i);
                    if (i === data.number) {
                        button.disabled = true;
                    }
                    pagination.appendChild(button);
                }
            }
        }

        function searchProducts() {
            let query = document.getElementById('searchInput').value;
            loadProducts(0, query);
        }

        function deleteProduct(name) {
            if (confirm('Are you sure you want to delete this product?')) {
                fetch(`/admin/api/products/delete/${name}`, { method: 'GET' })
                    .then(() => loadProducts(currentPage))
                    .catch(error => console.error('Error:', error));
            }
        }

        function shareOnWhatsApp(name) {
            let productUrl = `${window.location.origin}/getProduct/${name}`;
            let whatsappUrl = `https://wa.me/?text=${encodeURIComponent(productUrl)}`;
            window.open(whatsappUrl, '_blank');
        }

        // Initial load
        document.addEventListener('DOMContentLoaded', function() {
            loadProducts();

            // Add event listener for search on Enter key press
            document.getElementById('searchInput').addEventListener('keypress', function(e) {
                if (e.key === 'Enter') {
                    searchProducts();
                }
            });
        });