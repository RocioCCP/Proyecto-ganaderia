package Controlador;

import DaoPersistencia.DaoPerfil;
import Modelo.Perfil;
import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorPerfil extends HttpServlet {

 Perfil user = new Perfil();

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
                registrarPerfil(request, response);
                break;

            case "listar":
                listarPerfil(request, response);
                break;

            case "editar":    // listar para editar
                editarPerfil(request, response);
                break;

            case "actualizar":
                actualizarPerfil(request, response);
                break;

            case "buscar":
                  buscarPerfil(request, response);
                break;

            case "eliminar":
                eliminarPerfil(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
        
    }
    
   // Metodos para procesar a informacion enviada desde el formulario (Vista)
      
    private void registrarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String perf = request.getParameter("txtPerfil");
            
            Perfil perfil = new Perfil();
           perfil.setTipoperfil(perf);
            
            if (DaoPerfil.grabar(perfil)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            //Listar la informacion registrada en el formulario

            List<Perfil> lt = DaoPerfil.listar();
            request.setAttribute("listaPerfil", lt);
            request.getRequestDispatcher("Vista/ListarPerfil.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            
            // Esta linea es igual a 3 lineas que estan en listar la informacion registrada
            listarPerfil(request, response);
        }
    }

    private void listarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Perfil> lt = DaoPerfil.listar();
            request.setAttribute("listaPerfil", lt);
            request.getRequestDispatcher("Vista/ListarPerfil.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarPerfil.jsp").forward(request, response);
        }
    }

    private void editarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            Perfil p = DaoPerfil.obtenerPerfilPorID(ide);
            
            request.setAttribute("User", p);

            List<Perfil> lt = DaoPerfil.listar();
            request.setAttribute("listaPerfil", lt);
            request.getRequestDispatcher("Vista/ListarPerfil.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarPerfil (request, response);
        }
    }

    private void actualizarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Utilizar la variable de instancia para obtener el ID
            Perfil p = DaoPerfil.obtenerPerfilPorID(ide);
            request.setAttribute("User",p);

            // Resto del código para actualizar el Consecutivo
            
          String perf = request.getParameter("txtPerfil");
            
            //Creamos un Objeto de tipo  tiipodocumento
                        
          Perfil  perfil = new  Perfil();

            // Cargamos el Objeto de tipo Usuario con la informacion que viene del formulario
                            
           perfil.setTipoperfil(perf);
           perfil.setIdPerfil(ide);   // Utilizar la variable de instancia para obtener el ID del TipoDocumento

          boolean actualizacionExitosa = DaoPerfil.actualizar(perfil);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Perfil actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Resgistro");
            }

            List<Perfil> lt = DaoPerfil.listar();
            request.setAttribute("listaPerfil", lt);
            request.getRequestDispatcher("Vista/ListarPerfil.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarPerfil(request, response);
        }
    }

       private void buscarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           String text = request.getParameter("txtbuscar");
            List<Perfil> lt = DaoPerfil.buscarPerfil(text);
           request.setAttribute("listaPerfil", lt);
            request.getRequestDispatcher("Vista/ListarPerfil.jsp").forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListarPerfil.jsp").forward(request, response);
        }
        
    }
    
        
private void eliminarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idPerfil = Integer.parseInt(request.getParameter("id"));

            if (DaoPerfil.eliminar(idPerfil)) {
                request.setAttribute("mensaje", "El Perfil fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el Perfil");
            }

             listarPerfil(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
             listarPerfil(request, response);
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
