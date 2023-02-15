package com.IkerLucia.MassiveGaming;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.IkerLucia.MassiveGaming.model.Usuario;
import com.IkerLucia.MassiveGaming.repository.UsuarioRepository;

@Controller
public class MassiveGamingController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@PostConstruct
	public void init() {
		usuarios.save(new Usuario("Lucia", "Molinero", "lucia@gmail.com", "1234"));
	}
	
	@GetMapping("/greeting")
	public String greeting(Model model) {

		model.addAttribute("name", "Mundo");

		return "greeting_template";
	}

}