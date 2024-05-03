package Controlador;
 
import DaoPersistencia.DaoProveedor;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Proveedor;

public class ControladorProveedor extends HttpServlet {

 Proveedor user = new Proveedor();

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
                registrarProveedor(request, response);
                break;

            case "listar":
                listarProveedor(request, response);
                break;

            case "editar":    // listar para editar
                editarProveedor(request, response);
                break;

            case "actualizar":
                actualizarProveedor(request, response);
                break;

            case "buscar":
                  buscarProveedor(request, response);
                break;

            case "eliminar":
                eliminarProveedor(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    // Metodos para procesar a informacion enviada desde el formulario (Vista)
    
    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
         Proveedor proveedor = new Proveedor();

            proveedor.setCiudadId(Integer.parseInt(request.getParameter("txtIdciudad")));
            proveedor.setNomProveedor(request.getParameter("txtProveedor"));
            proveedor.setTelefono(Integer.parseInt(request.getParameter("txtTelefono")));
            proveedor.setCorreo(request.getParameter("txtCorreo"));
            proveedor.setNomToro(request.getParameter("txttoror"));
            
            if (DaoProveedor.grabar(proveedor)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            
            
//Listar la informacion registrada en el formulario

            List<Proveedor> lt = DaoProveedor.listar();
            request.setAttribute("listaProveedor", lt);
            request.getRequestDispatcher("Vista/ListarProveedor.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
          
  // Esta línea es igual a 3 lineas que están en listar la información registrada
            List<Proveedor> lt = DaoProveedor.listar();
            request.setAttribute("listaProveedor", lt);
            request.getRequestDispatcher("Vista/ListarProveedor.jsp").forward(request, response);
        }
    }

    private void listarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Proveedor> lt = DaoProveedor.listar();
            request.setAttribute("listaProveedor", lt);
            request.getRequestDispatcher("Vista/ListarProveedor.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarProveedor.jsp").forward(request, response);
        }
    }

    private void editarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            Proveedor pv = DaoProveedor.obtenerProveedorPorId(ide);
            request.setAttribute("User", pv);

            List<Proveedor> lt = DaoProveedor.listar();
            request.setAttribute("listaProveedor", lt);
            request.getRequestDispatcher("Vista/ListarProveedor.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarProveedor(request, response);
        }
    }

  private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Utilizar la variable de instancia para obtener el ID
            Proveedor pv = DaoProveedor.obtenerProveedorPorId(ide);
            request.setAttribute("User", pv);

            Proveedor proveedor = new Proveedor();

            // Cargamos el Objeto de tipo Usuario con la informacion que viene del formulario
            System.out.println("ID Ciudad: " + request.getParameter("txtIdciudad"));
            System.out.println("Nombre Proveedor: " + request.getParameter("txtProveedor"));
            System.out.println("Teléfono: " + request.getParameter("txtTelefono"));
            System.out.println("Correo: " + request.getParameter("txtCorreo"));
            System.out.println("Nombre Toro: " + request.getParameter("txttoror"));
            System.out.println("ID Proveedor: " + ide);

            proveedor.setCiudadId(Integer.parseInt(request.getParameter("txtIdciudad")));
            proveedor.setNomProveedor(request.getParameter("txtProveedor"));
            proveedor.setTelefono(Integer.parseInt(request.getParameter("txtTelefono")));
            proveedor.setCorreo(request.getParameter("txtCorreo"));
            proveedor.setNomToro(request.getParameter("txttoror"));
            proveedor.setIdProveedor(ide);      

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoProveedor.actualizar(proveedor);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Proveedor actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Proveedor");
            }
          
            listarProveedor(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarProveedor(request, response);
        }
    }


       private void buscarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
            String texto = request.getParameter("txtbuscar");
            // Validar el texto de búsqueda
            if (texto == null || texto.isEmpty()) {
                request.setAttribute("mensaje", "Error al buscar los Consecutivos, validar campos ingresados");
                listarProveedor(request, response);
                return;
            }

            List<Proveedor> lt = DaoProveedor.buscarProveedor(texto);
            request.setAttribute("listaProveedor", lt);
            request.getRequestDispatcher("Vista/ListarProveedor.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vista/ListarProveedor.jsp").forward(request, response);
        }
    }
   
    
    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idproveedor = Integer.parseInt(request.getParameter("id"));

            if (DaoProveedor.eliminar(idproveedor)) {
                request.setAttribute("mensaje", "El proveedor fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el Proveedor");
            }

            listarProveedor(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarProveedor(request, response);
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
