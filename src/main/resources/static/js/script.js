function handleFileSelect(fileInput, previewElement, isVideo = false) {
            fileInput.addEventListener('change', function() {
                const file = this.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function(e) {
                        previewElement.innerHTML = '';
                        if (isVideo) {
                            const video = document.createElement('video');
                            video.src = e.target.result;
                            video.controls = true;
                            previewElement.appendChild(video);
                        } else {
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            previewElement.appendChild(img);
                        }
                    }
                    reader.readAsDataURL(file);
                }
            });
        }

        handleFileSelect(document.getElementById('image'), document.getElementById('imagePreview'));
        handleFileSelect(document.getElementById('video'), document.getElementById('videoPreview'), true);

        // Open file dialog when custom button is clicked
        document.querySelectorAll('.custom-file-input').forEach(button => {
            button.addEventListener('click', function() {
                const inputId = this.getAttribute('data-for');
                document.getElementById(inputId).click();
            });
        });

        // Update custom button text
        document.querySelectorAll('input[type="file"]').forEach(input => {
            input.addEventListener('change', function() {
                const button = document.querySelector(`.custom-file-input[data-for="${this.id}"]`);
                if (this.files.length > 0) {
                    button.textContent = this.files[0].name;
                } else {
                    button.textContent = this.accept.includes('image') ? 'Choose Image' : 'Choose Video';
                }
            });
        });