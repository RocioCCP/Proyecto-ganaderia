package Controlador;

import DaoPersistencia.DaoHistoriaClinica;
import Modelo.HistoriaClinica;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorHistoriaClinica extends HttpServlet {

    HistoriaClinica user = new HistoriaClinica();

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
                registrarHistoria(request, response);
                break;

            case "listar":
                listarHistoria(request, response);
                break;

            case "editar":    // listar para editar
                editarHistoria(request, response);
                break;

            case "actualizar":
                actualizarHistoria(request, response);
                break;

            case "buscar":
                buscarHistoria(request, response);
                break;

            case "eliminar":
                eliminarHistoria(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    // Metodos para procesar a informacion enviada desde el formulario (Vista)
    private void registrarHistoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            HistoriaClinica historiaclinica = new HistoriaClinica();

            historiaclinica.setVacaId(Integer.parseInt(request.getParameter("txtVaca")));
            historiaclinica.setMedicamentosId(Integer.parseInt(request.getParameter("txtMedica")));
            historiaclinica.setProcedimientosId(Integer.parseInt(request.getParameter("txtProced")));
            historiaclinica.setFechaproceso(request.getParameter("txFechaProc"));
            historiaclinica.setRecomendaciones(request.getParameter("txRecomenda"));
          
            if (DaoHistoriaClinica.grabar(historiaclinica)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro no fue registrado, validar campos ingresados");
            }

            // Listar la información registrada en el formulario
            List<HistoriaClinica> lt = DaoHistoriaClinica.listar();
            request.setAttribute("listaHistoriaClinica", lt);
            request.getRequestDispatcher("Vista/ListarHistoriaClinica.jsp").forward(request, response);

       } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");

            // Esta línea es igual a 3 lineas que están en listar la información registrada
            List<HistoriaClinica> lt = DaoHistoriaClinica.listar();
            request.setAttribute("listaHistoriaClinica", lt);
            request.getRequestDispatcher("Vista/ListarHistoriaClinica.jsp").forward(request, response);
        }
    }

    private void listarHistoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<HistoriaClinica> lt = DaoHistoriaClinica.listar();
            request.setAttribute("listaHistoriaClinica", lt);
            request.getRequestDispatcher("Vista/ListarHistoriaClinica.jsp").forward(request, response);
          } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarHistoriaClinica.jsp").forward(request, response);
        }
    }

    private void editarHistoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             ide = Integer.parseInt(request.getParameter("id"));
            HistoriaClinica h = DaoHistoriaClinica.obtenerHistoriaClinicaPorId(ide);
            request.setAttribute("User", h);

            List<HistoriaClinica> lt = DaoHistoriaClinica.listar();
            request.setAttribute("listaHistoriaClinica", lt);
            request.getRequestDispatcher("Vista/ListarHistoriaClinica.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarHistoria(request, response);
        }
    }
     
    private void actualizarHistoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Utilizar la variable de instancia para obtener el ID
            HistoriaClinica h = DaoHistoriaClinica.obtenerHistoriaClinicaPorId(ide);
            request.setAttribute("User", h);
                     
            HistoriaClinica historiaclinica = new HistoriaClinica();

            historiaclinica.setVacaId(Integer.parseInt(request.getParameter("txtVaca")));
            historiaclinica.setMedicamentosId(Integer.parseInt(request.getParameter("txtMedica")));
            historiaclinica.setProcedimientosId(Integer.parseInt(request.getParameter("txtProced")));
            historiaclinica.setFechaproceso(request.getParameter("txFechaProc"));
            historiaclinica.setRecomendaciones(request.getParameter("txRecomenda"));
            historiaclinica.setIdHistoriaClinica(ide);

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoHistoriaClinica.actualizar(historiaclinica);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Registro");
            }

            listarHistoria(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarHistoria(request, response);
        }
    }

    private void buscarHistoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String texto = request.getParameter("txtbuscar");
            // Validar el texto de búsqueda
            if (texto == null || texto.isEmpty()) {
                request.setAttribute("mensaje", "Error al buscar los Consecutivos, validar campos ingresados");
                listarHistoria(request, response);
                return;
            }

            List<HistoriaClinica> lt = DaoHistoriaClinica.buscarHistoriaClinica(texto);
            request.setAttribute("listaHistoriaClinica", lt);
            request.getRequestDispatcher("Vista/ListarHistoriaClinica.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarHistoriaClinica.jsp").forward(request, response);
        }
    }

    private void eliminarHistoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idHistoriaClinica = Integer.parseInt(request.getParameter("id"));

              if (DaoHistoriaClinica.eliminar(idHistoriaClinica)) {
                request.setAttribute("mensaje", "Registro fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el registro");
            }

            listarHistoria(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarHistoria(request, response);
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


   