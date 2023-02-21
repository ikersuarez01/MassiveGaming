package com.IkerLucia.MassiveGaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	 List<Producto> findByNombre(String nombre);
}
