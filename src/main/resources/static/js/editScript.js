document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('productForm');
    const fileInputs = document.querySelectorAll('input[type="file"]');

    fileInputs.forEach(input => {
        const button = document.querySelector(`[data-for="${input.id}"]`);
        const preview = document.getElementById(`${input.id.replace('new', '').toLowerCase()}Preview`);

        button.addEventListener('click', () => input.click());

        input.addEventListener('change', function() {
            if (this.files && this.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    preview.innerHTML = '';
                    if (input.accept.includes('image')) {
                        const img = document.createElement('img');
                        img.src = e.target.result;
                        preview.appendChild(img);
                    } else if (input.accept.includes('video')) {
                        const video = document.createElement('video');
                        video.src = e.target.result;
                        video.controls = true;
                        preview.appendChild(video);
                    }
                }
                reader.readAsDataURL(this.files[0]);
            }
        });
    });

    form.addEventListener('submit', function(e) {
        const name = document.getElementById('name') || document.getElementById('newName');
        const url = document.getElementById('url') || document.getElementById('newUrl');

        if (!name.value.trim()) {
            e.preventDefault();
            alert('Please enter a product name');
        } else if (!url.value.trim()) {
            e.preventDefault();
            alert('Please enter a product URL');
        }
    });
});