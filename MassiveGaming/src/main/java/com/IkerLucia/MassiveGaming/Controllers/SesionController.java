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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.IkerLucia.MassiveGaming.model.*;
import com.IkerLucia.MassiveGaming.repository.*;

@Controller
public class SesionController{
	public Long userId = (long) 0;

	@Autowired
	private SesionActual sesionActual;
	@Autowired
	private UsuarioRepository usuarios;
	
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
	
	@PostMapping("/inicioSesionError")
    public String inicioSesionError(Model model, @RequestParam(required=false) String correo, @RequestParam(required=false) String clave) {
		
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
            
            //guardamos el id del usuario en la sesion
            userId = usu.get(0).getId();
            
            sesionActual.SetId(userId);
          //Parte común de la nav bar
    		if(userId == 0) {
            	//No se ha iniciado sesion
                model.addAttribute("mostrarPerfil",false);
            }else {
                model.addAttribute("mostrarPerfil",true);
            }
            
            return "index";
        }else {
            //Contraseña incorrecta
            model.addAttribute("sesionIniciada",false);
            model.addAttribute("texto","Error al iniciar sesión: contraseña incorrecta");
            return "inicioSesion";
        }
    }
	
	@RequestMapping("/iniciarSesion")
    public String iniciarSesion(Model model) {		
        model.addAttribute("texto","");
        return "inicioSesion";
    }
	
	@RequestMapping("/login")
	public String login(Model model) {
			
		return "login";
	}
	
	@RequestMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}

	@GetMapping("/private")
	public String privatePage(Model model, HttpServletRequest request) {

		String correo = ((Usuario) request.getUserPrincipal()).getCorreo1();
		
		Usuario user = usuarios.findByCorreo1(correo).orElseThrow();

		model.addAttribute("correo", user.getCorreo());		
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    model.addAttribute("token", token.getToken());   

		return "private";
	}

	@GetMapping("/crearCuenta")
	public String crearCuenta(Model model) {
		return "crearCuenta";
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