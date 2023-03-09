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
public class PerfilController{
	public Long userId = (long) 0;
	
	@Autowired
	private UsuarioRepository usuarios;
	@Autowired
	private CompraRepository compras;
	
	
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
	
	@GetMapping("/perfil")
	public String perfil(Model model) {

		Usuario user = usuarios.getById(userId);
		
        model.addAttribute("NombreUser", user.getNombre());
        model.addAttribute("ApellidoUser", user.getApellido());
        model.addAttribute("CorreoUser", user.getCorreo());
        model.addAttribute("PasswordUser", user.getPassword());
        
        model.addAttribute("compra", compras.findByUsuario(usuarios.getById(userId)));
        if(compras.findByUsuario(usuarios.getById(userId)).isEmpty()) {
        	model.addAttribute("vacio", true);
        }else {
        	model.addAttribute("vacio", false);
        }
		
		return "perfil";
	}
	
	@PostMapping("/perfil/actualizado")
	public  String UpdateUser(Model model, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String correo,@RequestParam String password) {

		
		Usuario user = usuarios.getById(userId);
		
		model.addAttribute("idtemp", user.getId());
		if(nombre != "")
			user.setNombre(nombre);
		if(apellido != "")
			user.setApellido(apellido);
		if(correo != "")
			user.setCorreo(correo);			
		if(password != "")
			user.setPassword(password);
		
		usuarios.save(user);
		
        model.addAttribute("NombreUser", user.getNombre());
        model.addAttribute("ApellidoUser", user.getApellido());
        model.addAttribute("CorreoUser", user.getCorreo());
        model.addAttribute("PasswordUser", user.getPassword());
        model.addAttribute("compra", compras.findByUsuario(usuarios.getById(userId)));
        if(compras.findByUsuario(usuarios.getById(userId)).isEmpty()) {
        	model.addAttribute("vacio", true);
        }else {
        	model.addAttribute("vacio", false);
        }

		return "perfil";
	}
}