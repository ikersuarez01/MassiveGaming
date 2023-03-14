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

import com.IkerLucia.MassiveGaming.InternalServices.Producer;
import com.IkerLucia.MassiveGaming.model.*;
import com.IkerLucia.MassiveGaming.repository.*;

@Controller
public class CarritoController {
	public Long userId = (long) 0;
	
	@Autowired
	private UsuarioRepository usuarios;
	@Autowired
	private CompraRepository compras;
	
	
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
	
	@GetMapping("/carrito")
	public String carrito(Model model) {
		
		if(userId != 0) {
			List<Item> listaItems = usuarios.getById(userId).getCarrito().getItems();
			model.addAttribute("items",listaItems);
			if(listaItems.isEmpty()) {
				model.addAttribute("vacio", true);
				model.addAttribute("textoVacio"," Tu carrito está vacío");
				model.addAttribute("precio", 0.0);
			}else {
				model.addAttribute("vacio", false);
				DecimalFormat format1 = new DecimalFormat("#.00");
				model.addAttribute("precio", format1.format(usuarios.getById(userId).getCarrito().getPrecio()));
			}
		}else {
			model.addAttribute("items","");
		}
			
		return "carrito";
	}
	@PostMapping("/carritoVaciar")
	public String carritoVaciar(Model model) {
		
		if(userId != 0) {
			Usuario usu = usuarios.getById(userId);
			List<Item> listaItems = usu.getCarrito().getItems();
			usu.getCarrito().resetItems();
			usuarios.save(usu);
			listaItems = usuarios.getById(userId).getCarrito().getItems();
			model.addAttribute("items",listaItems);
			if(listaItems.isEmpty()) {
				model.addAttribute("vacio", true);
				model.addAttribute("textoVacio"," Tu carrito está vacío");
				model.addAttribute("precio", 0.0);
			}else {
				model.addAttribute("vacio", false);
				DecimalFormat format1 = new DecimalFormat("#.00");
				model.addAttribute("precio", format1.format(usuarios.getById(userId).getCarrito().getPrecio()));
			}
		}else {
			model.addAttribute("items","");
		}
		return "carrito";
	}
	@PostMapping("/carritoComprar")
	public String carritoComprar(Model model) {
		
		if(userId != 0) {
			Usuario usu = usuarios.getById(userId);
			List<Item> listaItems = usu.getCarrito().getItems();
			Double valor = usuarios.getById(userId).getCarrito().getPrecio();
			valor = valor*100;
			valor = (double) Math.round(valor);
			valor = valor/100;
			Compra nuevaCompra = new Compra(listaItems,usuarios.getById(userId),Date.valueOf(java.time.LocalDate.now()),valor);
			compras.save(nuevaCompra);
			usu.getCarrito().resetItems();
			usuarios.save(usu);
			listaItems = usuarios.getById(userId).getCarrito().getItems();
			model.addAttribute("items",listaItems);
			if(listaItems.isEmpty()) {
				model.addAttribute("vacio", true);
				model.addAttribute("textoVacio","Compra realizada correctamente");
				model.addAttribute("precio", 0.0);
			}else {
				model.addAttribute("vacio", false);
				DecimalFormat format1 = new DecimalFormat("#.00");
				model.addAttribute("precio", format1.format(usuarios.getById(userId).getCarrito().getPrecio()));
			}
		}else {
			model.addAttribute("items","");
		}	
			
		return "carrito";
	}
}