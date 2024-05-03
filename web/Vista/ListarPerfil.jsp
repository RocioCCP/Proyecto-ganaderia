<%@page import="java.util.List"%>
<%@page import="Modelo.Perfil"%>
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
                <button 
                    class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse  " id="navbarSupportedContent">

                   
                 <ul class="navbar-nav mb-3 mb-lg-0 float-start ">   

                        <li class="nav-item ">
                            <a class="nav-link active my-menu-item" aria-current="page" href="./index.jsp"><b>
                                    <span  style="margin-left: 10px; border:none" class="btn btn-outline-light">
                                        <i class="bi bi-arrow-left-square-fill text-dark"></i> <b> Inicio </b>
                                    </span>
                            </a>
                        </li>
                       </ul>
                </div>
            </div>
        </nav>
        <!--Barra de Navegacion -->
        
        <div class="row ">
            <hr>
            <div class="card col-sm-4 mb-2  mt-5 sticky-top">
               
                 <h4 class="text-secondary elegant-font mt-4"><b>Formulario Perfiles</b></h4> 

                <form action="ControladorPerfil" method="POST" autocomplete="off" class="custom-form">
                    
                    
                        <div class="col-md-8 ">
                        <br>

                      <div class="form-group text-left">
                            <label for="perfi" class="text-left">Nombre</label>
                            <input type="text" class="form-control" value="${User.getTipoperfil()}" 
                                   id="perf" name="txtPerfil" placeholder="Ingrese Nombre" required
                                      pattern="[A-Za-z0-9\s]*"
                                       title="Escribe un nombre puede contener letras y/o números">
                                <small id="perfError" class="error form-text text-muted">
                                     Campo es obligatorio <span class="error">*</span>
                                    </small>
                        </div>   
                             
           
                    </div>

                    <div class="form-group mt-3 text-center" style="border: none;">

                        <br>
                        <button type="submit" name="accion" value="registrar" class="btn btn-warning">
                            <i class="fas fa-save "></i> Agregar</button>
                        <button type="submit" name="accion" value="actualizar" class="btn btn-success">
                            <i class="bi bi-arrow-repeat"></i> Actualizar</button>
                        <button type="submit" name="accion" value="listar" class="btn btn-secondary">
                            <i class="bi bi-x-lg"></i> Cancelar</button>

                    </div>    
                </form>
            </div>   

            <%-- Tabla para listar --%>
            <div class="card col-sm-4 mb-4  mt-5 sticky-top">
                <div class="col-sm-9 mb-4 mt-5 sticky-top">
                    
                    <h4 class="text-secondary elegant-font mt-4"><b>Lista Tipo Perfil</b></h4> 
                    <br>
                    <div class=" table-container ml-4 md-4 table-responsive" >
                        <table id="miTabla" class="table table-striped table-hover sticky-top">
                            <thead >
                                <tr>
                                    <th>ID</th>
                                    <th>Perfil</th>                     
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                 <%
                                    List<Perfil> Lista = (List<Perfil>) request.getAttribute("listaPerfil");
                                    for (Perfil perfil : Lista) {%>
                                <tr>
                                <tr>
                                    <td><%= perfil.getIdPerfil()%></td>
                                    <td><%= perfil.getTipoperfil()%></td>                           

                                    <td>
                                        <div class="btn-group" role="group" aria-label="Acciones">

                                            <div class="mt-2">
                                            <a href="ControladorPerfil?accion=editar&id=<%= perfil.getIdPerfil()%>" class="btn btn-outline-light btn-success">  
                                                <!-- id  de un usuario se lo envio al controlador  en variable id -->  
                                                <i class="fas fa-pencil-alt"></i> <!-- Ícono de lápiz -->  
                                            </a>
                                            <a href="ControladorPerfil?accion=eliminar&id=<%=perfil.getIdPerfil()%>"
                                                class="btn btn-secondary" onclick="return confirm('¿Estás seguro de que deseas eliminar este registro?')">
                                                <i class="fas fa-trash"></i> <!-- Ícono de papelera -->
                                            </a>  
                                    </div>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group text-right">
                    <a href="ControladorPerfil?accion=listar" class="btn btn-primary mr-2"style="background-color: #0C4F5A; border-color:#0C4F5A;">
                        <i class="fas fa-list"></i> Listar</a>
                    <a href="./index2.jsp" class="btn btn-warning"style="background-color: #90DEEB; border-color:#90DEEB;">
                        <i class="bi bi-arrow-left-square-fill text-dark"></i> Inicio</a>
                </div>
            </div>
            <h1>${mensaje}</h1>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">GUARDAR</button>
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

<script>
                        $(document).ready(function () {
                            $('#miTabla').DataTable({
                                "paging": true, // Habilita la paginación
                                "pageLength": 10, // Número de registros por página
                                "language": {
                                    "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Spanish.json"
                                }
                            });
                        });
</script>

<style>
    /* CSS personalizado para DataTables */
    .dataTables_wrapper {
        font-family: 'Roboto', sans-serif;
         color:gray;

    }



</style>
    
    
    
    
    
</body>
</html>
