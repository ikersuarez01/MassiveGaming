package com.IkerLucia.MassiveGaming;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
	}
	
	@GetMapping("/MassiveGaming")
	public String home(Model model) {
		List<Videojuego> prod = videojuegos.findAll();
        model.addAttribute("productos",prod);
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
		List<Videojuego> prod = videojuegos.findAll();
        model.addAttribute("productos",prod);
        if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
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
	
	
	@GetMapping("/perfil")
	public String perfil(Model model) {
		//tecnicamente en esta pagina no se mete si no está logueado
		//userId != 0
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

		return "perfil";
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
            
            //guardamos el id del usuario en la sesion
            userId = usu.get(0).getId();
            
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
        if(userId != 0) {
        	model.addAttribute("sesionIniciada",true);
        }else {
        	model.addAttribute("sesionIniciada",false);
        }
        List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        model.addAttribute("valoraciones",val);
		return "videojuegosConcreto";
	}
	@PostMapping("/videojuegos/{nombre}/valorado")
	public String crearValoracion(Model model, @PathVariable String nombre, @RequestParam String texto) {
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
		model.addAttribute("juego",prod.get(0));
		model.addAttribute("sesionIniciada",false);
		valoraciones.save(new Valoracion(prod.get(0),usuarios.getById(userId),texto));
		List<Valoracion> val = valoraciones.findByNombreProducto(nombre);
        model.addAttribute("valoraciones",val);
		return "videojuegosConcreto";
	}
	@PostMapping("/videojuegos/{nombre}/cestaActualizada")
	public String addCesta(Model model, @PathVariable String nombre) {
		List<Videojuego> prod = videojuegos.findByNombre(nombre);
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
		Item unidadItem = new Item(prod.get(0),1);
		items.save(unidadItem);
		
		carro.addItem(unidadItem);
		usuarios.save(user);
		
		return "videojuegosConcreto";
	}
	
	@GetMapping("/consolas")
	public String consolas(Model model) {
		List<Consola> prod = consolas.findAll();
        model.addAttribute("productos",prod);
        
        model.addAttribute("UserId", userId);
        
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