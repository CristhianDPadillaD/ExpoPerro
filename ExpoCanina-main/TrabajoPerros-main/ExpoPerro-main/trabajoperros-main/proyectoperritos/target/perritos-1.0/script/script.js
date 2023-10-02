/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const myModal = document.getElementById('myModal')
const myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', () => {
  myInput.focus()
  
  
  
})


// Escucha el evento de clic en el botón de confirmación de eliminación
document.getElementById('confirmarEliminacion').addEventListener('click', function () {
    var nombre = document.getElementById('eliminarModal').getAttribute('data-nombre'); // Obtiene el nombre del perro desde el modal

    // Realiza una solicitud AJAX al servlet para eliminar el perro por su nombre
    // Asegúrate de ajustar la URL y el nombre del parámetro según tu configuración
    var url = 'EliminarPerroServlet?perroName=' + nombre; // Cambia por la URL correcta
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            // La eliminación fue exitosa, realiza acciones adicionales si es necesario
            // Por ejemplo, redirige a la página principal
            window.location.href = 'index.jsp';
        } else {
            // Maneja errores aquí si es necesario
            console.log('Error al eliminar el perro.');
        }
    };

    xhr.send();
});





