/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import DaoPersistencia.DaoProcedimientos;
import Modelo.Procedimientos;
import java.util.Scanner;

/*
public class PruebaProcedimientos {
     //Metodo para prueba Registrar 
    
   public static void main(String[] args) {
        
        // Instanciar la clases o ( Crear un Objeto)
        
        DaoProcedimientos daoprocedimientos = new DaoProcedimientos();
        Procedimientos procedimientos = new Procedimientos();

        Scanner Ingreso_Teclado = new Scanner(System.in);

        String proced;

        System.out.println("Por favor digite el nombre del procedimiento");
        proced = Ingreso_Teclado.next();

        procedimientos.setTipoprocedimiento(proced);;       

        boolean respuesta = DaoProcedimientos.grabar(procedimientos);
        if (respuesta == true) {
            System.out.println("Registro Exitoso");
        } else {
            System.out.println("Error" + respuesta);
        }
    }
   
   /*
// Metodo para Listar
    public static void main(String[] args) {
        List<TipoDocumento> listaDocumentos = DaoTipoDocumento.listar();

        // Imprimir los documentos obtenidos For i
        for (TipoDocumento documento : listaDocumentos) {
            System.out.println("ID: " + documento.getIdTipodocumento()
                    + ", Nombre: " + documento.getNombredocumento());
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


