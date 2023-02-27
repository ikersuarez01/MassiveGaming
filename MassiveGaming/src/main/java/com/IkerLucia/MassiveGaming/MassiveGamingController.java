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
		//Creación de datos de la base de datos
		usuarios.save(new Usuario("Lucia", "Molinero", "lucia@gmail.com", "1234"));
        usuarios.save(new Usuario("Iker", "Suarez", "iker@gmail.com", "1234"));
        usuarios.save(new Usuario("Raul", "Llona", "raul@gmail.com", "starwars"));
        usuarios.save(new Usuario("Juan", "De Carlos", "juan@gmail.com", "mariobros4life"));

        
        videojuegos.save(new Videojuego("Cult of the lamb", 22.99, true));
        videojuegos.save(new Videojuego("Cult of the lamb", 22.99, false));
        videojuegos.save(new Videojuego("The last of Us", 35.9, false));
        videojuegos.save(new Videojuego("Mario Kart 8 Deluxe", 68.9, false));
        videojuegos.save(new Videojuego("Minecraft", 30.0, true));
        videojuegos.save(new Videojuego("Star Wars Jedi: Survivor", 55.9, true));
        videojuegos.save(new Videojuego("Resident Evil 4", 19.9, false));
        videojuegos.save(new Videojuego("Street Fighter 3", 49.9, true));
        videojuegos.save(new Videojuego("Elden Ring", 59.9, true));
        
        consolas.save(new Consola("Nintendo Switch", 359.99, "Rojo-Azul"));
        consolas.save(new Consola("Nintendo Switch", 359.99, "Naranja-Morado"));
        consolas.save(new Consola("Nintendo Switch", 359.99, "Negro"));
        consolas.save(new Consola("Nintendo 3DS", 142.55, "Azul"));
        consolas.save(new Consola("PlayStation 4", 248.00, "Negro"));
        consolas.save(new Consola("PlayStation 5", 627.00, "Blanco"));
        consolas.save(new Consola("Nintendo Gameboy Advance", 74.95, "Azul"));

        valoraciones.save(new Valoracion(videojuegos.findByNombre("Cult of the lamb").get(0), usuarios.findByNombre("Lucia").get(0),"Es un juego muy bueno"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Cult of the lamb").get(0), usuarios.findByNombre("Iker").get(0),"Muy bonito el apartado visual"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Star Wars Jedi: Survivor").get(0), usuarios.findByNombre("Raul").get(0),"Me encanta, estoy deseando probar más juegos de este estilo"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Mario Kart 8 Deluxe").get(0), usuarios.findByNombre("Raul").get(0),"¡Super divertido!"));
        valoraciones.save(new Valoracion(videojuegos.findByNombre("Mario Kart 8 Deluxe").get(0), usuarios.findByNombre("Juan").get(0),"Este juego es mi vida"));
	}
	
	@GetMapping("/MassiveGaming")
	public String home(Model model) {
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
	@GetMapping("/crearCuenta")
	public String crearCuenta(Model model) {
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		return "crearCuenta";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		return "contacto";
	}
	
	
	@GetMapping("/perfil")
	public String perfil(Model model) {
		//tecnicamente en esta pagina no se mete si no está logueado
		//es decir, userId != 0
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
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
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
        
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
	
	@GetMapping("/iniciarSesion")
    public String iniciarSesion(Model model) {
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }		
        model.addAttribute("texto","");
        return "inicioSesion";
    }
	
	@GetMapping("/videojuegos")
	public String videojuegos(Model model) {
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
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
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
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
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
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
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
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
	
	@PostMapping("/cuentaCreada")
	public String cuentaCreada(Model model,@RequestParam String usuario,@RequestParam String apellido,@RequestParam String correo,@RequestParam String clave) {
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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
		
		//Parte común de la nav bar
		if(userId == 0) {
        	//No se ha iniciado sesion
            model.addAttribute("mostrarPerfil",false);
        }else {
            model.addAttribute("mostrarPerfil",true);
        }
		
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