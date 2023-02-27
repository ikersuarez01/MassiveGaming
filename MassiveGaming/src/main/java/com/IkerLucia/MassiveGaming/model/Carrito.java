package com.IkerLucia.MassiveGaming.model;


import java.util.List;

import javax.persistence.*;

@Entity
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
		
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Item> items;
	
	private Double precio;
	
	@OneToOne
	private Usuario usuario;
	
	protected Carrito() {
		
	}
	//Constructors
	public Carrito(Usuario usuario) {
		super();
		this.usuario=usuario;
		precio=0.0;
	}
	
	public Carrito(List<Item> items) {
		super();
		this.items = items;
	}
	
	public long getId () {
		return id;
	}
		
	public List<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		Boolean encontrado = false;
		int j=0;
		for(int i = 0; i < items.size();i++) {
			if(items.get(i).equals(item)) {
				encontrado = true;
				j=i;
			}
		}
		if(encontrado) {
			items.get(j).setCantidad(items.get(j).getCantidad()+1);
		}else {
			items.add(item);
		}
		precio += item.getProducto().getPrecio();
	}
	public void resetItems() {
		precio = (double) 0;
		items.clear();
	}
	public Double getPrecio () {
		return precio;
	}
	
}