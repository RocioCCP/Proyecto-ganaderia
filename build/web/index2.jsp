<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Incluye los archivos CSS de Bootstrap -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <link href="EstilosCSS/EstilosFondo.css" rel="stylesheet" type="text/css"/>

        <style>
            #cuadro {
                max-width: 600px;
                margin: 50px auto;
                /*  background-image: url('Fondo.jpg'');*/
                background-size: cover;
                background-position: center;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.7); /* Agregado sombreado al cuadro */
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            #cuadro h1 {
                text-align: center;
                color: #000066;
                margin-top: 0;
                font-family: 'Lucida Console', sans-serif; /* Cambiado a Arial, puedes usar otras fuentes como 'Georgia', 'Times New Roman', etc. */
                text-shadow: 2px 2px 4px rgba(0, 250, 250, 0.9);
                margin: 2;
                font-weight: bold; /* Agregado negrita */
                font-size: 40px; /* Aumentado el tamaño de letra a 28px, ajusta según tus necesidades */
            }

            #cuadro img {
                max-width: 180px; /* Ajusta el ancho máximo del logo según sea necesario */
                height: auto;
                border-radius: 5px;
            }


            /*otros estilos para el cuadro
            /* #cuadro img {
                max-width: 150px; /* Ajusta el ancho máximo del logo según sea necesario */
            /*height: auto;
            display: block;
            margin: 20px 0; /* Ajusta el margen superior e inferior según sea necesario */
            /* border-radius: 5px;
             float: left; /* Alinea el logo a la izquierda */
            /* margin: 20px auto; /* Centra la imagen en el cuadro */
            /*margin-right: 0; /* Ajusta el margen derecho según sea necesario */



        </style>

    </head>
    <body>
        <div class="row">
            <div class="col-12"> 
               <nav class="navbar navbar-expand-md navbar-dark bg-dark border-3 fixed-top border-bottom" id="menu">
                    <div class="container-fluid">

                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                              
                                <!--target="_blank" esto sirve para abrir una pagina independiente -->

                                <li class="nav-item">
                                    <a style="margin-left: 10px; border:none" class="btn btn-outline-light"
                                       href="ControladorUsuario?accion=listar">Usuarios</a>
                                </li>
                                 <li class="nav-item">
                                    <a style="margin-left: 10px; border:none" class="btn btn-outline-light"
                                       href="ControladorVaca?accion=listar">Vaca</a>
                                </li>
                                 <li class="nav-item">
                                    <a style="margin-left: 10px; border:none" class="btn btn-outline-light"
                                       href="ControladorCicloReproductivo?accion=listar">Ciclo Reproductivo</a>
                                </li>
                                <li class="nav-item">
                                    <a style="margin-left: 10px; border:none" class="btn btn-outline-light"
                                       href="ControladorHistoriaClinica?accion=listar">Historia Clinica</a>
                                </li>
                                <li class="nav-item">
                                    <a style="margin-left: 10px; border:none" class="btn btn-outline-light"
                                       href="ControladorProduccion?accion=listar">Produccion</a>
                                </li>
                                
                                <li class="nav-item dropdown">
                                    <a style="margin-left: 10px; border:none" class="btn btn-outline-light dropdown-toggle"
                                       href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Administrar Información    
                                    </a>
                                   
                                    </a>
                                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><a class="dropdown-item" href="ControladorProcedimientos?accion=listar"><b style="color: #0d6efd;">Procedimientos</b></a></li>
                                        <li><hr class="dropdown-divider"></li>                                       
                                        <li><a class="dropdown-item" href="ControladorMedicamentos?accion=listar"><b>Medicamentos</b></a></li>
                                        <li><hr class="dropdown-divider"></li>       
                                        <li><a class="dropdown-item" href="ControladorTipoDocumento?accion=listar"><b style="color: #34ce57;">Tipo Documento</b></a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="ControladorPerfil?accion=listar"><b style="color: #0d6efd;">Perfil</b></a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="ControladorCiudad?accion=listar"><b>Ciudad</b></a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="ControladorProveedor?accion=listar"><b>Proveedor</b></a></li>
                                        <li><hr class="dropdown-divider"></li>
                                    </ul>                               
                                    </div>
                                    <!-- Botón de inicio -->
                                    <a class="btn btn-outline-light" href="./index.jsp">
                                        <i class="bi bi-arrow-left-square-fill text-dark"></i> <b>Inicio</b>
                                    </a>
                                    </div>
                                    </nav>    
                                  </div>   

        <!-- Contenedor principal centrado -->

        <div class="row">
            <div class="col-md-12 mx-auto d-flex justify-content-center">
                <div id="cuadro">
                    <img src="Imagenes/softrcc.jpg" alt=""/>
                    <h1 class="mt-5">  ¡BIENVENIDO! SISTEMA INFORMACION GANADERIA<h1>
                </div>

            </div>
        </div>
<script>
  
    </script>

        <!-- Bootstrap CSS y JavaScript -->
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"
        ></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
                crossorigin="anonymous"
        ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
                crossorigin="anonymous"
        ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous"
        ></script>


    </body>
</html>
