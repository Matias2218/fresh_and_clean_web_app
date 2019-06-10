package com.qualitysolutions.fresh_and_clean_web_app.controladores;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Cliente;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.PeticionHora;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Servicio;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class UsuarioControlador
{

	@Autowired
	IUsuarioServicio usuarioServicio;
	@GetMapping("/")
	public String index()
	{
		return "index";
	}

	@GetMapping("/pedirHora")
	public String vistaPedirHora(Model model)
	{
		Map<String,String> generos = new HashMap<>();
		Map<Integer,String> barberos = usuarioServicio.findAllByTipoEmpleado()
                .stream()
                .collect(Collectors.toMap(o->
                o.getIdEmpleado(),o-> o.getPersona().getNombre()));
		Map<Integer, ArrayList<String>> servicios = usuarioServicio.findAllServicio()
										.stream()
										.collect(Collectors.toMap(o->o.getIdServicio(),o->new ArrayList<>(Arrays.asList(o.getNombreServicio(),o.getPrecioServicio().toString()))));
		Cliente cliente = new Cliente();
		generos.put("M","Masculino");
		generos.put("F","Femenino");
		model.addAttribute("servicios",servicios);
		model.addAttribute("barberos",barberos);
		model.addAttribute("generos",generos);
		model.addAttribute("cliente",cliente);
		return "pedirHora";
	}
	@Secured("ROLE_GERENTE")
	@GetMapping("gerente")
	public String vistaGerente(Principal principal, Model model)
	{
		Empleado empleado;
		empleado = usuarioServicio.findById(Integer.parseInt(principal.getName()));
		return "redirect:/intranet/";
	}
}
