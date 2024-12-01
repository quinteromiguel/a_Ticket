// URL base del backend
const BASE_URL = "http://localhost:8080/api/event";

// Función para crear un evento
document.getElementById('formCrearEventoFormulario').addEventListener('submit', function (e) {
    e.preventDefault();

    const eventData = {
        title: document.getElementById('nombreEvento').value,
        description: document.getElementById('descripcionEvento').value,
        date: document.getElementById('fechaEvento').value,
        vipPrice: 2000, // O lo que sea necesario
        regularPrice: 1000 // Lo que sea necesario
    };

    // Validar que los campos estén completos
    if (!eventData.title || !eventData.description || !eventData.date) {
        alert('Por favor, completa todos los campos obligatorios.');
        return;
    }

    // Enviar solicitud al backend
    fetch(BASE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(eventData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error al crear el evento: ${response.status} ${response.statusText}`);
            }
            console.log('Evento creado exitosamente.');
            alert('Evento creado correctamente.');  // Mensaje de éxito
            mostrarEventos(); // Actualizar la lista de eventos después de crear uno nuevo
            document.getElementById('formCrearEventoFormulario').reset(); // Limpiar el formulario
            toggleFormulario('crearEvento'); // Ocultar formulario
        })
        .catch(error => {
            console.error('Error al crear el evento:', error);
            alert('Hubo un error al crear el evento. Inténtalo nuevamente.');
        });
});

// Función para alternar la visibilidad del formulario
function toggleFormulario(formularioId) {
    const formularios = ['crearEvento', 'editarEvento', 'eliminarEvento']; // Agregar más formularios si es necesario
    formularios.forEach(id => {
        document.getElementById('form' + id.charAt(0).toUpperCase() + id.slice(1)).style.display = 'none';
    });

    const formulario = document.getElementById('form' + formularioId.charAt(0).toUpperCase() + formularioId.slice(1));
    formulario.style.display = formulario.style.display === 'block' ? 'none' : 'block';
}

// Función para mostrar eventos
function mostrarEventos() {
    fetch(BASE_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener los eventos");
            }
            return response.json();
        })
        .then(data => {
            const eventosLista = document.getElementById("eventosLista");
            eventosLista.innerHTML = ""; // Limpiar lista previa

            if (data.length === 0) {
                eventosLista.innerHTML = "<p>No hay eventos disponibles.</p>";
                return;
            }

            data.forEach(evento => {
                const eventoItem = document.createElement("div");
                eventoItem.classList.add("list-group-item");

                eventoItem.innerHTML = `
                    <h4>${evento.title}</h4>
                    <p>${evento.description}</p>
                    <p><strong>Fecha:</strong> ${evento.date}</p>
                    <p><strong>Precios:</strong> VIP $${evento.vipPrice}, Regular $${evento.regularPrice}</p>
                `;

                eventosLista.appendChild(eventoItem);
            });
        })
        .catch(error => {
            console.error("Error al mostrar los eventos:", error);
            alert("Hubo un problema al cargar los eventos");
        });
}

// Función para buscar un evento por ID
function buscarEvento() {
    const idEvento = document.getElementById("idEventoBuscar").value;

    if (!idEvento) {
        alert("Por favor, ingresa un ID válido.");
        return;
    }

    fetch(`${BASE_URL}/${idEvento}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Evento no encontrado");
            }
            return response.json();
        })
        .then(data => {
            const resultados = document.getElementById("eventosLista");
            resultados.innerHTML = `
                <div class="list-group-item">
                    <h4>${data.title}</h4>
                    <p>${data.description}</p>
                    <p><strong>Fecha:</strong> ${data.date}</p>
                </div>
            `;
        })
        .catch(error => {
            console.error("Error al buscar el evento:", error);
            alert("Hubo un problema al buscar el evento. Asegúrate de que el ID sea correcto.");
        });
}

// Escuchar el evento submit del formulario de búsqueda
document.getElementById("formBuscarEventoFormulario").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario
    buscarEvento(); // Llamar a la función buscarEvento
});

