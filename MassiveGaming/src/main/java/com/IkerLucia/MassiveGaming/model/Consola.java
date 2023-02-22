package com.IkerLucia.MassiveGaming.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Consola")
public class Consola extends Producto implements Serializable{
   
    private String color; 
    
    //Constructors
    protected Consola() {}
    
    public Consola(String nombre, Double precio, String color) {
        super();
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.color = color;
    }
    
    
    public void setColor(String color) {
        color = color;
    }
    public String getColor() {
        return color;
    }
    
}