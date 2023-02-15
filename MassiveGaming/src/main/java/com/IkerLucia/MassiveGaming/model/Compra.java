package com.IkerLucia.MassiveGaming.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Producto producto;
	private Usuario usuario;
	private Date fecha;
	
	
	//Constructors
	protected Compra() {}
	
	public Compra(Producto producto, Usuario usuario, Date fecha) {
		super();
		this.producto = producto;
		this.usuario = usuario;
		this.fecha = fecha;
	}
	
	public long getId () {
		return id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public Producto getProducto() {
		return producto;
	}
	public Date getFecha() {
		return fecha;
	}
	
}