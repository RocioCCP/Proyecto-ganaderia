package DaoPersistencia;

import Conectar.Conexion;
import Modelo.CicloReproductivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoCicloReproductivo {

    static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar); 
    public static boolean grabar(CicloReproductivo cicloreproductivo) {
        boolean resultado = false;
        try {
            con = conetor.conectar();
            String sql = "INSERT INTO cicloreproductivo(vacaId, proveedorId, fechainseminacion, " // 3
                    + "toro, fechaParto, numeroCrias, sexoCria, observaciones) " // 5  = 8
                    + "VALUES(?,?,?,?,?,?,?,?);";

            ps = con.prepareStatement(sql);

            ps.setInt(1, cicloreproductivo.getVacaId());
            ps.setInt(2, cicloreproductivo.getProveedorId());
            ps.setString(3, cicloreproductivo.getFechainseminacion());
            ps.setString(4, cicloreproductivo.getToro());
            ps.setString(5, cicloreproductivo.getFechaParto());
            ps.setInt(6, cicloreproductivo.getNumeroCrias());
            ps.setString(7, cicloreproductivo.getSexoCria());
            ps.setString(8, cicloreproductivo.getObservaciones());

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
    public static List<CicloReproductivo> listar() {
        List<CicloReproductivo> lista = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT * FROM cicloreproductivo;"; // Corregido para seleccionar de la tabla "produccion"
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CicloReproductivo cicloreproductivo = new CicloReproductivo();

                cicloreproductivo.setIdCicloReproductivo(rs.getInt("idCicloReproductivo"));
                cicloreproductivo.setVacaId(rs.getInt("vacaId"));
                cicloreproductivo.setProveedorId(rs.getInt("proveedorId"));
                cicloreproductivo.setFechainseminacion(rs.getString("fechainseminacion"));
                cicloreproductivo.setToro(rs.getString("toro"));
                cicloreproductivo.setFechaParto(rs.getString("fechaParto"));
                cicloreproductivo.setNumeroCrias(rs.getInt("numeroCrias"));
                cicloreproductivo.setSexoCria(rs.getString("sexoCria"));
                cicloreproductivo.setObservaciones(rs.getString("observaciones"));

                lista.add(cicloreproductivo);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Metodo me permite Listar para luego Editar   
    public static CicloReproductivo obtenerCicloReproductivoPorId(int id) {
        CicloReproductivo cicloreproductivo = null;

        String sql = "SELECT * FROM cicloreproductivo WHERE idCicloReproductivo=?";

        try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cicloreproductivo = new CicloReproductivo();

                    cicloreproductivo.setIdCicloReproductivo(rs.getInt("idCicloReproductivo"));
                    cicloreproductivo.setVacaId(rs.getInt("vacaId"));
                    cicloreproductivo.setProveedorId(rs.getInt("proveedorId"));
                    cicloreproductivo.setFechainseminacion(rs.getString("fechainseminacion"));
                    cicloreproductivo.setToro(rs.getString("toro"));
                    cicloreproductivo.setFechaParto(rs.getString("fechaParto"));
                    cicloreproductivo.setNumeroCrias(rs.getInt("numeroCrias"));
                    cicloreproductivo.setSexoCria(rs.getString("sexoCria"));
                    cicloreproductivo.setObservaciones(rs.getString("observaciones"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoCicloReproductivo.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return cicloreproductivo;

    }

    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps    
    public static boolean actualizar(CicloReproductivo cicloreproductivo) {
        try {
            con = conetor.conectar();
            String sql = "UPDATE cicloreproductivo set vacaId=?, proveedorId=?, fechainseminacion=?, "
                    + " toro=?, fechaParto=?, numeroCrias=?, sexoCria=?, observaciones=? "
                    + " WHERE idCicloReproductivo=? ";

            ps = con.prepareStatement(sql);

            ps.setInt(1, cicloreproductivo.getVacaId());
            ps.setInt(2, cicloreproductivo.getProveedorId());
            ps.setString(3, cicloreproductivo.getFechainseminacion());
            ps.setString(4, cicloreproductivo.getToro());
            ps.setString(5, cicloreproductivo.getFechaParto());
            ps.setInt(6, cicloreproductivo.getNumeroCrias());
            ps.setString(7, cicloreproductivo.getSexoCria());
            ps.setString(8, cicloreproductivo.getObservaciones());
            ps.setInt(9, cicloreproductivo.getIdCicloReproductivo());

            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoCicloReproductivo.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

///    metodo de Buscar(Consultar) verificar por nombre o x otro dato en la barra de busqueda
    public static List<CicloReproductivo> buscarCicloReproductivo(String texto) {
        List<CicloReproductivo> listaCicloReproductivo = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM cicloreproductivo "
                    + "WHERE vacaId LIKE ? ";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                CicloReproductivo cicloreproductivo = new CicloReproductivo();

                cicloreproductivo.setIdCicloReproductivo(rs.getInt("idCicloReproductivo"));
                cicloreproductivo.setVacaId(rs.getInt("vacaId"));
                cicloreproductivo.setProveedorId(rs.getInt("proveedorId"));
                cicloreproductivo.setFechainseminacion(rs.getString("fechainseminacion"));
                cicloreproductivo.setToro(rs.getString("toro"));
                cicloreproductivo.setFechaParto(rs.getString("fechaParto"));
                cicloreproductivo.setNumeroCrias(rs.getInt("numeroCrias"));
                cicloreproductivo.setSexoCria(rs.getString("sexoCria"));
                cicloreproductivo.setObservaciones(rs.getString("observaciones"));

                listaCicloReproductivo.add(cicloreproductivo);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaCicloReproductivo;
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
            Logger.getLogger(DaoCicloReproductivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos();
        }
        return "--";
    }

    public static boolean eliminar(int idCicloReproductivo) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM cicloreproductivo WHERE idCicloReproductivo=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCicloReproductivo);

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
    public static String obtenerunNombreCicloReproductivo(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();

            String sql = "SELECT  vacaId  FROM CicloReproductivo WHERE idCicloReproductivo=?";
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
            throw new RuntimeException("Error al obtener el nombre  " + ex.getMessage(), ex);
        } finally {
            // Cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }
    }

    public String getCowNameByPlateNumber(String plateNumber) {
        String cowName = null;

        try {
            con = conetor.conectar();
            String sql = "SELECT cicloreproductivo.*, vaca.nombre AS nombre_vaca "
                    + "FROM cicloreproductivo "
                    + "JOIN vaca ON cicloreproductivo.vacaId = vaca.id "
                    + "WHERE cicloreproductivo.placa = ?";

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
