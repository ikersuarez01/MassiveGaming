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
public class ProductosController{
	public Long userId = (long) 0;
	
	@Autowired
	private UsuarioRepository usuarios;
	@Autowired
	private VideojuegoRepository videojuegos;
	@Autowired
	private ConsolaRepository consolas;
	@Autowired
	private ValoracionRepository valoraciones;
	@Autowired
	private ItemRepository items;
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
	
	@GetMapping("/videojuegos")
	public String videojuegos(Model model) {
		
		List<Videojuego> prod = videojuegos.findAll();
		List<String> nombres = new ArrayList<String>();
		List<Videojuego> prodNoRepes = new ArrayList<Videojuego>();
		for(int i = 0; i<prod.size();i++) {
			if(!nombres.contains(prod.get(i).getNombre())) {
				nombres.add(prod.get(i).getNombre());
				prodNoRepes.add(prod.get(i));
			}
		}
        model.addAttribute("productos",prodNoRepes);
		return "videojuegos";
	}
	@GetMapping("/videojuegos/{nombre}")
	public String videojuegosConcreto(Model model, @PathVariable String nombre) {
		
		
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
		model.addAttribute("juego",prod.get(0));
		if(!prod.get(0).getFisico_Digital()) {
			model.addAttribute("versionFisico", true);
		}else {
			model.addAttribute("versionDigital", true);
		}
		if(prod.size()>1) {
			if(!prod.get(1).getFisico_Digital()) {
				model.addAttribute("versionFisico", true);
			}else {
				model.addAttribute("versionDigital", true);
			}
		}
        if(userId != 0) {
        	model.addAttribute("sesionIniciada",true);
        }else {
        	model.addAttribute("sesionIniciada",false);
        }
        List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        if(val.isEmpty())
        	model.addAttribute("Nohayvaloraciones",true);
        else {
        	model.addAttribute("Nohayvaloraciones",false);
        	model.addAttribute("valoraciones",val);
        }
		return "videojuegosConcreto";
	}
	@PostMapping("/videojuegos/{nombre}/valorado")
	public String crearValoracion(Model model, @PathVariable String nombre, @RequestParam String texto) {
		
		
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
		if(!prod.get(0).getFisico_Digital()) {
			model.addAttribute("versionFisico", true);
		}else {
			model.addAttribute("versionDigital", true);
		}
		if(prod.size()>1) {
			if(!prod.get(1).getFisico_Digital()) {
				model.addAttribute("versionFisico", true);
			}else {
				model.addAttribute("versionDigital", true);
			}
		}
		model.addAttribute("juego",prod.get(0));
		model.addAttribute("sesionIniciada",true);
		valoraciones.save(new Valoracion(prod.get(0),usuarios.getById(userId),texto));
		List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        model.addAttribute("valoraciones",val);
		return "videojuegosConcreto";
	}
	@PostMapping("/videojuegos/{nombre}/cestaActualizadaF")
	public String addCestaF(Model model, @PathVariable String nombre) {
		
		
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
		if(!prod.get(0).getFisico_Digital()) {
			model.addAttribute("versionFisico", true);
		}else {
			model.addAttribute("versionDigital", true);
		}
		if(prod.size()>1) {
			if(!prod.get(1).getFisico_Digital()) {
				model.addAttribute("versionFisico", true);
			}else {
				model.addAttribute("versionDigital", true);
			}
		}
		model.addAttribute("juego",prod.get(0));
		if(userId != 0) {
        	model.addAttribute("sesionIniciada",true);
        }else {
        	model.addAttribute("sesionIniciada",false);
        }
		List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        model.addAttribute("valoraciones",val);
		Usuario user = usuarios.getById(userId);
		Carrito carro = user.getCarrito();
		Item unidadItem;
		if(!prod.get(0).getFisico_Digital()) {
			unidadItem = new Item(prod.get(0),1,false);
		}else {
			unidadItem = new Item(prod.get(1),1,false);
		}
		items.save(unidadItem);
		carro.addItem(unidadItem);
		usuarios.save(user);
		
		return "videojuegosConcreto";
	}
	@PostMapping("/videojuegos/{nombre}/cestaActualizadaD")
	public String addCestaD(Model model, @PathVariable String nombre) {
		
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
		if(!prod.get(0).getFisico_Digital()) {
			model.addAttribute("versionFisico", true);
		}else {
			model.addAttribute("versionDigital", true);
		}
		if(prod.size()>1) {
			if(!prod.get(1).getFisico_Digital()) {
				model.addAttribute("versionFisico", true);
			}else {
				model.addAttribute("versionDigital", true);
			}
		}
		model.addAttribute("juego",prod.get(0));
		if(userId != 0) {
        	model.addAttribute("sesionIniciada",true);
        }else {
        	model.addAttribute("sesionIniciada",false);
        }
		List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        model.addAttribute("valoraciones",val);
		Usuario user = usuarios.getById(userId);
		Carrito carro = user.getCarrito();
		Item unidadItem;
		if(prod.get(0).getFisico_Digital()) {
			unidadItem = new Item(prod.get(0),1,false);
		}else {
			unidadItem = new Item(prod.get(1),1,false);
		}
		items.save(unidadItem);
		carro.addItem(unidadItem);
		usuarios.save(user);
		
		return "videojuegosConcreto";
	}
	
