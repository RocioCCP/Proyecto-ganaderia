package DaoPersistencia;

import Conectar.Conexion;
import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoProveedor {

    static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar o grabar en tabla); 
    public static boolean grabar(Proveedor proveedor) {
        boolean resultado = false;
        try {
            con = conetor.conectar();
            String sql = "INSERT INTO proveedor(ciudadId, nomProveedor, telefono, " // 3
                    + "correo, nomToro) " // 2
                    + "VALUES(?,?,?,?,?);";

            ps = con.prepareStatement(sql);

            ps.setInt(1, proveedor.getCiudadId());
            ps.setString(2, proveedor.getNomProveedor());
            ps.setInt(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getCorreo());
            ps.setString(5, proveedor.getNomToro());

            if (ps.executeUpdate() > 0) {
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (Exception e) {
            System.out.println("Error al grabar el proveedor: " + e.getMessage());
        } finally {
            cerrarRecursos();
            System.out.println("Recursos cerrados correctamente");
        }
        return resultado;
    }

    //Metodo para listar y visualizar en un tabla
    public static List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT * FROM proveedor;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();

                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setCiudadId(rs.getInt("ciudadId"));
                proveedor.setNomProveedor(rs.getString("nomProveedor"));
                proveedor.setTelefono(rs.getInt("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setNomToro(rs.getString("nomToro"));

                lista.add(proveedor);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Metodo me permite Listar para luego Editar  
    public static Proveedor obtenerProveedorPorId(int id) {
        Proveedor proveedor = null;

        String sql = "SELECT * FROM proveedor WHERE idProveedor=?";

        try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    proveedor = new Proveedor();

                    proveedor.setIdProveedor(rs.getInt("idProveedor"));
                    proveedor.setCiudadId(rs.getInt("ciudadId"));
                    proveedor.setNomProveedor(rs.getString("nomProveedor"));
                    proveedor.setTelefono(rs.getInt("telefono"));
                    proveedor.setCorreo(rs.getString("correo"));
                    proveedor.setNomToro(rs.getString("nomToro"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoProveedor.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return proveedor;

    }

    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps   
    //Metodo me actualizar    
    public static boolean actualizar(Proveedor proveedor) {
        try {
            con = conetor.conectar();
            String sql = "UPDATE proveedor SET ciudadId = ?, nomProveedor = ?, telefono = ?, "
                    + "correo = ?, nomToro=? WHERE idProveedor = ?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, proveedor.getCiudadId());
            ps.setString(2, proveedor.getNomProveedor());
            ps.setInt(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getCorreo());
            ps.setString(5, proveedor.getNomToro());
            ps.setInt(6, proveedor.getIdProveedor());

            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoProveedor.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

    //. metodo de Buscar(Consultar) verificar por nombre o x otro barra de busqueda
    public static List<Proveedor> buscarProveedor(String texto) {
        List<Proveedor> listaproveedor = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM proveedor  WHERE nomProveedor LIKE ? ";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();

                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setCiudadId(rs.getInt("ciudadId"));
                proveedor.setNomProveedor(rs.getString("nomProveedor"));
                proveedor.setTelefono(rs.getInt("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setNomToro(rs.getString("nomToro"));

                listaproveedor.add(proveedor);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaproveedor;
    }

    //. Metodo para Obtener  datos de la BD  que necesite mostrar en otra clase (usuario) Captura una informacion nombre y lo muestra en otra tabla
    public static String obteneunNombreProveedor(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();

            String sql = "SELECT nomProveedor FROM proveedor WHERE idProveedor=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nomProveedor");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProveedor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }

    // Metodo Elimar registro
    public static boolean eliminar(int idProveedor) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM proveedor WHERE idProveedor=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedor);

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
    public static String obtenerunNombreProveedor(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();
            String sql = "SELECT  nomProveedor  FROM proveedor WHERE idProveedor=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nomProveedor");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoVaca.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }

    /// Metodo para Obtener  datos de la BD  que necesite mostrar en otra clase (usuario) Captura una informacion nombre y lo muestra en otra tabla
    public static String obtenerunNombretoroProveedor(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();
            String sql = "SELECT  nomToro  FROM proveedor WHERE idProveedor=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nomToro");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProveedor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }
}
