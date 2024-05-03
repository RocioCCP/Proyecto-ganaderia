/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DaoPersistencia.DaoMedicamentos;
import Modelo.Medicamentos;
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
public class ControladorMedicamentos extends HttpServlet {

 Medicamentos user = new Medicamentos();

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
                registrarMedica(request, response);
                break;

            case "listar":
                listarMedica(request, response);
                break;

            case "editar":    // listar para editar
                editarMedica(request, response);
                break;

            case "actualizar":
                actualizarMedica(request, response);
                break;

            case "buscar":
                  buscarMedica(request, response);
                break;

            case "eliminar":
                eliminarMedica(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
        
    }
    
   // Metodos para procesar a informacion enviada desde el formulario (Vista)
      
    private void registrarMedica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String medica = request.getParameter("txtMedicam");
            String labora = request.getParameter("txtLabora");          
            

            Medicamentos medicamentos = new Medicamentos();
           
            medicamentos.setMedicamento(medica);
            medicamentos.setLaboratorio(labora);

            if (DaoMedicamentos.grabar(medicamentos)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            //Listar la informacion registrada en el formulario

            List<Medicamentos> lt = DaoMedicamentos.listar();
            request.setAttribute("listaMedicamentos", lt);
            request.getRequestDispatcher("Vista/ListarMedicamentos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            
            // Esta linea es igual a 3 lineas que estan en listar la informacion registrada
            listarMedica(request, response);
        }
    }

    private void listarMedica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Medicamentos> lt = DaoMedicamentos.listar();
            request.setAttribute("listaMedicamentos", lt);
            request.getRequestDispatcher("Vista/ListarMedicamentos.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarMedicamentos.jsp").forward(request, response);
        }
    }

    private void editarMedica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            Medicamentos m = DaoMedicamentos.obtenerMedicamentosPorID(ide);
            
            request.setAttribute("User", m);

            List<Medicamentos> lt = DaoMedicamentos.listar();
            request.setAttribute("listaMedicamentos", lt);
            request.getRequestDispatcher("Vista/ListarMedicamentos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarMedica (request, response);
        }
    }

    private void actualizarMedica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Utilizar la variable de instancia para obtener el ID
            Medicamentos m = DaoMedicamentos.obtenerMedicamentosPorID(ide);
            request.setAttribute("User", m);

            // Resto del código para actualizar el Consecutivo
            
            String medica = request.getParameter("txtMedicam");
            String labora = request.getParameter("txtLabora");
                    
            //Creamos un Objeto de tipo  medficamentos
                        
          Medicamentos medicamentos = new  Medicamentos();

            // Cargamos el Objeto de tipo medicamentos con la informacion que viene del formulario
                            
           medicamentos.setMedicamento(medica);
           medicamentos.setLaboratorio(labora);
           medicamentos.setIdMedicamento(ide);   //Utilizar la variable de instancia para obtener el ID del medicamentos

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoMedicamentos.actualizar(medicamentos);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Medicamento actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Resgistro");
            }

            List<Medicamentos> lt = DaoMedicamentos.listar();
            request.setAttribute("listaMedicamentos", lt);
            request.getRequestDispatcher("Vista/ListarMedicamentos.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarMedica(request, response);
        }
    }

       private void buscarMedica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           String text = request.getParameter("txtbuscar");
            List<Medicamentos> lt = DaoMedicamentos.buscarMedicamentos(text);
           request.setAttribute("listaMedicamentos", lt);
            request.getRequestDispatcher("Vista/ListarMedicamentos.jsp").forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListarMedicamentos.jsp").forward(request, response);
        }
        
    }
    
        
private void eliminarMedica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMedicamento = Integer.parseInt(request.getParameter("id"));

            if (DaoMedicamentos.eliminar(idMedicamento)) {
                request.setAttribute("mensaje", "El Medicamneto fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el  Medicamento");
            }

             listarMedica(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
             listarMedica(request, response);
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