	@GetMapping("/consolas")
	public String consolas(Model model) {
		
		List<Consola> prod = consolas.findAll();
		List<String> nombres = new ArrayList<String>();
		List<Consola> prodNoRepes = new ArrayList<Consola>();
		for(int i = 0; i<prod.size();i++) {
			if(!nombres.contains(prod.get(i).getNombre())) {
				nombres.add(prod.get(i).getNombre());
				prodNoRepes.add(prod.get(i));
			}
		}
        model.addAttribute("productos",prodNoRepes);
        model.addAttribute("UserId", userId);
		return "consolas";
	}
	@GetMapping("/consolas/{nombre}")
	public String consolasConcreto(Model model, @PathVariable String nombre) {

		List<Consola> prod = consolas.findByNombre(nombre);
		model.addAttribute("consola",prod.get(0));
        if(userId != 0) {
        	model.addAttribute("sesionIniciada",true);
        }else {
        	model.addAttribute("sesionIniciada",false);
        }
        List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        if(val.isEmpty())
	    	model.addAttribute("Nohayvaloraciones",true);
	    else {
	    	model.addAttribute("Nohayvaloraciones",false);
	    	model.addAttribute("valoraciones",val);
	    }
        model.addAttribute("color",prod);
		return "consolasConcreto";
	}
	@PostMapping("/consolas/{nombre}/valorado")
	public String crearValoracionConsola(Model model, @PathVariable String nombre, @RequestParam String texto) {

		List<Consola> prod = consolas.findByNombre(nombre);
		model.addAttribute("consola",prod.get(0));
		model.addAttribute("sesionIniciada",true);
		valoraciones.save(new Valoracion(prod.get(0),usuarios.getById(userId),texto));
		List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
		model.addAttribute("color",prod);
        model.addAttribute("valoraciones",val);
		return "consolasConcreto";
	}
	@PostMapping("/consolas/{nombre}/cestaActualizada/{color}")
	public String addCestaConsola(Model model, @PathVariable String nombre,@PathVariable String color) {

		List<Consola> prod = consolas.findByNombre(nombre);
		model.addAttribute("consola",prod.get(0));
		if(userId != 0) {
        	model.addAttribute("sesionIniciada",true);
        }else {
        	model.addAttribute("sesionIniciada",false);
        }
		List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        model.addAttribute("valoraciones",val);
        model.addAttribute("color",prod);
		Usuario user = usuarios.getById(userId);
		Carrito carro = user.getCarrito();
		int aux=-1;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getColor().equalsIgnoreCase(color)) {
				aux=i;
			}
		}
		Item unidadItem = new Item(prod.get(aux),1,true);
		items.save(unidadItem);
		
		carro.addItem(unidadItem);
		usuarios.save(user);
		
		return "consolasConcreto";
	}
}