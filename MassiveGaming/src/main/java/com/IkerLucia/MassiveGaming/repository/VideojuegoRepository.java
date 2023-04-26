package com.IkerLucia.MassiveGaming.repository;

import java.util.List;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.*;

@CacheConfig(cacheNames = "videojuegos")
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
	 @CacheEvict(allEntries=true)
	 Videojuego save(Videojuego videojuego);
	 @Cacheable
	 List<Videojuego> findByNombre(String nombre);
	 @Cacheable
	 List<Videojuego> findAll();
}
