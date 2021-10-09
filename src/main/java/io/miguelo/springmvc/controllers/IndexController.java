package io.miguelo.springmvc.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.miguelo.springmvc.model.Usuario;

//allows us to auto-detect implementation classes through the classpath scanning
@Controller
//annotation for request handling methods
@RequestMapping("/app")
public class IndexController {
	
	/* 
	 * This annotation can be used for injecting values into fields 
	 * in Spring-managed beans, and it can be applied at the field 
	 * or constructor/method parameter level */
	@Value("Hola Spring Framework con Model desde @Value!")
	private String textoIndex;
	
	/* Using the @PropertySource annotation allows us to work 
	 * with values from properties files with the @Value annotation */
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;
	
	@GetMapping({"/index", "/", "", "/home"})
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Miguel");
		usuario.setApellido("Ramos");
		usuario.setEmail("elmiguelo@live.com.mx");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()
				+ " " + usuario.getApellido()));
		
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", textoListar);
		
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = Arrays.asList(new Usuario("Miguel", "Ramos", "elmiguelo@live.com.mx"),
				new Usuario("John", "Doe", "john@correo.com"),
				new Usuario("Jane", "Doe", "jane@correo.com"),
				new Usuario("Tornado", "Roe", "roe@correo.com"));
		
		return usuarios;
	}
}
