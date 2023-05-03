package com.IkerLucia.MassiveGaming.Services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IkerLucia.MassiveGaming.model.Consola;
import com.IkerLucia.MassiveGaming.model.Usuario;
import com.IkerLucia.MassiveGaming.model.Valoracion;
import com.IkerLucia.MassiveGaming.model.Videojuego;
import com.IkerLucia.MassiveGaming.repository.ConsolaRepository;
import com.IkerLucia.MassiveGaming.repository.UsuarioRepository;
import com.IkerLucia.MassiveGaming.repository.ValoracionRepository;
import com.IkerLucia.MassiveGaming.repository.VideojuegoRepository;

@Service
public class DatabaseInitializer {
	@Autowired
	private UsuarioRepository usuarios;
	@Autowired
	private VideojuegoRepository videojuegos;
	@Autowired
	private ConsolaRepository consolas;
	@Autowired
	private ValoracionRepository valoraciones;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
//				
//		//USUARIOS
//		usuarios.save(new Usuario("Lucia", "Molinero", "l.molinero.2019@alumnos.urjc.es", passwordEncoder.encode("1234")));
//        usuarios.save(new Usuario("Iker", "Suarez", "ikersuarez01@gmail.com", passwordEncoder.encode("1234")));
//        usuarios.save(new Usuario("Raul", "Llona", "r.llona.2019@alumnos.urjc.es", passwordEncoder.encode("starwars")));
//        usuarios.save(new Usuario("Juan", "De Carlos", "juan@gmail.com", passwordEncoder.encode("mariobros4life")));
//
//        //VIDEOJUEGOS
//        videojuegos.save(new Videojuego("Cult of the lamb", 22.99, true));
//        videojuegos.save(new Videojuego("Cult of the lamb", 22.99, false));
//        videojuegos.save(new Videojuego("The last of Us", 35.9, false));
//        videojuegos.save(new Videojuego("Mario Kart 8 Deluxe", 68.9, false));
//        videojuegos.save(new Videojuego("Minecraft", 30.0, true));
//        videojuegos.save(new Videojuego("Star Wars Jedi: Survivor", 55.9, true));
//        videojuegos.save(new Videojuego("Resident Evil 4", 19.9, false));
//        videojuegos.save(new Videojuego("Street Fighter 3", 49.9, true));
//        videojuegos.save(new Videojuego("Elden Ring", 59.9, true));
//        
//        //CONSOLAS
//        consolas.save(new Consola("Nintendo Switch", 359.99, "Rojo-Azul"));
//        consolas.save(new Consola("Nintendo Switch", 359.99, "Naranja-Morado"));
//        consolas.save(new Consola("Nintendo Switch", 359.99, "Negro"));
//        consolas.save(new Consola("Nintendo 3DS", 142.55, "Azul"));
//        consolas.save(new Consola("PlayStation 4", 248.00, "Negro"));
//        consolas.save(new Consola("PlayStation 5", 627.00, "Blanco"));
//        consolas.save(new Consola("Nintendo Gameboy Advance", 74.95, "Azul"));
//
//        //VALORACIONES
//        valoraciones.save(new Valoracion(videojuegos.findByNombre("Cult of the lamb").get(0), usuarios.findByNombre("Lucia").get(0),"Es un juego muy bueno"));
//        valoraciones.save(new Valoracion(videojuegos.findByNombre("Cult of the lamb").get(0), usuarios.findByNombre("Iker").get(0),"Muy bonito el apartado visual"));
//        valoraciones.save(new Valoracion(videojuegos.findByNombre("Star Wars Jedi: Survivor").get(0), usuarios.findByNombre("Raul").get(0),"Me encanta, estoy deseando probar más juegos de este estilo"));
//        valoraciones.save(new Valoracion(videojuegos.findByNombre("Mario Kart 8 Deluxe").get(0), usuarios.findByNombre("Raul").get(0),"¡Super divertido!"));
//        valoraciones.save(new Valoracion(videojuegos.findByNombre("Mario Kart 8 Deluxe").get(0), usuarios.findByNombre("Juan").get(0),"Este juego es mi vida"));
	}
}