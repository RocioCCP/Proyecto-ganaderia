

package Controlador;

import DaoPersistencia.DaoProduccion;
import Modelo.Produccion;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorProduccion extends HttpServlet {
    
  Produccion user = new Produccion();

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
                registrarProd(request, response);
                break;

            case "listar":
                listarProd(request, response);
                break;

            case "editar":    // listar para editar
                editarProd(request, response);
                break;

            case "actualizar":
                actualizarProd(request, response);
                break;

            case "buscar":
                buscarProd(request, response);
                break;

            case "eliminar":
                eliminarProd(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    // Metodos para procesar a informacion enviada desde el formulario (Vista)
    private void registrarProd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           
            Produccion produccion = new Produccion();

            produccion.setVacaId(Integer.parseInt(request.getParameter("txtVaca")));
            produccion.setFecha(request.getParameter("txtFecha"));
            produccion.setProducido1(Double.valueOf(request.getParameter("txtProdu1")));
            produccion.setProducido2(Double.valueOf(request.getParameter("txtProdu2")));
            produccion.setTotalproduc(Double.valueOf(request.getParameter("txtTotal")));
           produccion.setObservaciones(request.getParameter("txtobserva"));
       
            if (DaoProduccion.grabar(produccion)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro no fue registrado, validar campos ingresados");
            }

            //Listar la información registrada en el formulario
            List<Produccion> lt = DaoProduccion.listar();
            request.setAttribute("listaProduccion", lt);
            request.getRequestDispatcher("Vista/ListarProduccion.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");

            // Esta línea es igual a 3 lineas que están en listar la información registrada
            List<Produccion> lt = DaoProduccion.listar();
            request.setAttribute("listaProduccion", lt);
            request.getRequestDispatcher("Vista/ListarProduccion.jsp").forward(request, response);
        }
    }

    private void listarProd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
            List<Produccion> lt = DaoProduccion.listar();
            request.setAttribute("listaProduccion", lt);
            request.getRequestDispatcher("Vista/ListarProduccion.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarProduccion.jsp").forward(request, response);
        }
    }


    private void editarProd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            ide = Integer.parseInt(request.getParameter("id"));
            Produccion p = DaoProduccion.obtenerProduccionPorId(ide);
            request.setAttribute("User", p);

            List<Produccion> lt = DaoProduccion.listar();
            request.setAttribute("listaProduccion", lt);
            request.getRequestDispatcher("Vista/ListarProduccion.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarProd(request, response);
        }
    }

    private void actualizarProd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Utilizar la variable de instancia para obtener el ID
            Produccion p = DaoProduccion.obtenerProduccionPorId(ide);
            request.setAttribute("User", p);

            Produccion produccion = new Produccion();

            produccion.setVacaId(Integer.parseInt(request.getParameter("txtVaca")));
            produccion.setFecha(request.getParameter("txtFecha"));
            produccion.setProducido1(Double.valueOf(request.getParameter("txtProdu1")));
            produccion.setProducido2(Double.valueOf(request.getParameter("txtProdu2")));
            produccion.setTotalproduc(Double.valueOf(request.getParameter("txtTotal")));
           produccion.setObservaciones(request.getParameter("txtobserva"));
            produccion.setIdProduccion(ide); 

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoProduccion.actualizar(produccion);

             if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Registro");
            }

            listarProd(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarProd(request, response);
        }
    }


    private void buscarProd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String texto = request.getParameter("txtbuscar");
            // Validar el texto de búsqueda
            if (texto == null || texto.isEmpty()) {
                request.setAttribute("mensaje", "Error al buscar los Consecutivos, validar campos ingresados");
                listarProd(request, response);
                return;
            }

            List<Produccion> lt = DaoProduccion.buscarProduccion(texto);
            request.setAttribute("listaProduccion", lt);
            request.getRequestDispatcher("Vista/ListarProduccion.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarProduccion.jsp").forward(request, response);
        }
    }

    private void eliminarProd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idproduccion = Integer.parseInt(request.getParameter("id"));

            if (DaoProduccion.eliminar(idproduccion)) {
                request.setAttribute("mensaje", "La produccion fue Eliminada Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el Produccion");
            }

            listarProd(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarProd(request, response);
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
