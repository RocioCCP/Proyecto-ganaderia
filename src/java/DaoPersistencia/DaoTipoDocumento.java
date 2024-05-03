package DaoPersistencia;
import Conectar.Conexion;
import Modelo.TipoDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Crear El CRUD
// Registrar - Listar - Editar - Eliminar  // Buscar y/o Consultar

    public class DaoTipoDocumento {
    static Conexion conetor = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    // public String registrar(adicionar); 
    public static boolean grabar(TipoDocumento tipodocumento) {
        try {
            con = conetor.conectar();
            String sql = "INSERT INTO tipodocumento(nombredocumento) VALUES(?);";  
            
            ps = con.prepareStatement(sql);

            ps.setString(1, tipodocumento.getNombredocumento());
            
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
    
    public static List<TipoDocumento> listar() {
        List<TipoDocumento> lista = new ArrayList<>();
        try {
            con = conetor.conectar();
            String sql = "SELECT * FROM  tipodocumento;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoDocumento tipodocumento = new TipoDocumento();

                tipodocumento.setIdTipodocumento(rs.getInt("idTipodocumento"));
                tipodocumento.setNombredocumento(rs.getString("nombredocumento"));
               
                lista.add(tipodocumento);
            }
            
        } catch (Exception e) {
        } finally {
           cerrarRecursos();
        }
        return lista;
    }

    //Metodo me permite Listar para luego Editar   
    
     public static TipoDocumento obtenerTipodocumentoPorID(int id) {
         
          TipoDocumento tipodocumento = null;
             
          String sql = "SELECT * FROM tipodocumento WHERE idTipodocumento=?";
                
             try (Connection con = conetor.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
              
                 ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tipodocumento = new TipoDocumento();

                 tipodocumento.setIdTipodocumento(rs.getInt("idTipodocumento"));
                 tipodocumento.setNombredocumento(rs.getString("nombredocumento"));
                  
                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoTipoDocumento.class.getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        } finally {
            cerrarRecursos();
        }

        return tipodocumento;
    }
       
    
    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps   
    
    public static boolean actualizar(TipoDocumento tipodocumento) {
        try {
            con = conetor.conectar();
            String sql = "UPDATE tipodocumento SET nombredocumento = ? WHERE idTipodocumento =?";
         
            ps = con.prepareStatement(sql);

            ps.setString(1, tipodocumento.getNombredocumento());
            ps.setInt(2,tipodocumento.getIdTipodocumento());
      
            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoTipoDocumento.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

     
   // metodo de Buscar(Consultar) verificar por nombre o x otro dato barra de buisqueda

       public static List<TipoDocumento> buscarTipoDocumento(String texto) {
        List<TipoDocumento> listadocumentos = new ArrayList<>();

        try {
            con = conetor.conectar();

            String sql = "SELECT * FROM tipodocumento  WHERE nombredocumento LIKE ? ";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
          
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoDocumento tipodocumento = new TipoDocumento();

                tipodocumento.setIdTipodocumento(rs.getInt("idTipodocumento"));
                tipodocumento.setNombredocumento(rs.getString("nombredocumento"));

              listadocumentos.add(tipodocumento);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

       return listadocumentos;
    }
    
      /// Metodo para Obtener  datos de la BD  que necesite mostrar en otra clase (usuario) Captura una informacion nombre y lo muestra en otra tabla
    
    public static String obtenerunNombreTipodocumento(int id) {
        /*Metodo que sirve para obtener el nombre y poderlo listar en las tablas relacionadas
        Ejemplo: Tabla Listar Productos_Secos 
        <td><%= DaoUsuarios.obtenerNombreUsuario(terminadoseco.getUsuarios_idUsuarios())%></td> */
     
        try {
            con = conetor.conectar();

            String sql = "SELECT  nombredocumento  FROM tipodocumento WHERE idTipodocumento=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombredocumento");
            } else {
                return "--";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoTipoDocumento.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }
    
    public static boolean eliminar(int idTipoDocumento) {
        try {
            con = conetor.conectar();
            String sql = "DELETE FROM tipodocumento WHERE idTipodocumento=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idTipoDocumento);

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
