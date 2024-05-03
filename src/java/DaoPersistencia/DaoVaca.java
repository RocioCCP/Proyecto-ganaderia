package DaoPersistencia;

import Conectar.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.Vaca;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoVaca {

    static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar); 
    public static boolean grabar(Vaca vaca) {
        try {
            con = conetor.conectar();
            String sql = "INSERT INTO vaca(usuarioId, numPlaca, nombreVaca, " // 3
                    + "fechanacimiento, madre, padre) " // 3
                    + "VALUES(?,?,?,?,?,?);";

            ps = con.prepareStatement(sql);

            ps.setInt(1, vaca.getUsuarioId());
            ps.setString(2, vaca.getNumPlaca());
            ps.setString(3, vaca.getNombreVaca());
            ps.setString(4, vaca.getFechanacimiento());
            ps.setString(5, vaca.getMadre());
            ps.setString(6, vaca.getPadre());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al grabar vaca: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    //Metodo para listar y visualizar en un tabla
    public static List<Vaca> listar() {
        List<Vaca> lista = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT * FROM vaca;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vaca vaca = new Vaca();

                vaca.setIdVaca(rs.getInt("idVaca"));
                vaca.setUsuarioId(rs.getInt("usuarioId"));
                vaca.setNumPlaca(rs.getString("numPlaca"));
                vaca.setNombreVaca(rs.getString("nombreVaca"));
                vaca.setFechanacimiento(rs.getString("fechanacimiento"));
                vaca.setMadre(rs.getString("madre"));
                vaca.setPadre(rs.getString("padre"));

                lista.add(vaca);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Metodo me permite Listar para luego Editar   
    public static Vaca obtenerVacaPorId(int id) {
        Vaca vaca = null;

        String sql = "SELECT * FROM vaca WHERE idVaca=?";

        try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vaca = new Vaca();

                    vaca.setIdVaca(rs.getInt("idVaca"));
                    vaca.setUsuarioId(rs.getInt("usuarioId"));
                    vaca.setNumPlaca(rs.getString("numPlaca"));
                    vaca.setNombreVaca(rs.getString("nombreVaca"));
                    vaca.setFechanacimiento(rs.getString("fechanacimiento"));
                    vaca.setMadre(rs.getString("madre"));
                    vaca.setPadre(rs.getString("padre"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoVaca.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return vaca;
    }

    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps    
    public static boolean actualizar(Vaca vaca) {
        try {
            con = conetor.conectar();
            String sql = "UPDATE vaca set usuarioId = ?, numPlaca= ?, nombreVaca= ?, "
                    + " fechanacimiento= ?, madre = ?, padre = ? WHERE idVaca =?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, vaca.getUsuarioId());
            ps.setString(2, vaca.getNumPlaca());
            ps.setString(3, vaca.getNombreVaca());
            ps.setString(4, vaca.getFechanacimiento());
            ps.setString(5, vaca.getMadre());
            ps.setString(6, vaca.getPadre());
            ps.setInt(7, vaca.getIdVaca());

            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoVaca.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

///    metodo de Buscar(Consultar) verificar por nombre o x otro dato en la barra de busqueda
    public static List<Vaca> buscarVaca(String texto) {
        List<Vaca> listavaca = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM vaca "
                    + "WHERE numPlaca LIKE ? "
                    + "OR nombreVaca LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Vaca vaca = new Vaca();

                vaca.setIdVaca(rs.getInt("idVaca"));
                vaca.setUsuarioId(rs.getInt("usuarioId"));
                vaca.setNumPlaca(rs.getString("numPlaca"));
                vaca.setNombreVaca(rs.getString("nombreVaca"));
                vaca.setFechanacimiento(rs.getString("fechanacimiento"));
                vaca.setMadre(rs.getString("madre"));
                vaca.setPadre(rs.getString("padre"));

                listavaca.add(vaca);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listavaca;
    }

    public static List<String> obtNombreporPlaca() {
        List<String> nombresPlacas = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT CONCAT(v.numPlaca, ' - ', v.nombreVaca) AS NombrePlaca FROM vaca v;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombresPlacas.add(rs.getString("NombrePlaca"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoVaca.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos();
        }
        return nombresPlacas;
    }

    public static boolean eliminar(int idVaca) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM vaca WHERE idVaca=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVaca);

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

    /// Metodo para Obtener  datos de la BD  que necesite mostrar en otra clase (usuario) Captura una informacion nombre y lo muestra en otra tabla
    public static String obtenerunNombreVaca(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();
            String sql = "SELECT  nombreVaca  FROM vaca WHERE idVaca=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombreVaca");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoVaca.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }

    public static String obtenerunPlacaVacaporid(int id) {
        try {
            con = conetor.conectar();
            String sql = "SELECT numPlaca FROM vaca WHERE idVaca=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("numPlaca");
            } else {
                return "--";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoVaca.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos();
        }
        return "--";
    }

    public static String obtenerunNombreVacaporid(int id) {
        try {
            con = conetor.conectar();
            String sql = "SELECT nombreVaca FROM vaca WHERE idVaca=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombreVaca");
            } else {
                return "--";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoVaca.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos();
        }
        return "--";
    }

}
