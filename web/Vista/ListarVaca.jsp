<%@page import="Modelo.Vaca"%>
<%@page import="Modelo.Usuario"%>
<%@page import="DaoPersistencia.DaoUsuario"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Lista Vacas</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

          <!-- Link to Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">

         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

        <link href="./EstilosCSS/estiloscss.css" rel="stylesheet" type="text/css"/>

        <link href="./EstilosCSS/EstiloTablas2.css" rel="stylesheet" type="text/css"/>
         <style>
            .error {
                color: red;
                font-size: 10px; /* Tamaño de fuente opcional */     }
             </style>
        
    </head>
    <body>

        <!--Barra de Navegacion -->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark border-3 fixed-top border-bottom" id="menu">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"></a>

                <img src="./Imagenes/softrcc.jpg" alt="Logo" style="float: left; width: 100px;" />
                <button 
                    class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse  " id="navbarSupportedContent">

                    <ul class="navbar-nav ml-3 me-auto">  
                        <form class="navbar-nav ms-auto ml-auto" role="search">
                            <input class="form-control " type="search" name="txtbuscar" placeholder="Buscar Vaca" aria-label="Buscar">
                            <button  type="submit" name="accion" value="buscar" class="btn btn-primary ml-1">Buscar</button>
                        </form>

                    </ul>


                    <ul class="navbar-nav mb-3 mb-lg-0 float-start ">   

                        <li class="nav-item ">
                            <a class="nav-link active my-menu-item" aria-current="page" href="./index.jsp"><b>
                                    <span  style="margin-left: 10px; border:none" class="btn btn-outline-light">
                                        <i class="bi bi-arrow-left-square-fill text-dark"></i> <b> Inicio </b>
                                    </span>
                            </a>
                        </li>
                       </li>
                  </div>
            </div>
        </nav>
        <!--Barra de Navegacion -->

        <div class="row ">
            <hr>
            <div class="card col-sm-4 mb-2  mt-4 sticky-top">

            <h4 class="text-secondary elegant-font mt-4"><b>Formulario Registro Vacas</b></h4> 

               <form action="ControladorVaca" method="POST" autocomplete="off" class="custom-form">

                    <div class="row">
                        <div class="col-md-10 ">

                        <div class="form-group text-left">
                            <label for="placas" class="text-left"># Placa</label>
                            <input type="text" class="form-control" value="${User.getNumPlaca()}"
                                   id="placa" name="txtPlaca" placeholder="Ingrese Placa"   required
                                     title="La placa debe tener entre 1 y 6 caracteres, alfanuméricos o espacios">
                                  <small id="placarror" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                    </small>
                         </div>
                        <div class="form-group text-left">
                            <label for="nomva" class="text-left"> Nombre</label>
                            <input type="text" class="form-control" value="${User.getNombreVaca()}"
                                   id="nomvac" name="txtNombre" placeholder="Ingrese Nombre"required
                                     pattern="[A-Za-z0-9\s]*"
                                       title="Escribe un nombre puede contener letras y/o números">
                                <small id="nombreError" class="error form-text text-muted">
                                     Campo es obligatorio <span class="error">*</span>
                                    </small>
                                </small>
                            </div>
                                                          
                                <div class="form-group text-left">
                                    <label for="fecha">Fecha Nac.</label>
                                    <input type="date" class="form-control" id="fecha" 
                                           value="${User.getFechanacimiento()}" name="txtFecha" required
                                           title="Seleccionar una fecha">
                                    <small id="fechaError" class="error form-text text-muted">
                                   Campo es obligatorio <span class="error">*</span>
                                    </small>
                                        
                                </div>
                                <div class="form-group text-left">
                                    <label for="madre">Madre</label>
                                    <input type="text" class="form-control" id="madre" value="${User.getMadre()}"
                                           name="txtMadre" placeholder="Ingrese Madre" required
                                             pattern="[A-Za-z0-9\s]*"
                                            title="Escribe un nombre puede contener letras y/o números">
                                            <small id="madreError" class="error form-text text-muted">
                                              Campo es obligatorio <span class="error">*</span>
                                    </small>
                                </div>

                                    <div class="form-group text-left">
                                        <label for="padre">Padre</label>
                                        <input type="text" class="form-control" id="padre" value="${User.getPadre()}" 
                                               name="txtPadre" placeholder="Ingrese Padre" required
                                               pattern="[A-Za-z0-9\s]*"
                                                 title="Escribe un nombre puede contener letras y/o números">
                                                <small id="padreError" class="error form-text text-muted">
                                                  Campo es obligatorio <span class="error">*</span>
                                    </small>
                                    </div>

                                <div class="form-group text-left">
                                    <label for="usu">Usuario</label>
                                    <select class="form-control" id="usu" name="txtIdUsu" required>
                                        <option value="">Seleccione Usuario</option>
                                        <% List<Usuario> usuarios = DaoUsuario.listar();
                                            if (usuarios != null) {
                                                for (Usuario usuario : usuarios) {
                                                    if (usuario != null) {%>
                                        <option value="<%=usuario.getIdUsuario()%>"><%=usuario.getNombres()%></option>
                                        <%            }
                                                }
                                            } %>
                                    </select>
                                    <small id="usuarioError" class="error form-text text-muted">
                                        Campo es obligatorio <span class="error">*</span>
                                    </small>
                           </div>
                    </div>
                </div>
   
                            <div class="form-group mt-3 text-center" style="border: none;">

                                <button type="submit" name="accion" value="registrar" class="btn btn-warning">
                                    <i class="fas fa-save"></i> Agregar
                                </button>
                                <button type="submit" name="accion" value="actualizar" class="btn btn-success">
                                    <i class="bi bi-arrow-repeat"></i> Actualizar</button>
                                <button type="submit" name="accion" value="listar" class="btn btn-secondary">
                                    <i class="bi bi-x-lg"></i> Cancelar</button>
                            </div>    
            </form>
            </div>   

            <div class="col-sm-7 mb-4 mt-5 sticky-top">
                <h4 class="text-secondary elegant-font mt-4"><b>Lista Vacas</b></h4>

                <div class=" table-container ml-3 md-3 table-responsive" >
                    <table  id="miTabla" class="table table-striped table-hover sticky-top">
                        <thead >
                            <tr>
                                <th>ID</th>
                                <th>Placa</th>                       
                                <th>Nombre</th>                       
                                <th>fecha Nacimiento</th>                       
                                <th>Madre</th>
                                <th>Padre</th>
                                <th>Usuario</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                          <%
                                 List<Vaca> Lista = (List<Vaca>) request.getAttribute("listaVaca");
                                for (Vaca vaca :Lista) {%>
                            <tr>
                                <td><%= vaca.getIdVaca()%></td>
                                <td><%= vaca.getNumPlaca()%></td>                           
                                <td><%= vaca.getNombreVaca()%></td>
                                <td><%= vaca.getFechanacimiento()%></td>
                                <td><%= vaca.getMadre()%></td>
                                <td><%= vaca.getPadre()%></td>
                                <td><%= DaoUsuario.obtenerunNombreUsuario(vaca.getUsuarioId())%></td>

                                <td>
                                    <div class="btn-group" role="group" aria-label="Acciones">

                                        <div class="mt-2">                                   
                                            <a href="ControladorVaca?accion=editar&id=<%= vaca.getIdVaca()%>"class="btn btn-outline-light btn-success"> 
                                                <!-- id  de un usuario se lo envio al controlador  en variable id -->  
                                                <i class="fas fa-pencil-alt"></i> <!-- Ícono de lápiz -->  
                                            </a>
                                            <a href="ControladorVaca?accion=eliminar&id=<%= vaca.getIdVaca()%>"
                                               class="btn btn-secondary" onclick="return confirm('¿Estás seguro de que deseas eliminar este registro?')">
                                                <i class="fas fa-trash"></i> <!-- Ícono de papelera -->
                                            </a>          

                                    </div>
                                </td>
                            <% }%>
                        </tbody>
                    </table>
                        
                        
                </div>
                <div class="form-group mt-4 text-right">
                    <a href="ControladorVaca?accion=listar" class="btn btn-primary mr-2"style="background-color: #0C4F5A; border-color:#0C4F5A;">
                        <i class="fas fa-list"></i> Listar</a>
                    <a href="./index2.jsp"class="btn btn-warning"style="background-color: #90DEEB; border-color:#90DEEB;">
                        <i class="bi bi-arrow-left-square-fill text-dark"></i> Inicio</a>
                </div>
            </div>
        </div>   

    </div>

    <!-- Jquery-3.5.1 sirve para ejecutar funsion dezplazamiento dentro de la tabla -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"
    ></script>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- DataTables JS -->
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
    
     <!-- DataTable -->
            <script   type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>

            <script    type="text/javascript"   src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
            <script    type="text/javascript"   src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
            <script    type="text/javascript"   src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
            <script    type="text/javascript"   src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>
            <script    type="text/javascript"   src="https://cdn.datatables.net/buttons/2.3.3/js/dataTables.buttons.min.js"></script>
            <script    type="text/javascript"   src="https://cdn.datatables.net/buttons/2.3.3/js/buttons.bootstrap5.min.js"></script>
            <script    type="text/javascript"   src="https://cdn.datatables.net/buttons/2.3.3/js/buttons.html5.min.js"></script>
            <script    type="text/javascript"   src="https://cdn.datatables.net/buttons/2.3.3/js/buttons.print.min.js"></script>

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
           lengthMenu: '<span style="color: gray;">Mostrar _MENU_ Registros por Página</span>',
            zeroRecords: 'No se encontraron resultados',
            emptyTable: 'Ningún dato disponible en esta tabla',
            info: 'Mostrando _START_ a _END_ de _TOTAL_ entradas',
            infoEmpty: 'Mostrando 0 a 0 de 0 entradas',
            infoFiltered: '(filtrado de un total de _MAX_ Entradas)',
            search: '<span style="color: gray;">Buscar:</span>',
            paginate: {
                first: 'Primero',
                last: 'Último',
                next: 'Siguiente',
                previous: 'Anterior'
            },
          aria: {
            sortAscending: '<span style="color: green;">: Activar para ordenar la columna ascendente</span>',
            sortDescending: '<span style="color: red;">: Activar para ordenar la columna descendente</span>'
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
            color:gray;

        }
        .dataTables_length,
        .dataTables_filter {
            display: inline-block;
            vertical-align: middle;
            margin-right: 30px; /* Agrega un margen derecho para separarlos */
        }
        /* Estilos para el texto del encabezado cuando se activa la ordenación ascendente */
        .dataTables_wrapper .sorting_asc:after {
            content: ' ↑'; /* Agrega una flecha hacia arriba al texto */
            color:yellow; /* Cambia el color del texto cuando se activa la ordenación ascendente */
         }

        /* Estilos para el texto del encabezado cuando se activa la ordenación descendente */
        .dataTables_wrapper .sorting_desc:after {
            content: ' ↓'; /* Agrega una flecha hacia abajo al texto */
            color: red; /* Cambia el color del texto cuando se activa la ordenación descendente */
        }
    </style>

</body>
</html>
