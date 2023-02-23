package com.IkerLucia.MassiveGaming.model;


import java.util.List;

import javax.persistence.*;

@Entity
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
		
	@OneToMany
	private List<Item> items;
	
	//puedo añadir un precio total que se calcule como la suma de los precios de los items
	
	//Constructors
	protected Carrito() {}
	
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
		items.add(item);
	}
	
}