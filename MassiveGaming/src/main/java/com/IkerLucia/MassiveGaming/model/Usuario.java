package com.IkerLucia.MassiveGaming.model;

import javax.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	private String apellido;
	@Column(unique=true)
	private String correo;
	private String correo1;
	private String clave;
	

	@OneToOne(cascade = {CascadeType.ALL})
	private Carrito carrito;
	
	
	//Constructors
	protected Usuario() {}
	
	public Usuario(String nombre, String apellido, String correo, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.correo1 = correo;
		this.clave = password;
		this.carrito = new Carrito(this);
	}
	
	public Long getId () {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nuevoNombre) {
		nombre = nuevoNombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String nuevoApellido) {
		apellido = nuevoApellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String nuevoCorreo) {
		correo = nuevoCorreo;
	}
	public String getPassword() {
		return clave;
	}
	public void setPassword(String nuevaPassword) {
		clave = nuevaPassword;
	}
	public Carrito getCarrito() {
		return carrito;
	}
	public String getCorreo1() {
		return correo1;
	}
	
}