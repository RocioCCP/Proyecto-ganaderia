<script>
$(document).ready(function () {
    // Inicializa la tabla DataTables
    $('#miTabla').DataTable({
        dom: 'lfrtipB', // Mueve el input de búsqueda al final de la tabla
        buttons: [
            {
                extend: 'excelHtml5',
                text: '<i class="fas fa-file-excel"></i> ',
                titleAttr: 'Exportar a Excel',
                className: 'btn btn-success'
            },
            {
                extend: 'print',
                text: '<i class="fa fa-print"></i> ',
                titleAttr: 'Imprimir',
                className: 'btn btn-info'
            },
            {
                extend: 'pdfHtml5',
                text: '<i class="fas fa-file-pdf"></i> ',
                titleAttr: 'Exportar a PDF',
                className: 'btn btn-danger',
                orientation: 'landscape', // Establece la orientación horizontal
                customize: function (doc) {
                    // Ajusta las márgenes
                    doc.pageMargins = [20, 5, 5, 5]; // [left, top, right, bottom]
                    doc.defaultStyle.fontSize = 10;
                    // Ajusta el tamaño de la fuente
                    doc.defaultStyle.fontSize = 8; // Por ejemplo, establece el tamaño de la fuente en 10 puntos
                    // Filtra las columnas que quieres imprimir
                    var filteredColumns = [0, 1, 2, 3, 4, 5, 6]; // Índices de las columnas que quieres imprimir
                    // Remueve las columnas no deseadas
                    doc.content[1].table.body.forEach(function (row) {
                        row.splice(0, row.length, ...row.filter((cell, index) => filteredColumns.includes(index)));
                    });
                }
            }
        ],
        lengthMenu: [10, 15, 20, 100],
        columnDefs: [
            {className: 'centered', targets: '_all'}, // Aplica la clase 'centered' a todas las columnas
            {orderable: false, targets: [3, 4, 5, 6]},
            {searchable: false, targets: [3, 4, 5, 6]}
        ],
        pageLength: 5,
        destroy: true,
        language: {
            processing: 'Procesando...',
            lengthMenu: 'Mostrar _MENU_ Registros por Página',
            zeroRecords: 'No se encontraron resultados',
            emptyTable: 'Ningún dato disponible en esta tabla',
            info: 'Mostrando _START_ a _END_ de _TOTAL_ entradas',
            infoEmpty: 'Mostrando 0 a 0 de 0 entradas',
            infoFiltered: '(filtrado de un total de _MAX_ Entradas)',
            search: 'Buscar:',
            paginate: {
                first: 'Primero',
                last: 'Último',
                next: 'Siguiente',
                previous: 'Anterior'
            },
            aria: {
                sortAscending: ': Activar para ordenar la columna ascendente',
                sortDescending: ': Activar para ordenar la columna descendente'
            }
        }
    });

    // Mueve el input de búsqueda a la posición deseada
    $('#miTabla_filter').appendTo('#paginationContainer');
});
</script>

<script>
        function mostrarClave() {
            var inputClave = document.getElementById('clave');
            if (inputClave.type === 'password') {
                inputClave.type = 'text';
            } else {
                inputClave.type = 'password';
            }
        }
    </script>
    
<style>
/* CSS personalizado para DataTables */
.dataTables_wrapper {
    font-family: 'Roboto', sans-serif;
    color: gray;
}

/* Estilos para el texto del encabezado cuando se activa la ordenación ascendente */
.dataTables_wrapper .sorting_asc:after {
    content: ' ↑'; /* Agrega una flecha hacia arriba al texto */
    color: green; /* Cambia el color del texto cuando se activa la ordenación ascendente */
}

/* Estilos para el texto del encabezado cuando se activa la ordenación descendente */
.dataTables_wrapper .sorting_desc:after {
    content: ' ↓'; /* Agrega una flecha hacia abajo al texto */
    color: red; /* Cambia el color del texto cuando se activa la ordenación descendente */
}
</style>




</body>
</html>

