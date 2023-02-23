package com.IkerLucia.MassiveGaming;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IkerLucia.MassiveGaming.model.*;
import com.IkerLucia.MassiveGaming.repository.*;

@Controller
public class MassiveGamingController {

	private Long userId = (long) 0;
	
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

	
	@PostConstruct
	public void init() {
		usuarios.save(new Usuario("Lucia", "Molinero", "lucia@gmail.com", "1234"));
		usuarios.save(new Usuario("Iker", "Suarez", "iker@gmail.com", "1234"));
		
		videojuegos.save(new Videojuego("Cult of the lamb", 22.99, true));
		videojuegos.save(new Videojuego("The last of Us", 35.9, false));
		videojuegos.save(new Videojuego("Mario Kart 8 Deluxe", 68.9, false));
		videojuegos.save(new Videojuego("Minecraft", 30.0, true));
		videojuegos.save(new Videojuego("Star Wars Jedi: Survivor", 55.9, true));
		videojuegos.save(new Videojuego("Resident Evil 4", 19.9, false));
		videojuegos.save(new Videojuego("Street Fighter 3", 49.9, true));
		
		consolas.save(new Consola("Nintendo Switch", 359.99, "Rojo-Azul"));

		
		List<Videojuego> prod = videojuegos.findByNombre("Cult of the lamb");
		
		items.save(new Item(prod.get(0), 1));
		
		List<Usuario> user = usuarios.findByNombre("Lucia");

		compras.save(new Compra(items.findById(4), user.get(0), new java.sql.Date(23, 01, 2023)));
		
	}
	
	@GetMapping("/MassiveGaming")
	public String home(Model model) {
		List<Videojuego> prod = videojuegos.findAll();
        model.addAttribute("productos",prod);
		return "index";
	}
	@GetMapping("/crearCuenta")
	public String crearCuenta(Model model) {
		return "crearCuenta";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {

		return "contacto";
	}
	
	@PostMapping("/inicioSesion")
    public String inicioSesion(Model model, @RequestParam(required=false) String correo, @RequestParam(required=false) String clave) {
        List<Usuario> usu = usuarios.findByCorreo(correo);
        if(usu.isEmpty()) {
            //No existe el correo utilizado
            model.addAttribute("texto","Error al iniciar sesión: no existe el correo utilizado");
            return "inicioSesion";
        }
        if(usu.get(0).getPassword().equals(clave)) {
            //Sesion incio correcto
            model.addAttribute("sesionIniciada",true);
            model.addAttribute("texto","Sesión iniciada correctamente");
            return "inicioSesion";
        }else {
            //Contraseña incorrecta
            model.addAttribute("sesionIniciada",false);
            model.addAttribute("texto","Error al iniciar sesión: contraseña incorrecta");
            return "inicioSesion";
        }
    }
	
	@GetMapping("/iniciarSesion")
    public String iniciarSesion(Model model) {
        model.addAttribute("texto","");
        return "inicioSesion";
    }
	
	@GetMapping("/videojuegos")
	public String videojuegos(Model model) {
		List<Videojuego> prod = videojuegos.findAll();
        model.addAttribute("productos",prod);
		return "videojuegos";
	}
	@GetMapping("/videojuegos/{nombre}")
	public String videojuegosConcreto(Model model, @PathVariable String nombre) {
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
		model.addAttribute("juego",prod.get(0));
//        if(userId != 0) {
        	model.addAttribute("sesionIniciada",true);
//        }else {
//        	model.addAttribute("sesionIniciada",false);
//        }
		
		return "videojuegosConcreto";
	}
	@PostMapping("/videojuegos/{nombre}")
	public String crearValoracion(@PathVariable String nombre) {
		return "videojuegosConcreto";
	}
	
	@GetMapping("/consolas")
	public String consolas(Model model) {
		List<Consola> prod = consolas.findAll();
        model.addAttribute("productos",prod);
		return "consolas";
	}
	@GetMapping("/consolas/{nombre}")
	public String consolasConcreto(Model model, @PathVariable String nombre) {
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
		model.addAttribute("consola",prod.get(0));
		
		return "consolasConcreto";
	}
	
	@PostMapping("/cuentaCreada")
	public String cuentaCreada(Model model,@RequestParam String usuario,@RequestParam String apellido,@RequestParam String correo,@RequestParam String clave) {
		
		List<Usuario> usu = usuarios.findByCorreo(correo);
		if(usu.isEmpty()) {
			usuarios.save(new Usuario(usuario,apellido,correo,clave));
			model.addAttribute("guardado", true);
		}else {
			model.addAttribute("guardado", false);
		}
		
		return "cuentaCreada";
	}
}