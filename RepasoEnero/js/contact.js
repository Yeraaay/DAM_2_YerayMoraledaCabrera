"use strict";

document.addEventListener('DOMContentLoaded', function () {
    const cartaText = document.querySelector('.card p');
    const contacto = document.getElementById('contacto');

    let isCartaAbierta = false;

    cartaText.addEventListener('click', () => {
        if (!isCartaAbierta) {
            abrirCarta();
        } else {
            cerrarCarta();
        }
    });

    function abrirCarta() {
        const textoConfirmacion = prompt("Escribe 'abrir' para abrir la carta:");
        if (textoConfirmacion && textoConfirmacion.toLowerCase() === 'abrir') {
            isCartaAbierta = true;
            mostrarFormulario();
        }
    }

    function mostrarFormulario() {
        contacto.style.display = 'block';
        cartaText.textContent = 'Cerrar Carta';
    }

    function cerrarCarta() {
        const textoConfirmacion = prompt("Escribe 'cerrar' para cerrar la carta:");
        if (textoConfirmacion && textoConfirmacion.toLowerCase() === 'cerrar') {
            isCartaAbierta = false;
            contacto.style.display = 'none';
            cartaText.textContent = 'Abrir Carta';
        }
    }
});