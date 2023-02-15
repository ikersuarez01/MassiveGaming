package com.IkerLucia.MassiveGaming.model;

import javax.persistence.*;

@Entity
public class Valoracion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Producto producto;
	private Usuario usuario;
	private String texto;
	
	
	//Constructors
	protected Valoracion() {}
	
	public Valoracion(Producto producto, Usuario usuario, String texto) {
		super();
		this.producto = producto;
		this.usuario = usuario;
		this.texto = texto;
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
	public String getTexto() {
		return texto;
	}
	public void setTexto(String nuevoTexto) {
		texto = nuevoTexto;
	}
	
}