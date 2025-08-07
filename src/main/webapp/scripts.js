window.onload = function () {
    const nombre = prompt("Por favor, ingresá tu nombre:");
    if (nombre) {
        fetch('/hola', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'nombre=' + encodeURIComponent(nombre)
        })
        .then(response => response.text())
        .then(data => alert(data));
    }
};
