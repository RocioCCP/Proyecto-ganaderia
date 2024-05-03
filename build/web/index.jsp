
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
        <link href="EstilosCSS/css_tablas.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>
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
                /* margin-top: 0;*/
                font-family: 'Lucida Console', sans-serif; /* Cambiado a Arial, puedes usar otras fuentes como 'Georgia', 'Times New Roman', etc. */
                text-shadow: 2px 2px 4px rgba(0, 255, 255, 0.9);
                margin: 2;
                font-weight: bold; /* Agregado negrita */
                font-size: 50px; /* Aumentado el tamaño de letra a 28px, ajusta según tus necesidades */
            }

            #cuadro img {
                max-width: 150px; /* Ajusta el ancho máximo del logo según sea necesario */
                height: auto;
                border-radius: 5px;
            }


        </style>


    </nav>
    <!--Barra de Navegacion -->

    <div class="container mt-4 col-lg-4">
        <div class="card col-sm-10">
            <div class="card-body">

                <h4> Bienvenidos Software Ganaderia</h4>

                <form class="form-sing" action="ControladorValidar" method="POST"> 
                    <div class="form-group text-center">
                       
                        <label>Ingreso al Sistema</label>
                    </div>


                    <div class="form-group">
                        <label>Usuario:</label>
                        <input type="text" name="txtUser" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" name="txtclave" class="form-control">
                    </div>

                    <input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-block">

                    <!-- Add the error message display -->
                    <div class="form-group text-center">
                        <p style="color: red;">${errorMensaje}</p>
                    </div>

                </form>
            </div>
        </div>
        </div>

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

</html>

