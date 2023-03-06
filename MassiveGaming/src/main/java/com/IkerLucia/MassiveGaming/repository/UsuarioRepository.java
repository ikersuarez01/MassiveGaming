package com.IkerLucia.MassiveGaming.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	 List<Usuario> findByNombre(String nombre);
	 List<Usuario> findByApellido(String apellido);
	 Optional<Usuario> findByCorreo1(String correo);
	 List<Usuario> findByCorreo(String correo);
}