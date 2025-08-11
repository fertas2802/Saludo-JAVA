
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('greeting-form');
    const resultDiv = document.getElementById('result');
        const guardarBtn = document.getElementById('guardar-btn');

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const nombre = document.getElementById('nombre').value;
        
        if (!nombre.trim()) {
            resultDiv.textContent = 'Por favor, ingrese su nombre.';
            return;
        }

        fetch('/hola', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'nombre=' + encodeURIComponent(nombre)
        })
        .then(response => response.text())
        .then(data => {
            resultDiv.textContent = data;
        })
        .catch(error => {
            resultDiv.textContent = 'Error al procesar la solicitud.';
            console.error('Error:', error);
        });
    });
    
        guardarBtn.addEventListener('click', function() {
            const nombre = document.getElementById('nombre').value;
            if (!nombre.trim()) {
                resultDiv.textContent = 'Por favor, ingrese su nombre.';
                return;
            }
            fetch('/guardar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: 'nombre=' + encodeURIComponent(nombre)
            })
            .then(response => response.text())
            .then(data => {
                resultDiv.textContent = data;
            })
            .catch(error => {
                resultDiv.textContent = 'Error al guardar en la base de datos.';
                console.error('Error:', error);
            });
        });
});
