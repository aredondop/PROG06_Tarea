/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG06_Core;

/**
 *
 * @author Angel
 * Nustra clase Main, donde sucede toda la logica de la aplicacion
 */

import java.util.Scanner;           //Biblioteca para poder añadir el input
//Dependencias para jugar con fechas
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

public class Principal {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); 
        
        input.useDelimiter("\n");
        input.useLocale(Locale.US);
        
        //Definimos la broza
        boolean salir = false;
        
        Concesionario miConcesionario = new Concesionario();
        
        //Como meter 50 vehiculos es un tostón, agregamos capa extra para generarlos
        
        if( Concesionario.demo == true){ 
            int random = Auxiliar.mostrarMenuRandom();
            if( random > 0){
                Vehiculo vehiculoRandom;

                for (int i = 0; i < random; i++) {
                    vehiculoRandom = Auxiliar.generarVehiculoRandom();

                    miConcesionario.insertarVehiculo(vehiculoRandom);
                }
               vehiculoRandom = null;
            }
        }
        
        
        while(salir == false){
            
            //Para evitar sorpresas, a cada vuelta limpiamos 
            
            String matricula, marca, nombreProp, DNIProp;
            int leerMenu, km, dia, mes, anyo;
            LocalDate fechaMatriculacion;
            Double precio;

            Vehiculo nuevoVehiculo;
            
            try{
                
                Auxiliar.mostrarMenu();
                leerMenu = input.nextInt();
            
                switch(leerMenu){
                    case 1:
                        if( miConcesionario.getTotalVehiculos() < Concesionario.maximoVehiculos ) {
                            
                        
                            System.out.println("Introduce la marca");
                            marca = input.next();

                            //Codigo redundante, se convierte a funciones
                            matricula = Auxiliar.pedirMatricula();
                            km = Auxiliar.pedirKMs();
                        
                            do{
                                System.out.println("Introduce el dia de la fecha de matriculacion, en formato num?rico");
                                dia = input.nextInt();

                                System.out.println("Introduce el mes de la fecha de matriculacion, en formato num?rico");
                                mes = input.nextInt();

                                System.out.println("Introduce el año de la fecha de matriculacion, en formato numerico");
                                anyo = input.nextInt();

                                fechaMatriculacion = LocalDate.of(anyo, mes, dia);

                            }while( !Auxiliar.validarFechaMatriculacion(fechaMatriculacion) );

                            System.out.println("Introduce la descripcion");
                            String descripcion = input.next();
                        
                            do{
                                System.out.println("Introduce el nombre completo del propietario");
                                nombreProp = input.next();
                            }while( !Auxiliar.validarNombre(nombreProp) );
                            
                            do{
                                System.out.println("Introduce el dni del propietario");
                                DNIProp = input.next(); //A validar
                            }while( !Auxiliar.validarDNI(DNIProp) );
                        
                        
                            System.out.println("Introduce el precio");
                            precio = input.nextDouble();

                            nuevoVehiculo = new Vehiculo(marca, matricula, km, fechaMatriculacion, descripcion, precio, nombreProp, DNIProp);

                            switch(miConcesionario.insertarVehiculo(nuevoVehiculo)){
                                case -2:
                                    System.out.println("Ya existe ese vehiculo");
                                break;
                                case -1:
                                    System.out.println("No caben más vehiculos. El concesionario esta lleno");
                                break;
                                case 0:
                                    System.out.println("Vehiculo insertado correctamente");
                                break;
                            }
                            
                        }else //Fin del if de control de totales
                            System.out.println("El concesionario esta lleno. No puedes agregar vehiculos.");

                    break;

                    case 2: //Ver Listado con todos los vehiculos
                        if( miConcesionario.getTotalVehiculos() > 0) {
                            miConcesionario.listaVehiculos();
                        }else
                            Auxiliar.mostrarError(" Lista de Vehiculos ");
                        
                    break;

                    case 3: //Buscar un Vehículo en concreto. Por matricula
                        if( miConcesionario.getTotalVehiculos() > 0) {
                            matricula = Auxiliar.pedirMatricula();
                            
                            if ( miConcesionario.buscaVehiculo(matricula) != null )
                                System.out.println("Encontrado : " +miConcesionario.buscaVehiculo(matricula) );
                            else
                                System.out.println("Vehiculo no encontrado " );
                        }else
                            Auxiliar.mostrarError(" posibilidad de buscar Vehiculos");
                    break;

                    case 4: //Actualizar KMs. Por matricula
                        if( miConcesionario.getTotalVehiculos() > 0) {
                            
                            matricula = Auxiliar.pedirMatricula();
                            km = Auxiliar.pedirKMs();
                            
                            System.out.println( miConcesionario.actualizarKms( km, matricula) );
                        
                        }else
                            Auxiliar.mostrarError(" posibilidad de actualizar KMs");
                    break;

                    case 5: //Eliminar Vehiculo
                        if( miConcesionario.getTotalVehiculos() > 0) {
                            matricula = Auxiliar.pedirMatricula();
                            
                            System.out.println( miConcesionario.eliminarVehiculo( matricula) );
                        }else
                            Auxiliar.mostrarError("Borrar");
                    break;


                    case 6:
                        salir = true;
                    break;
                    
                    default:
                        System.out.println("Debes introducir una opcion del menu. Elige entre 1 a 6 ");
                        

                } //Fin del switch. Que se me va la flapa
            
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
        
    }
    
}
