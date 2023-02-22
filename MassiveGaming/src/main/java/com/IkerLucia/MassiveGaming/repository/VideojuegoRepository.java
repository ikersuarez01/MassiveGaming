package com.IkerLucia.MassiveGaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.*;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
	 List<Videojuego> findByNombre(String nombre);
}
