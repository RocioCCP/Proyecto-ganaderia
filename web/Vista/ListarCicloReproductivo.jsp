

<%@page import="Modelo.CicloReproductivo"%>
<%@page import="DaoPersistencia.DaoProveedor"%>
<%@page import="Modelo.Proveedor"%>
<%@page import="DaoPersistencia.DaoVaca"%>
<%@page import="Modelo.Vaca"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                            <a class="nav-link active my-menu-item" aria-current="page" href="./index2.jsp"><b>
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

        <div class="row">           
            <hr>
            <div class="card col-sm-4 mb-6  mt-5 sticky-top">
             <br>

               <h4 class="text-secondary elegant-font mt-4t"><b>FORMULARIO CICLO REPRODUCTIVO</b></h4> 

                <%--  Inicio Formulario --%>

                <form action="ControladorCicloReproductivo" method="POST" autocomplete="off" class="custom-form">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group text-left">
                                <label for="nomb">Nombre Vaca</label>
                                <select class="form-control" id="vaca" name="txtVaca" required>
                                    <option value="0">Seleccione Nombre</option>
                                    <% List<Vaca> vacas = DaoVaca.listar();
                        if (vacas != null) {
                            for (Vaca vaca : vacas) {
                                if (vaca != null) {%>
                                    <option value="<%=vaca.getIdVaca()%>"><%=vaca.getNombreVaca()%></option>
                                    <% }
                            }
                        }%>
                                </select>
                                <small id="vacaError" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                </small>
                            </div>

                            <div class="form-group text-left">
                                <label for="provee">Ganaderia</label>
                                <select class="form-control" id="prove" name="txtProv" required>
                                    <option value="0">Seleccione Ganaderia</option>
                                    <% List<Proveedor> proveedores = DaoProveedor.listar();
                                        if (proveedores != null) {
                                            for (Proveedor proveedor : proveedores) {
                                if (proveedor != null) {%>
                                    <option value="<%=proveedor.getIdProveedor()%>"><%=proveedor.getNomProveedor()%></option>
                                    <% }
                            }
                        }%>
                                </select>
                                <small id="proveError" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                </small>
                            </div>

                            <div class="form-group text-left">
                                <label for="tor">Toro</label>
                                <select class="form-control" id="tor" name="txtToro" required>
                                    <option value="0">Seleccione Toro</option>
                                    <% List<Proveedor> proveedors = DaoProveedor.listar();
                                        if (proveedors != null) {
                                            for (Proveedor proveedor : proveedors) {
                                if (proveedor != null) {%>
                                    <option value="<%=proveedor.getIdProveedor()%>"><%=proveedor.getNomToro()%></option>
                                    <% }
                            }
                        }%>
                                </select>
                                <small id="torError" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                </small>
                            </div>

                            <div class="form-group text-left">
                                <label for="fepa">Fecha Parto</label>
                                <input type="date" class="form-control" id="fechapa" name="txtFechaPa" required value="${User.getFechaParto()}">
                                <small id="fechapaError" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                </small>
                            </div>

                            <div class="form-group text-left">
                                <label for="fepa">Editando ID</label>
                                <input type="text" readonly="" class="form-control" id="idedi" name="txt" value="${User.getIdCicloReproductivo()}">
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group text-left">
                                <label for="fech">Fecha Insem.</label>
                                <input type="date" class="form-control" id="fechain" name="txtFechaIn" required value="${User.getFechainseminacion()}">
                                <small id="fechainError" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                </small>
                            </div>

                            <div class="form-group text-left">
                                <label for="sex">Sexo:</label>
                                <input type="text" class="form-control" id="sex" name="txtSexo" required value="${User.getSexoCria()}" placeholder="Ingrese sexo">
                                <small id="sexError" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                </small>
                            </div>

                            <div class="form-group text-left">
                                <label for="observ">Observaciones:</label>
                                <input type="text" class="form-control" id="observ" name="txobserva" required value="${User.getObservaciones()}" placeholder="Ingrese observaciones">
                                <small id="observError" class="error form-text text-muted">
                                    Campo es obligatorio <span class="error">*</span>
                                </small>
                            </div>

                            <div class="form-group text-left">
                                <label for="cria"># Crias</label>
                                <input type="text" class="form-control" id="crias" name="txtCrias" required value="${User.getNumeroCrias()}" placeholder="Ingrese dato">
                                <small id="criasError" class="error form-text text-muted">
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
                            <i class="bi bi-arrow-repeat"></i> Actualizar
                        </button>
                        <button type="submit" name="accion" value="listar" class="btn btn-secondary">
                            <i class="bi bi-x-lg"></i> Cancelar
                        </button>
                    </div>
                </form>
            </div>   

            <div class="col-sm-7 mb-4 mt-5 sticky-top">
                <br>
                
                <h4 class="text-secondary elegant-font mt-4t" ><b > Lista Ciclo Reproductivo</b></h4> 

                <div class=" table-container ml-3 md-3 table-responsive" >
                    <table  id="miTabla" class="table table-striped table-hover sticky-top">
                        <thead >
                            <tr>
                                <th>ID</th>
                                <th>Vaca</th>                       
                                <th>Placa</th>                       
                                <th>fecha Inse. </th>                       
                                <th>Ganaderia</th>
                                <th>Toro</th>
                                <th>Fecha parto</th>
                                <th># Crias</th>
                                <th>Sexo Cria</th>
                                <th>Observaciones</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<CicloReproductivo> Lista = (List<CicloReproductivo>) request.getAttribute("listaCicloReproductivo");
                                for (CicloReproductivo cicloreproductivo : Lista) {%>
                            <tr>
                                <td><%= cicloreproductivo.getIdCicloReproductivo()%></td>
                                <td><%=DaoVaca.obtenerunNombreVacaporid(cicloreproductivo.getVacaId())%></td>
                                <td><%=DaoVaca.obtenerunPlacaVacaporid(cicloreproductivo.getVacaId())%></td>
                                 <td><%=cicloreproductivo.getFechainseminacion()%></td>       
                                 <td><%=DaoProveedor.obtenerunNombreProveedor(cicloreproductivo.getProveedorId())%></td>
                                 <td><%=DaoProveedor.obtenerunNombretoroProveedor(cicloreproductivo.getProveedorId())%></td>
                                <td><%= cicloreproductivo.getFechaParto()%></td>
                                <td><%= cicloreproductivo.getNumeroCrias()%></td>
                                <td><%= cicloreproductivo.getSexoCria()%></td>
                               <td><%= cicloreproductivo.getObservaciones()%></td>
                                <td>
                                    <div class="btn-group" role="group" aria-label="Acciones">

                                        <div class="mt-2">
                                            <a href="ControladorCicloReproductivo?accion=editar&id=<%= cicloreproductivo.getIdCicloReproductivo()%>" class="btn btn-outline-light btn-success "> 
                                                <!-- id de un usuario se lo envio al controlador en variable id -->  
                                                <i class="fas fa-pencil-alt "></i> <!-- Ícono de lápiz -->  
                                            </a>
                                            <a href="ControladorCicloReproductivo?accion=eliminar&id=<%= cicloreproductivo.getIdCicloReproductivo()%>" 
                                               class="btn btn-secondary" onclick="return confirm('¿Estás seguro de que deseas eliminar este registro?')">
                                                <i class="fas fa-trash"></i> <!-- Ícono de papelera -->
                                            </a> 
                                        </div>                             
                                    </div>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                   </div>       
                <div class="form-group mt-4 text-right">
                    <a href="ControladorCicloReproductivo?accion=listar" class="btn btn-primary mr-2"style="background-color: #0C4F5A; border-color:#0C4F5A;">
                        <i class="fas fa-list"></i> Listar</a>
                    <a href="./index2.jsp" class="btn btn-warning"style="background-color: #90DEEB; border-color:#90DEEB;">
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
    function calcularTotal() {
        var producido1 = parseFloat(document.getElementById("prod1").value);
        var producido2 = parseFloat(document.getElementById("prod2").value);
        var total = producido1 + producido2;
        document.getElementById("txtTotal").value = total.toFixed(2); // Redondear el resultado a 2 decimales
    }
</script>
            
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
