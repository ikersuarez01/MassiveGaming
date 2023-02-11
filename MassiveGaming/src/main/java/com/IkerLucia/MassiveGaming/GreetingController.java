package com.IkerLucia.MassiveGaming;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	
	private String infoCompartida;

	@GetMapping("/formulario")
	public String formulario(Model model) {

		return "formulario";
	}
	@PostMapping("/resultadoFormulario")
	public String resultadoFormulario(@RequestParam String info, HttpSession sesion) {

		sesion.setAttribute("infoUsuario", info);
		infoCompartida = info;

		return "resultadoFormulario";
	}
	@GetMapping("/mostrarDatos")
	public String mostrarDatos(Model model, HttpSession sesion) {

		String infoUsuario = (String) sesion.getAttribute("infoUsuario");

		model.addAttribute("infoUsuario", infoUsuario);
		model.addAttribute("infoCompartida", infoCompartida);

		return "datos";
	}

	@GetMapping("/greeting")
	public String greeting(Model model) {

		model.addAttribute("name", "Mundo");

		return "greeting_template";
	}
}