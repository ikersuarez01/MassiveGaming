package com.IkerLucia.MassiveGaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.*;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
<<<<<<< Updated upstream
	 List<Videojuego> findByNombre(String nombre);
=======
	 @CacheEvict(allEntries=true)
	 Videojuego save(Videojuego videojuego);
	 @Cacheable
	 List<Videojuego> findByNombre(String nombre);
	 @Cacheable
	 List<Videojuego> findAll();
>>>>>>> Stashed changes
}
