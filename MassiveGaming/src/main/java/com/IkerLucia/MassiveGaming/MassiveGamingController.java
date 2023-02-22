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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IkerLucia.MassiveGaming.model.*;
import com.IkerLucia.MassiveGaming.repository.*;

@Controller
public class MassiveGamingController {

	@Autowired
	private UsuarioRepository usuarios;
	@Autowired
	private ProductoRepository productos;
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
		productos.save(new Producto("Cult of the lamb", 22.99));
		
		List<Producto> prod = productos.findByNombre("Cult of the lamb");
		
		items.save(new Item(prod.get(0), 1));
		
		List<Usuario> user = usuarios.findByNombre("Lucia");

		compras.save(new Compra(items.findById(4), user.get(0), new java.sql.Date(23, 01, 2023)));
		
	}
	
	@GetMapping("/greeting")
	public String greeting(Model model) {

		model.addAttribute("name", "Mundo");

		return "greeting_template";
	}
	@GetMapping("/formulario")
	public String formulario(Model model) {

		model.addAttribute("name", "Mundo");

		return "formulario";
	}
	
	@PostMapping("/formularioCompleto")
	public String formularioCompleto(Model model,@RequestParam String usuario,@RequestParam String apellido,@RequestParam String correo,@RequestParam String clave) {
		
		List<Usuario> usu = usuarios.findByCorreo(correo);
		if(usu.isEmpty()) {
			usuarios.save(new Usuario(usuario,apellido,correo,clave));
			model.addAttribute("guardado", true);
		}else {
			model.addAttribute("guardado", false);
		}
		
		return "formularioCompleto";
	}
}