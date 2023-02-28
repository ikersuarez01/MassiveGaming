package com.IkerLucia.MassiveGaming.model;

import org.springframework.stereotype.Component;

@Component
public class SesionActual {
	private long id;
	
	public long getId () {
		return id;
	}
	public void SetId (long nuevoId) {
		id = nuevoId;
	}
}