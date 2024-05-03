package Controlador;
 
import DaoPersistencia.DaoUsuario;
import Modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorUsuario extends HttpServlet {

    Usuario user = new Usuario();

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
                registrarUsuario(request, response);
                break;

            case "listar":
                listarUsuario(request, response);
                break;

            case "editar":    // listar para editar
                editarUsuario(request, response);
                break;

            case "actualizar":
                actualizarUsuario(request, response);
                break;

            case "buscar":
                  buscarUsuario(request, response);
                break;

            case "eliminar":
                eliminarUsuario(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    // Metodos para procesar a informacion enviada desde el formulario (Vista)
    
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            int Perfil = Integer.parseInt(request.getParameter("txtIdperfil"));
            int TipoDoc = Integer.parseInt(request.getParameter("txtIdTipoDoc"));
            String numdoc = request.getParameter("txtNumeroDoc");
            String nombres = request.getParameter("txtNombre");
            String apellido = request.getParameter("txtApellido");
            String correo = request.getParameter("txtEmail");
            int telefono = Integer.parseInt(request.getParameter("txtTelefono"));
            String usuar = request.getParameter("txtUser");
            String clave = request.getParameter("txtclave");
            
           Usuario usuario = new Usuario();

            usuario.setPerfilId(Perfil);
            usuario.setTipodocumentoId(TipoDoc);
            usuario.setNumDocumento(numdoc);
            usuario.setNombres(nombres);
            usuario.setApellidos(apellido);
            usuario.setEmail(correo);
            usuario.setTelefono(telefono);
            usuario.setUsuario(usuar);
            usuario.setClave(clave);
                    

            if (DaoUsuario.grabar(usuario)) {
                request.setAttribute("mensaje", "Registro Exitoso");
            } else {
                request.setAttribute("mensaje", "Registro  no fue registrado, validar campos ingresados");
            }
            
//Listar la informacion registrada en el formulario

            List<Usuario> lt = DaoUsuario.listar();
            request.setAttribute("listaUsuario", lt);
            request.getRequestDispatcher("Vista/ListarUsuario.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
          
// Esta linea es igual a 3 lineas que estan en listar la informacion registrada
           listarUsuario(request, response);
        }
    }

   private void listarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           List<Usuario> lt = DaoUsuario.listar();
            request.setAttribute("listaUsuario", lt);
            request.getRequestDispatcher("Vista/ListarUsuario.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
             request.getRequestDispatcher("Vista/ListarUsuario.jsp").forward(request, response);
        }
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia ide
            ide = Integer.parseInt(request.getParameter("id"));    //Esta variable ya vale algo ?       

            Usuario u = DaoUsuario.obtenerUsuarioPorId(ide);
            request.setAttribute("User", u);

            List<Usuario> lt = DaoUsuario.listar();
            request.setAttribute("listaUsuario", lt);
            request.getRequestDispatcher("Vista/ListarUsuario.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarUsuario(request, response);
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Utilizar la variable de instancia para obtener el ID
            Usuario u = DaoUsuario.obtenerUsuarioPorId(ide);
            request.setAttribute("User", u);

            // Resto del código para actualizar el Consecutivo
            
            int Perfil = Integer.parseInt(request.getParameter("txtIdperfil"));
            int TipoDoc = Integer.parseInt(request.getParameter("txtIdTipoDoc"));
            String numdoc = request.getParameter("txtNumeroDoc");
            String nombres = request.getParameter("txtNombre");
            String apellido = request.getParameter("txtApellido");
            String correo = request.getParameter("txtEmail");
            int telefono = Integer.parseInt(request.getParameter("txtTelefono"));
            String usuar = request.getParameter("txtUser");
            String clave = request.getParameter("txtclave");
            
           //Creamos un Objeto de tipo Usuario
            Usuario usuario = new Usuario();

            // Cargamos el Objeto de tipo Usuario con la informacion que viene del formulario
            
            usuario.setPerfilId(Perfil);
            usuario.setTipodocumentoId(TipoDoc);
            usuario.setNumDocumento(numdoc);
            usuario.setNombres(nombres);
            usuario.setApellidos(apellido);
            usuario.setEmail(correo);
            usuario.setTelefono(telefono);
            usuario.setUsuario(usuar);
            usuario.setClave(clave);
            usuario.setIdUsuario(ide);   // Utilizar la variable de instancia para obtener el ID del Usuario

            // El id se le pasa directamentecon la variable que ya capturo el id
            boolean actualizacionExitosa = DaoUsuario.actualizar(usuario);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Usuario actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Resgistro");
            }

            List<Usuario> lt = DaoUsuario.listar();
            request.setAttribute("listaUsuario", lt);
            request.getRequestDispatcher("Vista/ListarUsuario.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarUsuario(request, response);
        }
    }

       private void buscarUsuario(HttpServletRequest request, HttpServletResponse response)    /// en barra de //
            throws ServletException, IOException {
        try {
           String texto = request.getParameter("txtbuscar");
            List<Usuario> lt = DaoUsuario.buscarUsuario(texto);
           request.setAttribute("listaUsuario", lt);
            request.getRequestDispatcher("Vista/ListarUsuario.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListarUsuario.jsp").forward(request, response);
        }
    }
    
    
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idUsuarios = Integer.parseInt(request.getParameter("id"));

            if (DaoUsuario.eliminar(idUsuarios)) {
                request.setAttribute("mensaje", "El Usuario fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el usuario");
            }

            listarUsuario(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarUsuario(request, response);
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
