package Controlador;

import DaoPersistencia.DaoCiudad;
import Modelo.Ciudad;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rocio Castillo
 */
 public class ControladorCiudad extends HttpServlet {

 Ciudad user = new Ciudad();

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
                registrarCiudad(request, response);
                break;

            case "listar":
                listarCiudad(request, response);
                break;

            case "editar":    // listar para editar
                editarCiudad(request, response);
                break;

            case "actualizar":
                actualizarCiudad(request, response);
                break;

            case "buscar":
                  buscarCiudad(request, response);
                break;

            case "eliminar":
                eliminarCiudad(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
        
    }
    
   // Metodos para procesar a informacion enviada desde el formulario (Vista)
      
    private void registrarCiudad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String ciud = request.getParameter("txtCiud");
            

            Ciudad ciudad = new Ciudad();
           
            ciudad.setCiudad(ciud);

            if (DaoCiudad.grabar(ciudad)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            //Listar la informacion registrada en el formulario

            List<Ciudad> lt = DaoCiudad.listar();
            request.setAttribute("listaCiudad", lt);
            request.getRequestDispatcher("Vista/ListarCiudad.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            
            // Esta linea es igual a 3 lineas que estan en listar la informacion registrada
            listarCiudad(request, response);
        }
    }

    private void listarCiudad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Ciudad> lt = DaoCiudad.listar();
            request.setAttribute("listaCiudad", lt);
            request.getRequestDispatcher("Vista/ListarCiudad.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarCiudad.jsp").forward(request, response);
        }
    }

    private void editarCiudad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            Ciudad c = DaoCiudad.obtenerCiudadPorID(ide);
            
            request.setAttribute("User", c);

            List<Ciudad> lt = DaoCiudad.listar();
            request.setAttribute("listaCiudad", lt);
            request.getRequestDispatcher("Vista/ListarCiudad.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarCiudad (request, response);
        }
    }

    private void actualizarCiudad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Utilizar la variable de instancia para obtener el ID
            Ciudad c = DaoCiudad.obtenerCiudadPorID(ide);
            request.setAttribute("User", c);

            // Resto del código para actualizar el Consecutivo
            
            String ciud = request.getParameter("txtCiud");
            
            
            //Creamos un Objeto de tipo  tiipodocumento
                        
          Ciudad  ciudad = new  Ciudad();

            // Cargamos el Objeto de tipo Ciudad con la informacion que viene del formulario
                            
           ciudad.setCiudad(ciud);
           ciudad.setIdCiudad(ide);   // Utilizar la variable de instancia para obtener el ID del TipoDocumento

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoCiudad.actualizar(ciudad);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Ciudad actualizada correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Resgistro");
            }

            List<Ciudad> lt = DaoCiudad.listar();
            request.setAttribute("listaCiudad", lt);
            request.getRequestDispatcher("Vista/ListarCiudad.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarCiudad(request, response);
        }
    }

       private void buscarCiudad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           String text = request.getParameter("txtbuscar");
            List<Ciudad> lt = DaoCiudad.buscarCiudad(text);
           request.setAttribute("listaCiudad", lt);
            request.getRequestDispatcher("Vista/ListarCiudad.jsp").forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListarCiudad.jsp").forward(request, response);
        }
        
    }
            
private void eliminarCiudad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idCiudad = Integer.parseInt(request.getParameter("id"));

            if (DaoCiudad.eliminar(idCiudad)) {
                request.setAttribute("mensaje", "La ciudad fue Eliminada Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar la ciudad");
            }

             listarCiudad(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
             listarCiudad(request, response);
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
 


