package Controlador;

import DaoPersistencia.DaoCicloReproductivo;
import Modelo.CicloReproductivo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorCicloReproductivo extends HttpServlet {

    CicloReproductivo user = new CicloReproductivo();

    int ide;       // Variable de instancia Global para almacenar el ID

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
                registrarCiclo(request, response);
                break;

            case "listar":
                listarCiclo(request, response);
                break;

            case "editar":    // listar para editar
                editarCiclo(request, response);
                break;

            case "actualizar":
                actualizarCiclo(request, response);
                break;

            case "buscar":
                buscarCiclo(request, response);
                break;

            case "eliminar":
                eliminarCiclo(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    // Metodos para procesar a informacion enviada desde el formulario (Vista)
    private void registrarCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            CicloReproductivo cicloreproductivo = new CicloReproductivo();

            cicloreproductivo.setVacaId(Integer.parseInt(request.getParameter("txtVaca")));
            cicloreproductivo.setProveedorId(Integer.parseInt(request.getParameter("txtProv")));
            cicloreproductivo.setFechainseminacion(request.getParameter("txtFechaIn"));
            cicloreproductivo.setToro(request.getParameter("txtToro"));
            cicloreproductivo.setFechaParto(request.getParameter("txtFechaPa"));
            cicloreproductivo.setNumeroCrias(Integer.parseInt(request.getParameter("txtCrias")));
            cicloreproductivo.setSexoCria(request.getParameter("txtSexo"));
            cicloreproductivo.setObservaciones(request.getParameter("txobserva"));

            if (DaoCicloReproductivo.grabar(cicloreproductivo)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro no fue registrado, validar campos ingresados");
            }

            //Listar la información registrada en el formulario
            List<CicloReproductivo> lt = DaoCicloReproductivo.listar();
            request.setAttribute("listaCicloReproductivo", lt);
            request.getRequestDispatcher("Vista/ListarCicloReproductivo.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");

            // Esta línea es igual a 3 lineas que están en listar la información registrada
            List<CicloReproductivo> lt = DaoCicloReproductivo.listar();
            request.setAttribute("listaCicloReproductivo", lt);
            request.getRequestDispatcher("Vista/ListarCicloReproductivo.jsp").forward(request, response);
        }
    }

    private void listarCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<CicloReproductivo> lt = DaoCicloReproductivo.listar();
            request.setAttribute("listaCicloReproductivo", lt);
            request.getRequestDispatcher("Vista/ListarCicloReproductivo.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarCicloReproductivo.jsp").forward(request, response);
        }
    }

    private void editarCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ide = Integer.parseInt(request.getParameter("id"));
            CicloReproductivo c = DaoCicloReproductivo.obtenerCicloReproductivoPorId(ide);
            request.setAttribute("User", c);

            List<CicloReproductivo> lt = DaoCicloReproductivo.listar();
            request.setAttribute("listaCicloReproductivo", lt);
            request.getRequestDispatcher("Vista/ListarCicloReproductivo.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarCiclo(request, response);
        }
    }

    private void actualizarCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Utilizar la variable de instancia para obtener el ID
            CicloReproductivo c = DaoCicloReproductivo.obtenerCicloReproductivoPorId(ide);
            request.setAttribute("User", c);

            CicloReproductivo cicloreproductivo = new CicloReproductivo();

            cicloreproductivo.setVacaId(Integer.parseInt(request.getParameter("txtVaca")));
            cicloreproductivo.setProveedorId(Integer.parseInt(request.getParameter("txtProv")));
            cicloreproductivo.setFechainseminacion(request.getParameter("txtFechaIn"));
            cicloreproductivo.setToro(request.getParameter("txtToro"));
            cicloreproductivo.setFechaParto(request.getParameter("txtFechaPa"));
            cicloreproductivo.setNumeroCrias(Integer.parseInt(request.getParameter("txtCrias")));
            cicloreproductivo.setSexoCria(request.getParameter("txtSexo"));
            cicloreproductivo.setObservaciones(request.getParameter("txobserva"));            
            cicloreproductivo.setIdCicloReproductivo(ide); 

                  
            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoCicloReproductivo.actualizar(cicloreproductivo);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Registro");
            }

            listarCiclo(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarCiclo(request, response);
        }
    }

    private void buscarCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String texto = request.getParameter("txtbuscar");
            // Validar el texto de búsqueda
            if (texto == null || texto.isEmpty()) {
                request.setAttribute("mensaje", "Error al buscar los Consecutivos, validar campos ingresados");
                listarCiclo(request, response);
                return;
            }

            List<CicloReproductivo> lt = DaoCicloReproductivo.buscarCicloReproductivo(texto);
            request.setAttribute("listaCicloReproductivo", lt);
            request.getRequestDispatcher("Vista/ListarCicloReproductivo.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarCicloReproductivo.jsp").forward(request, response);
        }
    }

    private void eliminarCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idcicloreproductivo = Integer.parseInt(request.getParameter("id"));

            if (DaoCicloReproductivo.eliminar(idcicloreproductivo)) {
                request.setAttribute("mensaje", "Registro fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el registro");
            }

            listarCiclo(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarCiclo(request, response);
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
