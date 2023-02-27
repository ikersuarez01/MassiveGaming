package com.IkerLucia.MassiveGaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.*;

public interface ConsolaRepository extends JpaRepository<Consola, Long> {
	 List<Consola> findByNombre(String nombre);
	 List<Consola> findByColor(String color);
}
