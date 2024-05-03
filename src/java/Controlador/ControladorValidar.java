package Controlador;

import DaoPersistencia.DaoUsuario;
import Modelo.Usuario;

//import Persistencia.Encriptador;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorValidar extends HttpServlet {

    DaoUsuario daouser = new DaoUsuario();

    // private Encriptador encriptador = new Encriptador();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        try {
            if (accion != null && accion.equalsIgnoreCase("Ingresar")) {
                String usuario = request.getParameter("txtUser");
                String clave = request.getParameter("txtclave");

                // Validar que ambos usuario y clave no sean nulos o vacíos
                if (usuario != null && clave != null && !usuario.isEmpty() && !clave.isEmpty()) {
                    // Autenticar al usuario
                    Usuario user = daouser.validarUsuario(usuario, clave);

                    if (user != null && user.getUsuario() != null) {
                        // Almacenar el usuario en la sesión
                        HttpSession session = request.getSession();
                        session.setAttribute("usuario", user);

                        // Redirigir al usuario según su perfil
                        switch (user.getPerfilId()) {
                            case 1:
                            case 2:
                                response.sendRedirect("index2.jsp");
                                break;                           
                            // Manejar otros perfiles o casos aquí
                            default:
                                 response.sendRedirect("index3.jsp");
                                break;
                        }
                        return;
                    } else {
                        // Usuario válido pero contraseña incorrecta
                        String errorMensaje = "Usuario y/o Contraseña incorrecta. Por favor, intenta de nuevo.";
                        request.setAttribute("errorMensaje", errorMensaje);
                    }
                } else {
                    // Usuario o contraseña son nulos o vacíos
                    String errorMensaje = "Usuario o contraseña no pueden estar vacíos.";
                    request.setAttribute("errorMensaje", errorMensaje);
                }
            } else {
                // Acción no reconocida
                String errorMensaje = "Acción no reconocida.";
                request.setAttribute("errorMensaje", errorMensaje);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Puedes agregar un mensaje de error adicional si es necesario
        }
        // Si se llega a este punto, redirigir al formulario de inicio de sesión con mensaje de error
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
