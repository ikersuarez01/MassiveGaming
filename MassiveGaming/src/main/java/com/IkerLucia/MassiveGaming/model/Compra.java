package com.IkerLucia.MassiveGaming.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Item> item;
	
	@OneToOne
	private Usuario usuario;
	private Date fecha;
	private Double precio;
	
	
	//Constructors
	protected Compra() {}
	
	public Compra(List<Item> item, Usuario usuario, Date fecha, Double precio) {
		super();
		this.item = new ArrayList<Item>();
		for(int i = 0; i < item.size();i++) {
			this.item.add(item.get(i));
		}
		//this.item = item;
		this.usuario = usuario;
		this.fecha = fecha;
		this.precio = precio;
		
	}
	
	public long getId () {
		return id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public List<Item> getItem() {
		return item;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date newFecha) {
		fecha = newFecha;
	}
	
}