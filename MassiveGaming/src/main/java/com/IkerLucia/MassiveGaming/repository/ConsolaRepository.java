package com.IkerLucia.MassiveGaming.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.IkerLucia.MassiveGaming.model.*;

@CacheConfig(cacheNames="consolas")
public interface ConsolaRepository extends JpaRepository<Consola, Long> {
	@CacheEvict(allEntries=true)
	Consola save(Consola consola); 
	@Cacheable
	List<Consola> findByNombre(String nombre);
	@Cacheable
	List<Consola> findByColor(String color);
	@Cacheable
	List<Consola> findAll();
}
