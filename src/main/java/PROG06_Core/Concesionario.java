/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG06_Core;

/**
 *
 * @author Ángel
 */
public class Concesionario {
    
    public static final int maximoVehiculos = 50;    //Para setear el máximo
    protected static boolean demo = false;           //Para setear el demo
    
    private int totalVehiculos;
    private final Vehiculo[] vehiculos;
    
    public Concesionario(){
        this.totalVehiculos = 0;
        this.vehiculos = new Vehiculo[maximoVehiculos];
    }
    
    public int getTotalVehiculos(){
        return this.totalVehiculos;
    }
    
    public String buscaVehiculo(String matricula){
        for (int i = 0; i < this.totalVehiculos; i++) {
            Vehiculo vehiculoBuscado = this.vehiculos[i];
            
            if(vehiculoBuscado.getMatricula().equals(matricula)){
                return vehiculoBuscado.toString();
            }
        }
        return null;
    }
    
    public int insertarVehiculo(Vehiculo vehiculo){
        
        if( this.vehiculos.length == this.totalVehiculos && this.totalVehiculos < maximoVehiculos)
            return -1; //Si llegamos al tope
        
        if(this.buscaVehiculo(vehiculo.getMatricula()) == null){
            this.vehiculos[this.totalVehiculos] = vehiculo;
            this.totalVehiculos++; //Subimos el contador, que empezó en 0
            return 0; 
        }else
            return -2; //Si ya lo existe
        
    }
    
    public void listaVehiculos(){
        for (int i = 0; i < totalVehiculos; i++) {
            Vehiculo vehiculo = this.vehiculos[i];
            System.out.println( vehiculo.toString() );
        }
    }
    
    public String actualizarKms( int Kms, String matricula){
        
        for (int i = 0; i < this.totalVehiculos; i++) {
            Vehiculo vehiculoBuscado = this.vehiculos[i];
            
            if(vehiculoBuscado.getMatricula().equals(matricula)){
                this.vehiculos[i].setKms(Kms);
                return "Se ha actualizado el vehiculo con  matricula "+ matricula;
            }
        }
        
        return "No se ha podido actualizar. No se ha encontrado";
        
    }
    
    public String eliminarVehiculo( String matricula){
        
        for (int i = 0; i < this.totalVehiculos; i++) {
            Vehiculo vehiculoBuscado = this.vehiculos[i];
            
            if(vehiculoBuscado.getMatricula().equals(matricula)){
                this.vehiculos[i] = null;
                
                for (int j = i+1; j < this.totalVehiculos; j++) {
                   this.vehiculos[i] = this.vehiculos[j];
                }
                this.totalVehiculos--;
                return "Se ha borrado el vehiculo con  matricula "+ matricula;
            }
        }
        
        
        return "No se ha podido borrar. No se ha encontrado";
    }
}
