package com.qualitysolutions.fresh_and_clean_web_app.controladores;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Cliente;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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
		generos.put("M","Masculino");
		generos.put("F","Femenino");
		Cliente cliente = new Cliente();
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
