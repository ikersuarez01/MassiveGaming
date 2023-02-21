package com.IkerLucia.MassiveGaming.model;

import javax.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nombre;
    private Double precio;
    
    
    //Constructors
    protected Producto() {}
    
    public Producto(String nombre, Double precio) {
        super();
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public long getId () {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nuevoNombre) {
        nombre = nuevoNombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double nuevoPrecio) {
        precio = nuevoPrecio;
    }
    
}