package com.qualitysolutions.fresh_and_clean_web_app.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Cliente;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.PeticionHora;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Servicio;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;


@Controller
public class UsuarioControlador {

	@Autowired
	IUsuarioServicio usuarioServicio;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/pedirHora")
	public String vistaPedirHora(Model model) {
		Map<String, String> generos = new HashMap<>();
		Map<Integer, String> barberos = usuarioServicio.findAllByTipoEmpleado()
				.stream()
				.collect(Collectors.toMap(o ->
						o.getIdEmpleado(), o -> o.getPersona().getNombre()));
		Map<Integer, ArrayList<String>> servicios = usuarioServicio.findAllServicio()
				.stream()
				.collect(Collectors.toMap(o -> o.getIdServicio(), o -> new ArrayList<>(Arrays.asList(o.getNombreServicio(), o.getPrecioServicio().toString()))));
		Cliente cliente = new Cliente();
		generos.put("M", "Masculino");
		generos.put("F", "Femenino");
		model.addAttribute("servicios", servicios);
		model.addAttribute("barberos", barberos);
		model.addAttribute("generos", generos);
		model.addAttribute("cliente", cliente);
		return "pedirHora";
	}

	@PostMapping(value = "/horasDisponibles", produces = "application/json")
	public ResponseEntity<?> horasDisponible(@RequestBody HashMap<String, String> datos)
	{
		Map<String,Object> result = new HashMap<>();
		Integer id = Integer.parseInt(datos.get("idEmpleado"));
		LocalDate fecha = LocalDate.parse(datos.get("fecha"));
		List<String> horas = usuarioServicio.findAllHorasOcupadas(fecha,id);
		result.put("datos",horas);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@PostMapping(value = "/generarHora")
	public String generarHora(@ModelAttribute("cliente")Cliente cliente,
							  @RequestParam(name="cmbEmpleado",required = false)String idEmpleado,
							  @RequestParam(name = "servicios[]",required = false)ArrayList<String> servicios,
							  @RequestParam(name="dateHoraAtencion",required = false) String fechaAtencion,
							  @RequestParam(name = "rdbHora[]",required = false) String[] horas,
							  Model model)
	{
		Integer idEmpleadoInt=0;
		String estado;
		//Conversión fecha
		String fechaAtencionConHoraString = fechaAtencion+" "+horas[0];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime fechaAtencionConHoraDate = LocalDateTime.parse(fechaAtencionConHoraString,formatter);
		//Cliente

		//Empleado
		try {
			idEmpleadoInt = Integer.parseInt(idEmpleado);
		}
		catch (Exception e)
		{

		}
		Empleado empleado = usuarioServicio.findById(idEmpleadoInt);
		//Servicios
		ArrayList<Integer> serviciosInteger = new ArrayList<>();
		serviciosInteger = servicios.stream().map(s -> Integer.parseInt(s)).collect(toCollection(ArrayList::new));
		List<Servicio> servicioList = usuarioServicio.findAllServicioById(serviciosInteger);
		estado="espera";
		PeticionHora peticionHora = new PeticionHora(fechaAtencionConHoraDate,cliente,empleado,servicioList,estado);
		peticionHora=usuarioServicio.savePeticion(peticionHora);
		model.addAttribute("fecha",peticionHora.getHoraAtencion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		model.addAttribute("hora",peticionHora.getHoraAtencion().format(DateTimeFormatter.ofPattern("HH:mm")));
		model.addAttribute("peticionHora",peticionHora);
		return "detalleHora";
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
