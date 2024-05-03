package Controlador;

import DaoPersistencia.DaoTipoDocumento;
import Modelo.TipoDocumento;
import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorTipoDocumento extends HttpServlet {

 TipoDocumento user = new TipoDocumento();

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
                registrarTipoDoc(request, response);
                break;

            case "listar":
                listarTipoDoc(request, response);
                break;

            case "editar":    // listar para editar
                editarTipoDoc(request, response);
                break;

            case "actualizar":
                actualizarTipoDoc(request, response);
                break;

            case "buscar":
                  buscarTipoDoc(request, response);
                break;

            case "eliminar":
                eliminarTipoDoc(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
        
    }
    
   // Metodos para procesar a informacion enviada desde el formulario (Vista)
      
    private void registrarTipoDoc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String nombre = request.getParameter("txtNombre");
            

            TipoDocumento tipodocumento = new TipoDocumento();
           
            tipodocumento.setNombredocumento(nombre);

            if (DaoTipoDocumento.grabar(tipodocumento)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            //Listar la informacion registrada en el formulario

            List<TipoDocumento> lt = DaoTipoDocumento.listar();
            request.setAttribute("listaTipoDocumento", lt);
            request.getRequestDispatcher("Vista/ListarTipoDocumento.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            
            // Esta linea es igual a 3 lineas que estan en listar la informacion registrada
            listarTipoDoc(request, response);
        }
    }

    private void listarTipoDoc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<TipoDocumento> lt = DaoTipoDocumento.listar();
            request.setAttribute("listaTipoDocumento", lt);
            request.getRequestDispatcher("Vista/ListarTipoDocumento.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarTipoDocumento.jsp").forward(request, response);
        }
    }

    private void editarTipoDoc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            TipoDocumento tp = DaoTipoDocumento.obtenerTipodocumentoPorID(ide);
            
            request.setAttribute("User", tp);

            List<TipoDocumento> lt = DaoTipoDocumento.listar();
            request.setAttribute("listaTipoDocumento", lt);
            request.getRequestDispatcher("Vista/ListarTipoDocumento.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarTipoDoc (request, response);
        }
    }

    private void actualizarTipoDoc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Utilizar la variable de instancia para obtener el ID
            TipoDocumento tp = DaoTipoDocumento.obtenerTipodocumentoPorID(ide);
            request.setAttribute("User", tp);

            // Resto del código para actualizar el Consecutivo
            
            String nombre = request.getParameter("txtNombre");
            
            
            //Creamos un Objeto de tipo  tiipodocumento
                        
          TipoDocumento  tipodocumento = new  TipoDocumento();

            // Cargamos el Objeto de tipo Usuario con la informacion que viene del formulario
                            
           tipodocumento.setNombredocumento(nombre);
           tipodocumento.setIdTipodocumento(ide);   // Utilizar la variable de instancia para obtener el ID del TipoDocumento

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoTipoDocumento.actualizar(tipodocumento);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Usuario actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Resgistro");
            }

            List<TipoDocumento> lt = DaoTipoDocumento.listar();
            request.setAttribute("listaTipoDocumento", lt);
            request.getRequestDispatcher("Vista/ListarTipoDocumento.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarTipoDoc(request, response);
        }
    }

       private void buscarTipoDoc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           String text = request.getParameter("txtbuscar");
            List<TipoDocumento> lt = DaoTipoDocumento.buscarTipoDocumento(text);
           request.setAttribute("listaTipoDocumento", lt);
            request.getRequestDispatcher("Vista/ListarTipoDocumento.jsp").forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListarTipoDocumento.jsp").forward(request, response);
        }
        
    }
    
        
private void eliminarTipoDoc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idTipodocumento = Integer.parseInt(request.getParameter("id"));

            if (DaoTipoDocumento.eliminar(idTipodocumento)) {
                request.setAttribute("mensaje", "El Usuario fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el usuario");
            }

             listarTipoDoc(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
             listarTipoDoc(request, response);
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
