package Controlador;

import DaoPersistencia.DaoVaca;
import Modelo.Vaca;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Vaca;


public class ControladorVaca extends HttpServlet {

    Vaca user = new Vaca();

    int ide;       // Variable de instancia para almacenar el ID

    //La variable serialVersionUID se utiliza en Java para asignar una versión única 
    //a una clase Serializable.
    //En este contexto, private static final long serialVersionUID = 1L; simplemente está estableciendo 
    //el serialVersionUID de la clase ControladorConsecutivo
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String action = request.getParameter("accion");

        switch (action) {

            case "registrar":
                registrarVaca(request, response);
                break;

            case "listar":
                listarVaca(request, response);
                break;

            case "editar":    // listar para editar
                editarVaca(request, response);
                break;

            case "actualizar":
                actualizarVaca(request, response);
                break;

            case "buscar":
                  buscarVaca(request, response);
                break;

            case "eliminar":
                eliminarVaca(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    // Metodos para procesar a informacion enviada desde el formulario (Vista)
    
    private void registrarVaca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            int Usu = Integer.parseInt(request.getParameter("txtIdUsu"));
            String placa = request.getParameter("txtPlaca");
            String nomva = request.getParameter("txtNombre");
            String fecha = request.getParameter("txtFecha");
            String madre = request.getParameter("txtMadre");
            String padre = request.getParameter("txtPadre");
            
           Vaca vaca = new Vaca();

            vaca.setUsuarioId(Usu);
            vaca.setNumPlaca(placa);
            vaca.setNombreVaca(nomva);
            vaca.setFechanacimiento(fecha);
            vaca.setMadre(madre);
            vaca.setPadre(padre);
                    
            if (DaoVaca.grabar(vaca)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            
//Listar la informacion registrada en el formulario

            List<Vaca> lt = DaoVaca.listar();
            request.setAttribute("listaVaca", lt);
            request.getRequestDispatcher("Vista/ListarVaca.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
          
// Esta linea es igual a 3 lineas que estan en listar la informacion registrada
           listarVaca(request, response);
        }
    }

   private void listarVaca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           List<Vaca> lt = DaoVaca.listar();
            request.setAttribute("listaVaca", lt);
            request.getRequestDispatcher("Vista/ListarVaca.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Reporte");
             request.getRequestDispatcher("Vista/ListarVaca.jsp").forward(request, response);
        }
    }

    private void editarVaca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            Vaca v = DaoVaca.obtenerVacaPorId(ide);
            request.setAttribute("User", v);

            List<Vaca> lt = DaoVaca.listar();
            request.setAttribute("listaVaca", lt);
            request.getRequestDispatcher("Vista/ListarVaca.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarVaca(request, response);
        }
    }
    private void actualizarVaca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Utilizar la variable de instancia para obtener el ID
            Vaca v = DaoVaca.obtenerVacaPorId(ide);
            request.setAttribute("User", v);

            // Resto del código para actualizar el Consecutivo
            
             int Usu = Integer.parseInt(request.getParameter("txtIdUsu"));
            String placa = request.getParameter("txtPlaca");
            String nomva = request.getParameter("txtNombre");
            String fecha = request.getParameter("txtFecha");
            String madre = request.getParameter("txtMadre");
            String padre = request.getParameter("txtPadre");
            
           //Creamos un Objeto de tipo Usuario
            Vaca vaca = new Vaca();

            // Cargamos el Objeto de tipo Usuario con la informacion que viene del formulario
            
            vaca.setUsuarioId(Usu);
            vaca.setNumPlaca(placa);
            vaca.setNombreVaca(nomva);
            vaca.setFechanacimiento(fecha);
            vaca.setMadre(madre);
            vaca.setPadre(padre);
            vaca.setIdVaca(ide);    // Utilizar la variable de instancia para obtener el ID del Usuario

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoVaca.actualizar(vaca);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Usuario actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Resgistro");
            }

            List<Vaca> lt = DaoVaca.listar();
            request.setAttribute("listaVaca", lt);
            request.getRequestDispatcher("Vista/ListarVaca.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarVaca(request, response);
        }
    }
       private void buscarVaca(HttpServletRequest request, HttpServletResponse response)    /// en barra de //
            throws ServletException, IOException {
        try {
           String texto = request.getParameter("txtbuscar");
            List<Vaca> lt = DaoVaca.buscarVaca(texto);
           request.setAttribute("listaVaca", lt);
            request.getRequestDispatcher("Vista/ListarVaca.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListarVaca.jsp").forward(request, response);
        }
    }
    
        private void eliminarVaca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVaca = Integer.parseInt(request.getParameter("id"));

            if (DaoVaca.eliminar(idVaca)) {
                request.setAttribute("mensaje", "El Registro fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el Registro");
            }

            listarVaca(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarVaca(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}