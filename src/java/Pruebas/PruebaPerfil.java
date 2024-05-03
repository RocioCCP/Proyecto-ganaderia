package Pruebas;

import DaoPersistencia.DaoCicloReproductivo;
import DaoPersistencia.DaoPerfil;
import Modelo.CicloReproductivo;
import Modelo.Perfil;
import java.util.Scanner;

public class PruebaPerfil {

    
    /*
    public static void main(String[] args) {
        // Crear un objeto CicloReproductivo con los datos de prueba
        CicloReproductivo ciclo = new CicloReproductivo();
        ciclo.setIdCicloReproductivo(7); // Reemplaza con el ID del ciclo a actualizar
        ciclo.setVacaId(2); // Reemplaza con el nuevo ID de vaca
        ciclo.setProveedorId(5); // Reemplaza con el nuevo ID del proveedor
        ciclo.setFechainseminacion("2024-03-21"); // Reemplaza con la nueva fecha de inseminación
        ciclo.setToro("Toro X"); // Reemplaza con el nuevo nombre del toro
        ciclo.setFechaParto("2024-12-25"); // Reemplaza con la nueva fecha de parto
        ciclo.setNumeroCrias(5); // Reemplaza con el nuevo número de crías
        ciclo.setSexoCria("Femenino"); // Reemplaza con el nuevo sexo de la cría
        ciclo.setObservaciones("Ninguna"); // Reemplaza con las nuevas observaciones
        
        // Llamar al método actualizar y mostrar el resultado
        boolean actualizado = DaoCicloReproductivo.actualizar(ciclo);
        if (actualizado) {
            System.out.println("El ciclo reproductivo se actualizó correctamente.");
        } else {
            System.out.println("Hubo un problema al actualizar el ciclo reproductivo.");
        }*/
    
    
    
    //Metodo para prueba Registrar 
   /*public static void main(String[] args) {
        
        // Instanciar la clases o ( Crear un Objeto)
        
        DaoPerfil daoperfil = new DaoPerfil();
        Perfil perfil = new Perfil();

        Scanner Ingreso_Teclado = new Scanner(System.in);

        String nombre;

        System.out.println("Por favor digite el nombre del perfil");
        nombre = Ingreso_Teclado.next();

        perfil.setTipoperfil(nombre);

        boolean respuesta = DaoPerfil.grabar(perfil);
        if (respuesta == true) {
            System.out.println("Registro Exitoso");
        } else {
            System.out.println("Error" + respuesta);
        }
    
     
    
// Metodo para Listar
   /* public static void main(String[] args) {
        List<Perfil> listaPerfil = DaoPerfil.listar();

        // Imprimir los documentos obtenidos For i
        for (Perfil documento : listaPerfil) {
            System.out.println("ID: " + documento.getIdPerfil()
                    + ", Nombre: " + documento.getTipoperfil());
        }
    }


    /*
        //Prueba Editar
         public static void main(String[] args) {

        // Creamos un Obj 
        DaoConsecutivo daoconsecutivo = new DaoConsecutivo();
        Consecutivo consecutivo = new Consecutivo();

        Scanner Leer = new Scanner(System.in);

        String nroRemision = "";
        int productos_idProductos = 0;
        int nroConsecutivo = 0;
        int clientes_idclientes = 0;
        String fechaCertificado = "";
        String fechaProduccion = "";
        int cantidadMaterial = 0;
        int usuarios_idUsuarios = 0;

        System.out.println("Por favor, nro Remision");
        nroRemision = Leer.next();

        System.out.println("Por favor, IDproducto");
        productos_idProductos = Leer.nextInt();

        System.out.println("Por favor, nro consecutivo");
        nroConsecutivo = Leer.nextInt();

        System.out.println("Por favor, IDcliente");
        clientes_idclientes = Leer.nextInt();

        System.out.println("Por favor, Ingrese FechaCert");
        fechaCertificado = Leer.next();

        System.out.println("Por favor, Ingrese Fechaprod");
        fechaProduccion = Leer.next();

        System.out.println("Por favor, Ingrese Canti Material");
        cantidadMaterial = Leer.nextInt();
        
        System.out.println("Por favor, Ingrese iDusuarios");
        usuarios_idUsuarios = Leer.nextInt();

        // Seteamos los parametros, pasandole lo ingresado por el Usuario
        consecutivo.setNroRemision(nroRemision);
        consecutivo.setProductosRef_idProductos(productos_idProductos);
        consecutivo.setNroConsecutivo(nroConsecutivo);
        consecutivo.setClientes_idclientes(clientes_idclientes);
        consecutivo.setFechaCertificado(fechaCertificado);
        consecutivo.setFechaProduccion(fechaProduccion);
        consecutivo.setCantidadMaterial(cantidadMaterial);
        consecutivo.setUsuarios_idUsuarios(usuarios_idUsuarios);
        

        boolean respuesta = daoconsecutivo.grabarConsecutivo(consecutivo);
        if (respuesta == true) {
            System.out.println("Registro Exitoso");
        } else {
            System.out.println("Error" + respuesta);
        }*/
}

