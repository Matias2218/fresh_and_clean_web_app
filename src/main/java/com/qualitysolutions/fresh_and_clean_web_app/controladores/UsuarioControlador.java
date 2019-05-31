package com.qualitysolutions.fresh_and_clean_web_app.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UsuarioControlador
{

	@GetMapping("/")
	public String index()
	{
		return "index";
	}
}
