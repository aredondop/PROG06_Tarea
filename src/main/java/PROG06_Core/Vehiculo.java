/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG06_Core;

/**
 *
 * @author Ángel
 * Para manejar lo relativo al vehículo
 */

import java.time.LocalDate;
import java.time.Period;

public class Vehiculo {
    
    //public static int totalVehiculos = 0; //Migrada a Concesionario
    
    private String marca, matricula, descripcion, nombrePropietario, dniPropietario;
    private int numeroKM;
    private double precio;
    private LocalDate fechaMatriculacion;
    
    //Constructor Vacío. Por si hace cosas raras
    public Vehiculo(){
    }
    
    //Constructor principal
    public Vehiculo(String marca, String matricula, int kms, LocalDate fechaMatriculacion, String descripcion, double precio, String nombrePropietario, String dniPropietario) {
        this.marca = marca;
        this.matricula = matricula;
        this.numeroKM = kms;
        this.fechaMatriculacion = fechaMatriculacion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombrePropietario = nombrePropietario;
        this.dniPropietario = dniPropietario;
    }
            
    //Todos los getters. Devuelven el tipo de turno
    public String getMarca(){
        return this.marca;
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public int getKms(){
        return this.numeroKM;
    }
    
    public LocalDate getFechaMatriculacion(){
        return this.fechaMatriculacion;
    }
      
    public String getDescripcion(){
        return this.descripcion;
    }
        
    public double getPrecio(){
        return this.precio;
    }
    
    public String getPropietario(){
        return this.nombrePropietario;
    }
    
    public String getDNI(){
        return this.dniPropietario;
    }
    
    //Todos los setter. Reciben el tipo de turno
    public void setMarca( String marca ){
        this.marca = marca;
    }
    
    public void setMatricula( String matricula ){
        this.matricula = matricula;
    }
    
    public void setKms( int kms ){
        this.numeroKM = kms;
    }
    
    public void setFechaMatriculacion( LocalDate fechaMatriculacion ){
        this.fechaMatriculacion = fechaMatriculacion;
    }
    
    public void setDescripcion( String descripcion ){
        this.descripcion = descripcion;
    }
    
    public void setPrecio( double precio ){
        this.precio = precio;
    }
    
    public void setNombrePropietario( String nombrePropietario ){
        this.nombrePropietario = nombrePropietario;
    }
    
    public void setDNI( String dniPropietario ){
        this.dniPropietario = dniPropietario;
    }
    
    //Getter especial, devuelve un int para decir el total de años
    public int get_Anyos(){
        LocalDate fechaMatriculacion = this.fechaMatriculacion;
        LocalDate hoy = LocalDate.now();
        
        Period p = Period.between(fechaMatriculacion, hoy);
        
        return p.getYears();
    }
    
    //Entendido lo de los overrides
    @Override
    public String toString() {
        return "Vehiculo { " + "marca=" + marca + ", matricula=" + matricula + ", KMs=" + numeroKM + " dni=" + dniPropietario + ", descripcion=" + descripcion + ", precio=" + precio + " }";
    }
}
