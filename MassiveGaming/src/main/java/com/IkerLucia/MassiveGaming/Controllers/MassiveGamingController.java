package com.IkerLucia.MassiveGaming.Controllers;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	private SesionActual sesionActual;
	
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
		//Parte común de la nav bar
        if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		return "index";
	}
	@GetMapping("/MassiveGamingC")
	public String cerrarSesion(Model model) {
		userId=(long) 0;
		sesionActual.SetId(userId);
		List<Videojuego> prod = videojuegos.findAll();
        model.addAttribute("productos",prod);
		//Parte común de la nav bar
        if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		return "index";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {
		userId = sesionActual.getId();
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		return "contacto";
	}
	
	
	

	
	
	
}