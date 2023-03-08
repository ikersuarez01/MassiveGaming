package com.IkerLucia.MassiveGaming.Controllers;

import java.security.Principal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.IkerLucia.MassiveGaming.model.*;
import com.IkerLucia.MassiveGaming.repository.*;

@Controller
public class MassiveGamingController {

	//Informacion compartida de inicio de sesion
	private Long userId = (long) 0; //SI ES CERO NO HAY SESION INICIADA
		
	@Autowired
	private UsuarioRepository usuarios;
	@Autowired
	private VideojuegoRepository videojuegos;
	@Autowired
	private ConsolaRepository consolas;
	@Autowired
	private CompraRepository compras;
	@Autowired
	private ValoracionRepository valoraciones;
	@Autowired
	private ItemRepository items;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SesionActual sesionActual;
	
	@PostConstruct
	public void init() {
		//Creación de datos de la base de datos
		usuarios.save(new Usuario("Lucia", "Molinero", "lucia@gmail.com", passwordEncoder.encode("1234")));
        usuarios.save(new Usuario("Iker", "Suarez", "iker@gmail.com", "1234"));
        usuarios.save(new Usuario("Raul", "Llona", "raul@gmail.com", "starwars"));
        usuarios.save(new Usuario("Juan", "De Carlos", "juan@gmail.com", "mariobros4life"));

        
        videojuegos.save(new Videojuego("Cult of the lamb", 22.99, true));
        videojuegos.save(new Videojuego("Cult of the lamb", 22.99, false));
        videojuegos.save(new Videojuego("The last of Us", 35.9, false));
        videojuegos.save(new Videojuego("Mario Kart 8 Deluxe", 68.9, false));
        videojuegos.save(new Videojuego("Minecraft", 30.0, true));
        videojuegos.save(new Videojuego("Star Wars Jedi: Survivor", 55.9, true));
        videojuegos.save(new Videojuego("Resident Evil 4", 19.9, false));
        videojuegos.save(new Videojuego("Street Fighter 3", 49.9, true));
        videojuegos.save(new Videojuego("Elden Ring", 59.9, true));
        
        consolas.save(new Consola("Nintendo Switch", 359.99, "Rojo-Azul"));
        consolas.save(new Consola("Nintendo Switch", 359.99, "Naranja-Morado"));
        consolas.save(new Consola("Nintendo Switch", 359.99, "Negro"));
        consolas.save(new Consola("Nintendo 3DS", 142.55, "Azul"));
        consolas.save(new Consola("PlayStation 4", 248.00, "Negro"));
        consolas.save(new Consola("PlayStation 5", 627.00, "Blanco"));
        consolas.save(new Consola("Nintendo Gameboy Advance", 74.95, "Azul"));

        valoraciones.save(new Valoracion(videojuegos.findByNombre("Cult of the lamb").get(0), usuarios.findByNombre("Lucia").get(0),"Es un juego muy bueno"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Cult of the lamb").get(0), usuarios.findByNombre("Iker").get(0),"Muy bonito el apartado visual"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Star Wars Jedi: Survivor").get(0), usuarios.findByNombre("Raul").get(0),"Me encanta, estoy deseando probar más juegos de este estilo"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Mario Kart 8 Deluxe").get(0), usuarios.findByNombre("Raul").get(0),"¡Super divertido!"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Mario Kart 8 Deluxe").get(0), usuarios.findByNombre("Juan").get(0),"Este juego es mi vida"));
	}
	
	@ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            List<Usuario> usu = usuarios.findByCorreo(principal.getName());
            userId = usu.get(0).getId();
            model.addAttribute("mostrarPerfil",true);
        } else {
            model.addAttribute("mostrarPerfil",false);
        }
    }
	
	@GetMapping("/MassiveGaming")
	public String home(Model model, HttpServletRequest request) {
		userId = sesionActual.getId();
		List<Videojuego> prod = videojuegos.findAll();
        model.addAttribute("productos",prod);
        
		return "index";
	}
	@GetMapping("/MassiveGamingC")
	public String cerrarSesion(Model model) {
		userId=(long) 0;
		sesionActual.SetId(userId);
		List<Videojuego> prod = videojuegos.findAll();
        model.addAttribute("productos",prod);
		return "index";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {
		return "contacto";
	}
	
	
	

	
	
	
}