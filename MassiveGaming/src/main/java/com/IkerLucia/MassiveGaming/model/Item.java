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
	
	
	//Constructors
	protected Item() {}
	
	public Item(Producto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
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