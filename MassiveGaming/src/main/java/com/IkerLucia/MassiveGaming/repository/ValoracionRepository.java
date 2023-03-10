package com.IkerLucia.MassiveGaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.*;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
	List<Valoracion> findByNombreProducto(String nombre);
}