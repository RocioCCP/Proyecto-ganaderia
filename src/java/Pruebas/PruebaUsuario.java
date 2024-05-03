
package Pruebas;

import DaoPersistencia.DaoUsuario;
import static DaoPersistencia.DaoUsuario.grabar;
import Modelo.Usuario;
import java.util.List;

public class PruebaUsuario {
    
   /*  public static void main(String[] args) {
        // Crear un objeto Usuario para probar el método grabar
        
        Usuario usuario = new Usuario();
        
         usuario.setPerfilId(1);
         usuario.setTipodocumentoId(1);
         usuario.setNumDocumento("12345678");
         usuario.setNombres("Nombre de ejemplo");
         usuario.setApellidos("Apellido de ejemplo");
         usuario.setEmail("ejemplo@example.com");
         usuario.setTelefono(123456789);
         usuario.setUsuario("usuarioEjemplo");
         usuario.setClave("claveEjemplo");
          
              // Supongamos que 1 representa un perfil específico
     
// Supongamos que 1 representa un tipo de documento específico
        
// Supongamos que 1 represta un perfil específico
       // usuario.usuario(1); // Supongamos que 1 representa un tipo de documento específico
        
        // Llamar al método grabar para intentar insertar el usuario en la base de datos
        boolean resultado = grabar(usuario);
        
        // Verificar el resultado e imprimir un mensaje
        if (resultado) {
            System.out.println("El usuario se ha registrado correctamente en la base de datos.");
        } else {
            System.out.println("Error al intentar registrar el usuario en la base de datos.");
        }
    }*/
    
      public static void main(String[] args) {
        List<Usuario> listaDocumentos = DaoUsuario.listar();

        // Imprimir los documentos obtenidos For i
        for (Usuario usuario : listaDocumentos) {
            System.out.println("ID: " + usuario.getIdUsuario()
                    + ", Apllido: " + usuario.getApellidos()
                    + ", Nombre: " + usuario.getNombres()
                    + ", Nombre: " + usuario.getNumDocumento()
                    + ", tel: " + usuario.getTelefono()
                    + ", clave: " + usuario.getClave()
                    + ", Correo: " + usuario.getEmail()
                    + ", perfl: " + usuario.getPerfilId()
                    + ", tp: " + usuario.getTipodocumentoId()
                    + ", Users: " + usuario.getUsuario());
        }
    }
}

    

