/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DaoPersistencia.DaoProcedimientos;
import Modelo.Procedimientos;
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
public class ControladorProcedimientos extends HttpServlet {

 Procedimientos user = new Procedimientos();

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
                registrarProc(request, response);
                break;

            case "listar":
                listarProc(request, response);
                break;

            case "editar":    // listar para editar
                editarProc(request, response);
                break;

            case "actualizar":
                actualizarProc(request, response);
                break;

            case "buscar":
                  buscarProc(request, response);
                break;

            case "eliminar":
                eliminarProc(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
        
    }
    
   // Metodos para procesar a informacion enviada desde el formulario (Vista)
      
    private void registrarProc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String proced = request.getParameter("txtProced");
            

            Procedimientos procedimientos = new Procedimientos();
           
            procedimientos.setTipoprocedimiento(proced);

            if (DaoProcedimientos.grabar(procedimientos)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            //Listar la informacion registrada en el formulario

            List<Procedimientos> lt = DaoProcedimientos.listar();
            request.setAttribute("listaProcedimientos", lt);
            request.getRequestDispatcher("Vista/ListarProcedimientos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            
            // Esta linea es igual a 3 lineas que estan en listar la informacion registrada
            listarProc(request, response);
        }
    }

    private void listarProc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Procedimientos> lt = DaoProcedimientos.listar();
            request.setAttribute("listaProcedimientos", lt);
            request.getRequestDispatcher("Vista/ListarProcedimientos.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarProcedimientos.jsp").forward(request, response);
        }
    }

    private void editarProc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            Procedimientos p = DaoProcedimientos.obtenerProcedimientosPorID(ide);
            
            request.setAttribute("User", p);

            List<Procedimientos> lt = DaoProcedimientos.listar();
            request.setAttribute("listaProcedimientos", lt);
            request.getRequestDispatcher("Vista/ListarProcedimientos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarProc (request, response);
        }
    }

    private void actualizarProc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Utilizar la variable de instancia para obtener el ID
            Procedimientos p = DaoProcedimientos.obtenerProcedimientosPorID(ide);
            request.setAttribute("User", p);

            // Resto del código para actualizar el Consecutivo
            
              String proced = request.getParameter("txtProced");
            
            //Creamos un Objeto de tipo  tiipodocumento
                        
          Procedimientos  procedimientos = new  Procedimientos();

            // Cargamos el Objeto de tipo Usuario con la informacion que viene del formulario
                            
          procedimientos.setTipoprocedimiento(proced);
          procedimientos.setIdProcedimientos(ide);;   // Utilizar la variable de instancia para obtener el ID del TipoDocumento

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoProcedimientos.actualizar(procedimientos);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Procedimiento actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Resgistro");
            }

            List<Procedimientos> lt = DaoProcedimientos.listar();
            request.setAttribute("listaProcedimientos", lt);
            request.getRequestDispatcher("Vista/ListarProcedimientos.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarProc(request, response);
        }
    }

       private void buscarProc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           String text = request.getParameter("txtbuscar");
            List<Procedimientos> lt = DaoProcedimientos.buscarProcedimientos(text);
           request.setAttribute("listaProcedimientos", lt);
            request.getRequestDispatcher("Vista/ListarProcedimientos.jsp").forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListarProcedimientos.jsp").forward(request, response);
        }
        
    }
    
        
private void eliminarProc(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idProcedimientos = Integer.parseInt(request.getParameter("id"));

            if (DaoProcedimientos.eliminar(idProcedimientos)) {
                request.setAttribute("mensaje", "El Procedimiento fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el procedimiento");
            }

             listarProc(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
             listarProc(request, response);
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
