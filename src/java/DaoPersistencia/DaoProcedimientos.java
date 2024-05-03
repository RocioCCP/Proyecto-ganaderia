/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoPersistencia;

import Conectar.Conexion;
import Modelo.Procedimientos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rocio Castillo
 */
public class DaoProcedimientos{
    static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar); 
    public static boolean grabar(Procedimientos procedimientos) {
        try {
            con = conetor.conectar();
            String sql = "INSERT INTO procedimientos(tipoprocedimiento) VALUES(?);";  
            
            ps = con.prepareStatement(sql);

            ps.setString(1, procedimientos.getTipoprocedimiento());
            
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
                 }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return false;
    }


    //Metodo para listar y visualizar en un tabla
    
    public static List<Procedimientos> listar() {
        List<Procedimientos> lista = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT * FROM  procedimientos;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Procedimientos procedimientos = new Procedimientos();

                procedimientos.setIdProcedimientos(rs.getInt("idProcedimientos"));
                procedimientos.setTipoprocedimiento(rs.getString("tipoprocedimiento"));
               
                lista.add(procedimientos);
            }
            
        } catch (Exception e) {
        } finally {
           cerrarRecursos();
        }
        return lista;
    }

    //Metodo me permite Listar para luego Editar   
    
     public static Procedimientos obtenerProcedimientosPorID(int id) {
         
          Procedimientos procedimientos = null;
             
          String sql = "SELECT * FROM procedimientos WHERE idProcedimientos=?";
                
             try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
              
                 ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    
                    procedimientos = new Procedimientos();

                    procedimientos.setIdProcedimientos(rs.getInt("idProcedimientos"));
                   procedimientos.setTipoprocedimiento(rs.getString("tipoprocedimiento"));
                  
                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoProcedimientos.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return procedimientos;
    }
       
    
    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps   
    
    public static boolean actualizar(Procedimientos procedimientos) {
        try {
            con = conetor.conectar();
            String sql = "UPDATE  procedimientos SET tipoprocedimiento = ? WHERE idProcedimientos =?";
         
            ps = con.prepareStatement(sql);

            ps.setString(1, procedimientos.getTipoprocedimiento());
            ps.setInt(2,procedimientos.getIdProcedimientos());
      
            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoProcedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

     
   // metodo de Buscar(Consultar) verificar por nombre o x otro dato barra de buisqueda

       public static List<Procedimientos> buscarProcedimientos(String texto) {
        List<Procedimientos> listaProcedimientos = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM procedimientos  WHERE tipoprocedimiento LIKE ? ";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
          
            rs = ps.executeQuery();

            while (rs.next()) {
                Procedimientos procedimientos = new Procedimientos();

               procedimientos.setIdProcedimientos(rs.getInt("idProcedimientos"));
               procedimientos.setTipoprocedimiento(rs.getString("tipoprocedimiento"));

              listaProcedimientos.add (procedimientos);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

       return listaProcedimientos;
    }
    
      /// Metodo para Obtener  datos de la BD  que necesite mostrar en otra clase (usuario) Captura una informacion nombre y lo muestra en otra tabla
    
    public static String obtenerunNombreProcedimientos(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */
     
        try {
            con = conetor.conectar();

            String sql = "SELECT  tipoprocedimiento  FROM procedimientos WHERE idProcedimientos=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("tipoprocedimiento");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProcedimientos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }
    
    public static boolean eliminar(int idProcedimientos) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM procedimientos WHERE idProcedimientos=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProcedimientos);

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

}
