package com.IkerLucia.MassiveGaming.Controllers;

import java.security.Principal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IkerLucia.MassiveGaming.InternalServices.Producer;
import com.IkerLucia.MassiveGaming.model.*;
import com.IkerLucia.MassiveGaming.repository.*;

@Controller
public class SesionController{
	public Long userId = (long) 0;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SesionActual sesionActual;
	@Autowired
	private UsuarioRepository usuarios;
	@Autowired
	private Producer producer;
	
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
	
	@GetMapping("/login")
	public String login(Model model) {
			
		return "login";
	}
	@RequestMapping("/loginerror")
	public String loginerror(Model model) {
			
		return "loginerror";
	}

	@GetMapping("/crearCuenta")
	public String crearCuenta(Model model) {
		return "crearCuenta";
	}
	
	@PostMapping("/cuentaCreada")
	public String cuentaCreada(Model model,@RequestParam String usuario,@RequestParam String apellido,@RequestParam String correo,@RequestParam String clave) {
		
		List<Usuario> usu = usuarios.findByCorreo(correo);
		if(usu.isEmpty()) {
			usuarios.save(new Usuario(usuario,apellido,correo,passwordEncoder.encode(clave)));
			producer.sendNuevaCuenta(new Usuario(usuario,apellido,correo,passwordEncoder.encode(clave)));
			model.addAttribute("guardado", true);
		}else {
			model.addAttribute("guardado", false);
		}
		
		return "cuentaCreada";
	}
}