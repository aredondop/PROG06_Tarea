/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG06_Core;

/**
 *
 * @author Ángel
 * 
 * Para todos nuestros métodos estáticos
 */

//Dependencias para jugar con fechas, reges, contar palabras, etc
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.Scanner; 
import java.util.concurrent.ThreadLocalRandom;

public class Auxiliar {
    
    // Se le pasa un DNI y nos devuelve true o false según si coincide o no. Simplificada con regex
    public static boolean validarDNI(String dni){
        return dni.matches("^[0-9]{8}[A-Z&&[^IÑOU]]");  //Simplificado
        //return dni.matches("^[0-9]{8}[A-Z]{1}$");
    }
    
    public static boolean validarMatricula( String matricula){
        return matricula.matches("^[0-9]{4}[A-Z]{3}$"); //Optimizado
    }
    
    public static boolean validarNombre( String nombrecompleto){
        
        StringTokenizer total = new StringTokenizer(nombrecompleto); //Sacamos palabras
        
        if(nombrecompleto.length() > 40 || !nombrecompleto.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" ) || total.countTokens() != 2 ){
            return false;
        }else
            return true;
    }
    
    //Para validar la fecha. Devuelve true o false
    public static boolean validarFechaMatriculacion(LocalDate fecha){
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fecha); //Optimizado
    }
    
    //Para validar los KMs. Devuelve true o false
    public static boolean validarKMs(int kms){
        return (kms > 0 ) ? true:false; //Optimizado
    }
    
    //Para pedir los KMs. Devuelve un Int
    public static int pedirKMs(){
        int km;
        Scanner input = new Scanner(System.in);
        
        do{
            System.out.println("Introduce el numero de kilometros");
            km = input.nextInt();
        }while( !Auxiliar.validarKMs(km) );
        
        return km;
    }
    
    //Para pedir la matrícula. Devuelve un String
    public static String pedirMatricula(){
        String matricula;
        int v = 0;
        Scanner input = new Scanner(System.in);
        
        do{
            if(v > 0)
               System.out.println("Has escrito mal la matricula"); 
            
            System.out.println("Introduce la matricula. Formato: NNNNLLL");
            matricula = input.next();
            
            ++v;
        }while( !Auxiliar.validarMatricula(matricula) );
        
        return matricula;
    }

    //Para mostrar el menú donde nos haga falta
     public static void mostrarMenu(){
        System.out.println("##############################################");
        System.out.println("#       CONCESIONARIO ALBUQUERQUE            #");
        System.out.println("#              Pulsa un numero               #");
        System.out.println("#   1. Nuevo Vehiculo                        #");
        System.out.println("#   2. Listar Vehiculo                       #");
        System.out.println("#   3. Buscar Vehiculo                       #");
        System.out.println("#   4. Modificar Kms Vehiculo                #");
        System.out.println("#   5. Eliminar Vehiculo                     #");
        System.out.println("#   6. Salir.                                #");
        System.out.println("##############################################");
    }
    
     //Para mostrar el error donde haga falta
     public static void mostrarError( String mensaje){
         System.out.println("No hay "+ mensaje +". Primero, debes crear un vehiculo");
     }
     
     //Para hacernos cadenas al azar y no andar penando para meter vehiculos
     
    public static int mostrarMenuRandom(){
        int total;
        Scanner input = new Scanner(System.in);
        
        do{
            System.out.println("Introduce el numero de vehiculos que quieras crear aleatoriamente. Minimo 0 y Maximo " + Concesionario.maximoVehiculos);
            total = input.nextInt();
        }while( !Auxiliar.validarMenuRandom(total) );
        
        return total;
    }
    
    public static boolean validarMenuRandom(int valor){
        if( valor >= 0 && valor <= Concesionario.maximoVehiculos){
            return true;
        }else{
            return false;
        }
    }
    public static Vehiculo generarVehiculoRandom(){
        Vehiculo vehiculoRandom;
        
        //Vamos añadiendo los datos que nos hagan falta
        String marca = Auxiliar.generarString(5);
        
        //String matricula = Auxiliar.generarInt(4) + Auxiliar.generarString(3);
        String matricula = "";
        for( int a = 0; a < 2; a++){
            matricula += Integer.toString( Auxiliar.generarInt(1) );
        }
        matricula += Auxiliar.generarString(3);
        
        LocalDate fechaMatriculacion = LocalDate.of(2000, 12, 1); 
        
        int km = 1 + Auxiliar.generarInt(1);
        
        String descripcion = Auxiliar.generarString(20);
        Double precio = 100.0;
        String nombreProp = Auxiliar.generarString(5) + " " + Auxiliar.generarString(5) + " " + Auxiliar.generarString(5);
        
        //String DNIProp = Auxiliar.generarInt(8) + Auxiliar.generarString(1);
        String DNIProp = "";
        for( int a = 0; a < 4; a++){
            DNIProp += Integer.toString( Auxiliar.generarInt(1) );
        }
        DNIProp += Auxiliar.generarString(1);
                
        vehiculoRandom = new Vehiculo(marca, matricula, km, fechaMatriculacion, descripcion, precio, nombreProp, DNIProp);
        
        return vehiculoRandom;
    }
      
     public static String generarString( int longitud){
   
        String cadena = "", banco = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }
     
    public static int generarInt( int longitud){
   
        int cadena = 0;
        String banco = "1234567890";
        
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }
     
     public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
}
