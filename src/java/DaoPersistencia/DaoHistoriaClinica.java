/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoPersistencia;

import Conectar.Conexion;
import Modelo.HistoriaClinica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoHistoriaClinica {
     static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar); 
    public static boolean grabar(HistoriaClinica historiaclinica) {
        boolean resultado = false;
        try {
            con = conetor.conectar();
            String sql = "INSERT INTO historiaclinica(vacaId, medicamentosId, procedimientosId, " // 3
                    + "fechaproceso, recomendaciones) " // 2  = 5
                    + "VALUES(?,?,?,?,?);";

            ps = con.prepareStatement(sql);

            ps.setInt(1, historiaclinica.getVacaId());
            ps.setInt(2, historiaclinica.getMedicamentosId());
            ps.setInt(3, historiaclinica.getProcedimientosId());
            ps.setString(4, historiaclinica.getFechaproceso());
            ps.setString(5, historiaclinica.getRecomendaciones());
          

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
    public static List<HistoriaClinica> listar() {
        List<HistoriaClinica> lista = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT * FROM historiaclinica;"; // Corregido para seleccionar de la tabla "historia "
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HistoriaClinica historiaclinica = new HistoriaClinica();

                historiaclinica.setIdHistoriaClinica(rs.getInt("idHistoriaClinica"));
                historiaclinica.setVacaId(rs.getInt("vacaId"));
                historiaclinica.setMedicamentosId(rs.getInt("medicamentosId"));
                historiaclinica.setProcedimientosId(rs.getInt("procedimientosId"));
                historiaclinica.setFechaproceso(rs.getString("fechaproceso"));
                historiaclinica.setRecomendaciones(rs.getString("recomendaciones"));

           lista.add(historiaclinica);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Metodo me permite Listar para luego Editar   
    
    public static HistoriaClinica obtenerHistoriaClinicaPorId(int id) {
        HistoriaClinica historiaclinica = null;

        String sql = "SELECT * FROM historiaclinica WHERE idHistoriaClinica=?";

        try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    historiaclinica = new HistoriaClinica();
                 
                    historiaclinica.setIdHistoriaClinica(rs.getInt("idHistoriaClinica"));
                    historiaclinica.setVacaId(rs.getInt("vacaId"));
                    historiaclinica.setMedicamentosId(rs.getInt("medicamentosId"));
                    historiaclinica.setProcedimientosId(rs.getInt("procedimientosId"));
                    historiaclinica.setFechaproceso(rs.getString("fechaproceso"));
                    historiaclinica.setRecomendaciones(rs.getString("recomendaciones"));
                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoHistoriaClinica.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return historiaclinica;

    }

    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps    
    public static boolean actualizar(HistoriaClinica historiaclinica) {
        try {
            con = conetor.conectar();
            String sql = "UPDATE historiaclinica set vacaId=?, medicamentosId=?, procedimientosId=?, "
                    + " fechaproceso=?, recomendaciones=? "
                    + " WHERE idHistoriaClinica=? ";

            ps = con.prepareStatement(sql);

            ps.setInt(1, historiaclinica.getVacaId());
            ps.setInt(2, historiaclinica.getMedicamentosId());
            ps.setInt(3, historiaclinica.getProcedimientosId());
            ps.setString(4, historiaclinica.getFechaproceso());
            ps.setString(5, historiaclinica.getRecomendaciones());
            ps.setInt(6, historiaclinica.getIdHistoriaClinica());

            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoHistoriaClinica.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

///    metodo de Buscar(Consultar) verificar por nombre o x otro dato en la barra de busqueda
    public static List<HistoriaClinica> buscarHistoriaClinica(String texto) {
        List<HistoriaClinica> listaHistoriaClinica = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM historiaclinica "
                    + "WHERE vacaId LIKE ? ";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                HistoriaClinica historiaclinica = new HistoriaClinica();

                historiaclinica.setIdHistoriaClinica(rs.getInt("idHistoriaClinica"));
                historiaclinica.setVacaId(rs.getInt("vacaId"));
                historiaclinica.setMedicamentosId(rs.getInt("medicamentosId"));
                historiaclinica.setProcedimientosId(rs.getInt("procedimientosId"));
                historiaclinica.setFechaproceso(rs.getString("fechaproceso"));
                historiaclinica.setRecomendaciones(rs.getString("recomendaciones"));

                listaHistoriaClinica.add(historiaclinica);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaHistoriaClinica;
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
            Logger.getLogger(DaoHistoriaClinica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos();
        }
        return "--";
    }

    public static boolean eliminar(int idHistoriaClinica) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM historiaclinica WHERE idHistoriaClinica=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHistoriaClinica);
            

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
    public static String obtenerunNombreHistoriaClinica(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */

        try {
            con = conetor.conectar();

            String sql = "SELECT  vacaId  FROM historiaclinica WHERE idHistoriaClinica=?";
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
            String sql = "SELECT historiaclinica.*, vaca.nombre AS nombre_vaca "
                    + "FROM historiaclinica "
                    + "JOIN vaca ON historiaclnica.vacaId = vaca.id "
                    + "WHERE historiaclinica.placa = ?";

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
  
