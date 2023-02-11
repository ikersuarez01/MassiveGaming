package com.IkerLucia.MassiveGaming.model;

import javax.persistence.*;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellido;
	private String correo;
	private String password;
	
	
	//Constructors
	protected Usuario() {}
	
	public Usuario(String nombre, String apellido, String correo, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.password = password;
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
		return password;
	}
	public void setPassword(String nuevaPassword) {
		password = nuevaPassword;
	}
	
}