
package DaoPersistencia;
import Conectar.Conexion;
import Modelo.Produccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DaoProduccion {

    static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar); 
 public static boolean grabar(Produccion produccion) {
    boolean resultado = false;
    try {
        con = conetor.conectar();
        String sql = "INSERT INTO produccion(vacaId, fecha, producido1, " // 3
                + "producido2, totalproduc, observaciones) " // 3  = 6
                + "VALUES(?,?,?,?,?,?);";

        ps = con.prepareStatement(sql);

        ps.setInt(1, produccion.getVacaId());
        ps.setString(2, produccion.getFecha());
        ps.setDouble(3, produccion.getProducido1());
        ps.setDouble(4, produccion.getProducido2());
        ps.setDouble(5, produccion.getTotalproduc());
        ps.setString(6, produccion.getObservaciones());

        if (ps.executeUpdate() > 0) {
            resultado = true;
        } else {
            resultado = false;
        }
    } catch (Exception e) {
        System.out.println("Error al grabar el produccion: " + e.getMessage());
    } finally {
        cerrarRecursos();
        System.out.println("Recursos cerrados correctamente");
    }
    return resultado;
}

// Metodo para listar y visualizar en un tabla
public static List<Produccion> listar() {
    List<Produccion> lista = new ArrayList<>();
    try {
        con = conetor.conectar();
        String sql = "SELECT * FROM produccion;"; // Corregido para seleccionar de la tabla "produccion"
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Produccion produccion = new Produccion();

            produccion.setIdProduccion(rs.getInt("idProduccion"));
            produccion.setVacaId(rs.getInt("vacaId"));
            produccion.setFecha(rs.getString("fecha"));
            produccion.setProducido1(rs.getDouble("producido1"));
            produccion.setProducido2(rs.getDouble("producido2"));
            produccion.setTotalproduc(rs.getDouble("totalproduc"));
            produccion.setObservaciones(rs.getString("observaciones"));

            lista.add(produccion);
        }
    } catch (Exception e) {
    } finally {
        cerrarRecursos();
    }
    return lista;
}

    //Metodo me permite Listar para luego Editar   
    public static Produccion obtenerProduccionPorId(int id) {
        Produccion produccion = null;

        String sql = "SELECT * FROM produccion WHERE idProduccion=?";

        try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    produccion = new Produccion();

                    produccion.setIdProduccion(rs.getInt("idProduccion"));
                    produccion.setVacaId(rs.getInt("vacaId"));
                    produccion.setFecha(rs.getString("fecha"));
                    produccion.setProducido1(rs.getDouble("producido1"));
                    produccion.setProducido2(rs.getDouble("producido2"));
                    produccion.setTotalproduc(rs.getDouble("totalproduc"));
                    produccion.setObservaciones(rs.getString("observaciones"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoProduccion.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return produccion;

    }

    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps    
    public static boolean actualizar(Produccion produccion) {
        try {
             con = conetor.conectar();
            String sql = "UPDATE produccion set vacaId=?, fecha=?, producido1=?, "
                    + " producido2=?, totalproduc=?, observaciones=? "
                    + " WHERE idProduccion=? ";

            ps = con.prepareStatement(sql);

            ps.setInt(1, produccion.getVacaId());
            ps.setString(2, produccion.getFecha());
            ps.setDouble(3, produccion.getProducido1());
            ps.setDouble(4, produccion.getProducido2());
            ps.setDouble(5, produccion.getTotalproduc());
            ps.setString(6, produccion.getObservaciones());
            ps.setInt(7, produccion.getIdProduccion());

            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoProduccion.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

///    metodo de Buscar(Consultar) verificar por nombre o x otro dato en la barra de busqueda
    public static List<Produccion> buscarProduccion(String texto) {
        List<Produccion> listaProduccion = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM produccion "
                    + "WHERE vacaId LIKE ? ";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Produccion produccion = new Produccion();

                produccion.setIdProduccion(rs.getInt("idProduccion"));
                produccion.setVacaId(rs.getInt("vacaId"));
                produccion.setFecha(rs.getString("fecha"));
                produccion.setProducido1(rs.getDouble("producido1"));
                produccion.setProducido2(rs.getDouble("producido2"));
                produccion.setTotalproduc(rs.getDouble("totalproduc"));
                produccion.setObservaciones(rs.getString("observaciones"));

                listaProduccion.add(produccion);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaProduccion;
    }

        public static String obtNombreporPlaca(int id) {
        try {
            con = conetor.conectar();
            String sql = "Select concat( v.numPlaca, ' - ' , v.nombreVaca) as NombrePlaca"
                    + "from vaca v;";
              // Agregado el WHERE para filtrar por ID

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("NombrePlaca"); // Se cambia a referencia
            } else {
                return "--";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos();
        }
        return "--";
    }

    public static boolean eliminar(int idProduccion) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM produccion WHERE idProduccion=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProduccion);

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
    public static String obtenerunNombreProduccion(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();

            String sql = "SELECT  vacaId  FROM Produccion WHERE idProduccion=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("vacaId");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            throw new RuntimeException("Error al obtener el nombre de la producción: " + ex.getMessage(), ex);
        } finally {
            // Cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }
    }

    public String getCowNameByPlateNumber(String plateNumber) {
        String cowName = null;

        try {
            con = conetor.conectar();
            String sql = "SELECT produccion.*, vaca.nombre AS nombre_vaca "
                    + "FROM produccion "
                    + "JOIN vaca ON produccion.vacaId = vaca.id "
                    + "WHERE produccion.placa = ?";

            ps.setString(1, plateNumber);
            ResultSet rs = rs = ps.executeQuery();

            if (rs.next()) {
                cowName = rs.getString("nombre_vaca");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cowName;
    }

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