// Función para editar un evento
function editarEvento() {
    const idEvento = document.getElementById("idEventoEditar").value; // Obtener el ID del evento
    const nombreEvento = document.getElementById("nombreEventoEditar").value; // Obtener el nombre del evento
    const fechaEvento = document.getElementById("fechaEventoEditar").value; // Obtener la fecha del evento
    const descripcionEvento = document.getElementById("descripcionEventoEditar").value; // Obtener la descripción del evento

    // Verificar que todos los campos estén completos
    if (!idEvento || !nombreEvento || !fechaEvento || !descripcionEvento) {
        alert("Por favor, completa todos los campos.");
        return;
    }

    const BASE_URL = "http://localhost:8080/api/event"; // Cambia si el backend tiene otra URL

    // Enviar la solicitud PUT al backend con los nuevos datos del evento
    fetch(`${BASE_URL}?id=${idEvento}&name=${nombreEvento}&date=${fechaEvento}&description=${descripcionEvento}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("No se pudo actualizar el evento");
            }
            return response.json();
        })
        .then(data => {
            // Mostrar mensaje de éxito o hacer algo con la respuesta
            alert("Evento actualizado correctamente");
            console.log(data);
        })
        .catch(error => {
            console.error("Error al actualizar el evento:", error);
            alert("Hubo un problema al actualizar el evento. Asegúrate de que los datos sean correctos.");
        });
}

// Escuchar el evento submit del formulario de editar evento
document.getElementById("formEditarEventoFormulario").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario (recarga de página)
    editarEvento(); // Llamar a la función editarEvento
});


// Función para eliminar un evento
function eliminarEvento() {
    const idEvento = document.getElementById("idEventoEliminar").value; // Obtener el ID del evento

    // Verificar que el ID no esté vacío
    if (!idEvento) {
        alert("Por favor, ingresa un ID válido.");
        return;
    }

    const BASE_URL = "http://localhost:8080/api/event"; // Cambia si el backend tiene otra URL

    // Enviar la solicitud DELETE al backend para eliminar el evento con el ID especificado
    fetch(`${BASE_URL}?id=${idEvento}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("No se pudo eliminar el evento. Asegúrate de que el ID sea correcto.");
            }
            // Si la respuesta es exitosa, mostramos un mensaje y limpiamos el campo
            alert("Evento eliminado correctamente");
            document.getElementById("idEventoEliminar").value = ''; // Limpiar el campo de ID
        })
        .catch(error => {
            console.error("Error al eliminar el evento:", error);
            alert("Hubo un problema al eliminar el evento. Asegúrate de que el ID sea correcto.");
        });
}

// Escuchar el evento submit del formulario de eliminar evento
document.getElementById("formEliminarEventoFormulario").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario (recarga de página)
    eliminarEvento(); // Llamar a la función eliminarEvento
});


// Función para obtener el reporte de tickets vendidos
document.getElementById('formObtenerReporteTicket').addEventListener('submit', function (e) {
    e.preventDefault();

    const idEvento = document.getElementById('idEventoReporte').value;

    // Validar que el ID del evento no esté vacío
    if (!idEvento) {
        alert('Por favor, ingresa un ID válido.');
        return;
    }

    // Solicitud al backend para obtener el reporte de tickets
    fetch(`http://localhost:8080/api/event/reporte?id=${idEvento}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener el reporte");
            }
            return response.json();
        })
        .then(data => {
            const reporteResultado = document.getElementById('reporteResultado');

            // Mostrar el reporte en la interfaz
            reporteResultado.innerHTML = `
                <h4>Reporte de Tickets para el Evento: ${data.title}</h4>
                <p><strong>Fecha:</strong> ${data.date}</p>
                <p><strong>Descripción:</strong> ${data.description}</p>
                <p><strong>Precio VIP:</strong> $${data.vipPrice}</p>
                <p><strong>Precio General:</strong> $${data.generalPrice}</p>
                <p><strong>Tickets VIP vendidos:</strong> ${data.vipSold}</p>
                <p><strong>Tickets Generales vendidos:</strong> ${data.generalSold}</p>
                <p><strong>Total VIP:</strong> $${data.vipTotal}</p>
                <p><strong>Total General:</strong> $${data.generalTotal}</p>
                <p><strong>Total generado:</strong> $${data.total}</p>
            `;
        })
        .catch(error => {
            console.error('Error al obtener el reporte:', error);
            alert('Hubo un error al obtener el reporte. Inténtalo nuevamente.');
        });
});




