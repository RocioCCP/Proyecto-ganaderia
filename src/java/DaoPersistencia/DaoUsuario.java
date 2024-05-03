package DaoPersistencia;

import Conectar.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUsuario {

    static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar); 
    public static boolean grabar(Usuario usuario) {
        boolean resultado = false;
        try {
            con = conetor.conectar();
            String sql = "INSERT INTO usuario(perfiId, tipodocumentoId,numDocumento, " // 3
                    + "nombres, apellidos, email, " // 3
                    + "telefono, usuario, clave) " //3  = 3
                    + "VALUES(?,?,?,?,?,?,?,?,?);";

            ps = con.prepareStatement(sql);

            ps.setInt(1, usuario.getPerfilId());
            ps.setInt(2, usuario.getTipodocumentoId());
            ps.setString(3, usuario.getNumDocumento());
            ps.setString(4, usuario.getNombres());
            ps.setString(5, usuario.getApellidos());
            ps.setString(6, usuario.getEmail());
            ps.setInt(7, usuario.getTelefono());
            ps.setString(8, usuario.getUsuario());
            ps.setString(9, usuario.getClave());

            if (ps.executeUpdate() > 0) {
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (Exception e) {
            System.out.println("Error al grabar informe: " + e.getMessage());
        } finally {
            cerrarRecursos();
            System.out.println("Recursos cerrados correctamente");
        }
        return resultado;
    }

    //Metodo para listar y visualizar en un tabla
    public static List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT * FROM usuario;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setPerfilId(rs.getInt("perfiId"));
                usuario.setTipodocumentoId(rs.getInt("tipodocumentoId"));
                usuario.setNumDocumento(rs.getString("numDocumento"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getInt("telefono"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setClave(rs.getString("clave"));

                lista.add(usuario);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Metodo me permite Listar para luego Editar   
    public static Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE idUsuario=?";

        try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuario = new Usuario();

                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setPerfilId(rs.getInt("perfiId"));
                    usuario.setTipodocumentoId(rs.getInt("tipodocumentoId"));
                    usuario.setNumDocumento(rs.getString("numDocumento"));
                    usuario.setNombres(rs.getString("nombres"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefono(rs.getInt("telefono"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setClave(rs.getString("clave"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return usuario;
    }

    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps    
    public static boolean actualizar(Usuario usuario) {
        try {
            con = conetor.conectar();
            String sql = "UPDATE usuario set perfiId = ?, tipodocumentoId= ?, numDocumento= ?, "
                    + " nombres = ?, apellidos = ?, email = ?, telefono = ?, "
                    + "usuario = ?, clave = ? WHERE idUsuario =?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, usuario.getPerfilId());
            ps.setInt(2, usuario.getTipodocumentoId());
            ps.setString(3, usuario.getNumDocumento());
            ps.setString(4, usuario.getNombres());
            ps.setString(5, usuario.getApellidos());
            ps.setString(6, usuario.getEmail());
            ps.setInt(7, usuario.getTelefono());
            ps.setString(8, usuario.getUsuario());
            ps.setString(9, usuario.getClave());
            ps.setInt(10, usuario.getIdUsuario());

            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

///    metodo de Buscar(Consultar) verificar por nombre o x otro dato en la barra de busqueda
    public static List<Usuario> buscarUsuario(String texto) {
        List<Usuario> listausuarios = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM usuario "
                    + "WHERE numDocumento LIKE ? "
                    + "OR nombres LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setPerfilId(rs.getInt("perfiId"));
                usuario.setTipodocumentoId(rs.getInt("tipodocumentoId"));
                usuario.setNumDocumento(rs.getString("numDocumento"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getInt("telefono"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setClave(rs.getString("clave"));

                listausuarios.add(usuario);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listausuarios;
    }

    public static boolean eliminar(int idUsuario) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM usuario WHERE idUsuario=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja las excepciones de mejor manera, por ejemplo, lanzando una excepción personalizada.
        } finally {
            cerrarRecursos();
        }
        return false;
    }

    /// Metodo para Obtener  datos de la BD  que necesite mostrar en otra clase (usuario) Captura una informacion nombre y lo muestra en otra tabla
    public static String obtenerunNombreUsuario(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();

            String sql = "SELECT  nombres  FROM usuario WHERE idUsuario=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombres");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }

    //metodo jasypt para encriptar contraseña
    public Usuario validarUsuario(String usuario, String clave) {
        Usuario user = null;
        //Encriptador en = new Encriptador();

        String sql = "SELECT * FROM usuario WHERE usuario=? AND clave=?";

        try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            //  ps.setString(2, hashClave(clave)); // Utiliza una función hash para almacenar las contraseñas de manera segura            
            //   ps.setString(2, (en.desencriptar(clave)));
            ps.setString(2, clave);
            // ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new Usuario();                   
                    
                    user.setIdUsuario(rs.getInt("idUsuario"));
                    user.setClave(rs.getString("clave"));
                    user.setUsuario(rs.getString("usuario"));
                    user.setPerfilId(rs.getInt("perfiId"));

          
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Puedes agregar un mensaje de error adicional si es necesario
        } finally {
            cerrarRecursos(); // Asegúrate de cerrar los recursos de manera correcta
        }
        return user;

    }

    // Agrega este método para cerrar las conexiones y recursos
    private static void cerrarRecursos() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
