package com.IkerLucia.MassiveGaming.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Videojuego")
public class Videojuego extends Producto implements Serializable{
   
    private Boolean fisico_digital; //fisico=0; digital=1
    
    //Constructors
    protected Videojuego() {}
    
    public Videojuego(String nombre, Double precio, Boolean f_d) {
        super();
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.fisico_digital = f_d;
    }
    
    
    public void setF_D(Boolean f_d) {
        fisico_digital = f_d;
    }
    public Boolean getFisico_Digital() {
        return fisico_digital;
    }
    
}