package com.IkerLucia.MassiveGaming.model;


import javax.persistence.*;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Producto producto;
	private int cantidad;
	private Boolean tipo; //false = videojuego / true = consola
	
	//Constructors
	protected Item() {}
	public Item(Item item){
		super();
		this.producto=item.producto;
		this.cantidad=item.cantidad;
	}
	
	public Item(Producto producto, int cantidad, Boolean tipo) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	public long getId () {
		return id;
	}
	
	public Producto getProducto() {
		return producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public Boolean getTipo() {
		return tipo;
	}
	public void setTipo(Boolean nuevoTipo) {
		tipo = nuevoTipo;
	}
	public void setCantidad(int nuevaCantidad) {
		cantidad = nuevaCantidad;
	}
	
	public Boolean equals(Item i) {
		if(this.producto == i.producto)
			return true;
		else
			return false;
	}
	
}