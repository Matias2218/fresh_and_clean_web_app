package com.qualitysolutions.fresh_and_clean_web_app.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.PeticionHora;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.TipoEmpleado;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Categoria;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Marca;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Producto;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.RestResponsePagina;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.IApiServicio;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.IUsuarioServicio;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.SmtpMailSender;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/intranet")
public class IntranetControlador {

    private Logger logger = LoggerFactory.getLogger(IntranetControlador.class);

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private SmtpMailSender smtpMailSender;

    @Autowired
    private IApiServicio apiServicio;

    private static final List<String> EXTENSIONES = Arrays.asList("image/png", "image/jpeg", "image/jpg");
    private static final Long MAXIMO_PESO_IMAGEN = 83886080L;
    @GetMapping({"/","login",""})
    public String index(Principal principal,
                        Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        HttpSession session,
                        HttpServletRequest request,
                        HttpServletResponse response) {

        if (principal != null) {
            String rol;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            rol =
                    authentication
                            .getAuthorities().
                            stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining());
            Empleado empleado = usuarioServicio.findById(Integer.parseInt(principal.getName()));
            switch (rol) {
                case "ROLE_BARBERO":
                    session.setAttribute("empleado",empleado);
                    return "redirect:barbero/horas";
                case "ROLE_GERENTE":
                    session.setAttribute("empleado",empleado);
                    return "redirect:gerente";
                case "ROLE_INVENTARIO":
                    session.setAttribute("empleado",empleado);
                    return "redirect:inventario/1";
                case "ROLE_ADMINISTRADOR":
                    session.setAttribute("empleado",empleado);
                    return "redirect:administrador";
            }
        }

        if (error != null) {
            model.addAttribute("error", "Credenciales Incorrectas");
        }
        if(WebUtils.getCookie(request,"errorServidor")!=null)
        {
            model.addAttribute("errorServidor", "No se pudo establecer conexión con el servidor");
            Cookie cookie1 = new Cookie("errorServidor","");
            cookie1.setMaxAge(0);
            cookie1.setPath("/");
            response.addCookie(cookie1);
            return "login";
        }
        if (logout != null) {
            model.addAttribute("logout", "Ha cerrado sesión con éxito");
        }
        return "login";
    }


    @Secured("ROLE_BARBERO")
    @GetMapping({"barbero/horas","barbero/horas/{page}"})
    public String vistaBarbero(@PathVariable(name= "page",required = false)String page,
                               Principal principal,
                               Model model,
                               HttpSession session)
    {
        Integer pageInt=0;
        try {
            pageInt= Integer.parseInt(page);
        }
        catch (Exception e)
        {

        }
        pageInt=(pageInt==null)?1:pageInt;
        if(pageInt<0)
        {
            return "redirect:/intranet/barbero/horas";
        }
        Pageable pageable = PageRequest.of(pageInt,19);
        Page<PeticionHora> peticionHoras =  usuarioServicio.findAllPeticionHoras(pageable,Integer.parseInt(principal.getName()));
        List<PeticionHora> peticionHoraList = peticionHoras.get().collect(Collectors.toList());
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("peticionHoras",peticionHoraList);
        model.addAttribute("persona",((Empleado)session.getAttribute("empleado")).getPersona());
        return "barbero";
    }

    @Secured("ROLE_BARBERO")
    @GetMapping({"barbero/horasEspera","barbero/horasEspera/{page}"})
    public String vistaBarberoHoraEspea(@PathVariable(name= "page",required = false)String page,
                               Principal principal,
                               Model model,
                               HttpSession session)
    {
        Integer pageInt=0;
        try {
            pageInt= Integer.parseInt(page);
        }
        catch (Exception e)
        {
        }
        pageInt=(pageInt==null)?1:pageInt;
        if(pageInt<0)
        {
            return "redirect:/intranet/barbero/horaEspera";
        }
        Pageable pageable = PageRequest.of(pageInt,19);
        Page<PeticionHora> peticionHoras =  usuarioServicio.findAllPeticionHorasEspera(pageable,Integer.parseInt(principal.getName()));
        List<PeticionHora> peticionHoraList = peticionHoras.get().collect(Collectors.toList());
        model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("peticionHoras",peticionHoraList);
        model.addAttribute("persona",((Empleado)session.getAttribute("empleado")).getPersona());
        return "horaEspera";
    }

    @Secured("ROLE_BARBERO")
    @GetMapping({"barbero/productos/{page}","barbero/productos","barbero/productos/"})
    public String vistaProductos(Principal principal,
                                 Model model,
                                 @PathVariable(name = "page",required = false)String page,
                                 HttpSession session)
    {
        Integer pageInt;
        Long totalProductos;
        Empleado empleado;
        ResponseEntity<RestResponsePagina<Producto>> productosJSON;
        try {
            pageInt = Integer.parseInt(page);
        }
        catch (Exception e)
        {
            return "redirect:/intranet/barbero/horas";
        }
        pageInt=(pageInt==null)?1:pageInt;
        if(pageInt<0)
        {
            return "redirect:/intranet/barbero/horas";
        }
        try {
            productosJSON = apiServicio.traerProductos(pageInt);
        }
        catch (Exception e)
        {
            return "redirect:/intranet/barbero/horas";
        }
        if(pageInt>productosJSON.getBody().getTotalPages())
        {
            return "redirect:/intranet/barbero/horas";
        }
        List<Producto> productos = productosJSON.getBody().getContent();
        totalProductos = productosJSON.getBody().getTotalElements();
        empleado = usuarioServicio.findById(Integer.parseInt(principal.getName()));
        model.addAttribute("productos",productos);
        model.addAttribute("empleado",empleado);
        model.addAttribute("page",productosJSON.getBody().getPageable());
        model.addAttribute("totalPaginas",productosJSON.getBody().getTotalPages());
        session.setAttribute("totalProductos",totalProductos);
        model.addAttribute("persona",((Empleado)session.getAttribute("empleado")).getPersona());
        return "verProductos";
    }

    @Secured("ROLE_BARBERO")
    @PostMapping("barbero/rechazarHora")
    public ResponseEntity<?> rechazarHora(@RequestBody HashMap<String, String> datos) {
        int id = Integer.parseInt(datos.get("idHora"));
        String fechaHora;
        String horaAtencion;
        String motivo = datos.get("motivoRechazo");
        Map<String,Object> result = new HashMap<>();
        usuarioServicio.rechazarHora(id);
        PeticionHora peticionHora = usuarioServicio.findByIdPeticion(id);
        fechaHora = peticionHora.getHoraAtencion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        horaAtencion = peticionHora.getHoraAtencion().format(DateTimeFormatter.ofPattern("HH:mm"));
        try {
            smtpMailSender.send(peticionHora.getCliente().getEmailCliente(), "Solicitud de hora",
                    "<div>\n" +
                            "<div style=\"float: right;\">\n" +
                            "<img src=\"https://i.ibb.co/1Jk53Hs/logo-negro.png\" style=\"width:30%; float: right;\">\n" +
                            "</div>\n" +
                            "\n" +
                            "<div style=\"float:left;\">\n" +
                            "<h3 style=\"font-family: Arial, Helvetica, sans-serif; margin-top: 0; margin-bottom: 0.5rem;\">Solicitud de\n" +
                            "hora</h3>\n" +
                            "<div style=\"font-family: Arial, Helvetica, sans-serif;\">\n" +
                            "<p>Su solicitud de hora dentro de la barberia Fresh & Clean ha sido rechazada</p>\n" +
                            "<p>El motivo de rechazo de esta solicitud es: "+motivo+"</p>\n" +
                            "<p>Si necesita informacion, no dude en contactarnos al número:\n" +
                            "+569 9123 4567</p>\n" +
                            "</div>\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "<h3 style=\"margin-top: 0; margin-bottom: 0.5rem;font-family: Arial, Helvetica, sans-serif;\">Informacion\n" +
                            "sobre la hora</h3>\n" +
                            "</div>\n" +
                            "<table\n" +
                            "style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; max-width: 100%; margin-bottom: 1rem; background-color: transparent; padding: 0.75rem; vertical-align: top; background-color: #fff; border: 1px solid #dee2e6 !important; border-bottom-width: 2px;\">\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Nombre</td>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getPersona().getNombre()+"\n" +
                            ""+peticionHora.getCliente().getPersona().getApellido()+"</td>\n" +
                            "</tr>\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Email</td>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getEmailCliente()+"</td>\n" +
                            "</tr>\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Telefono</td>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getTelefonoCliente()+"</td>\n" +
                            "</tr>\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Barbero</td>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getEmpleado().getPersona().getNombre()+" "+peticionHora.getEmpleado().getPersona().getApellido()+"</td>\n" +
                            "</tr>\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #dee2e6; border-bottom: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top;\">Servicios</td>\n" +
                            peticionHora.obtenerServiciosCorreo(peticionHora)+
                            "</tr>\n" +
                            "\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">Fecha\n" +
                            "atencion</td>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">"+fechaHora+"</td>\n" +
                            "</tr>\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #ffd24d; border-top: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">Hora\n" +
                            "de atencion</td>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #ffd24d; border-top: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">"+horaAtencion+"\n" +
                            "am</td>\n" +
                            "</tr>\n" +
                            "<tr>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #ff1a1a; border-top: 1px solid #ff1a1a; padding: 0.75rem; vertical-align: top; background-color: #ff8080;\">Estado\n" +
                            "de Hora</td>\n" +
                            "<td\n" +
                            "style=\"border: 1px solid #ff1a1a; border-top: 1px solid #ff1a1a; padding: 0.75rem; vertical-align: top; background-color: #ff8080;\">"+peticionHora.getEstado()+"</td>\n" +
                            "</tr>\n" +
                            "<tfoot>\n" +
                            "<tr>\n" +
                            "<th colspan=\"2\" style=\"padding: 0.9rem;\">\n" +
                            "<button\n" +
                            "style=\"display: inline-block; font-weight: 400; cursor: pointer; text-align: center; white-space: nowrap; vertical-align: middle; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; border: 1px solid transparent; padding: 0.375rem 0.75rem; font-size: 1rem; line-height: 1.5; border-radius: 0.25rem; transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; color: #fff; background-color: #6c757d; border-color: #6c757d; float: right;\">Para\n" +
                            "mas informacion, visite nuestro sitio web</button>\n" +
                            "</th>\n" +
                            "</tr>\n" +
                            "</tfoot>\n" +
                            "</table>\n" +
                            "\n" +
                            "</div>");
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.put("mensaje","Hora rechazada con exito");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @Secured("ROLE_BARBERO")
    @PostMapping("barbero/horaRealizada")
    public ResponseEntity<?> horaRealizada(@RequestBody HashMap<String, String> datos)
    {
        Map<String,Object> result = new HashMap<>();
        Integer idHora = Integer.parseInt(datos.get("idHora"));
        Integer totalServicio = Integer.parseInt(datos.get("totalServicio"));
        usuarioServicio.horaRelizada(idHora);
        PeticionHora peticionHora = usuarioServicio.findByIdPeticion(idHora);
        ZonedDateTime localDateTime = ZonedDateTime.now(ZoneId.of("America/Santiago"));
        LocalDateTime horaBoleta = localDateTime.toLocalDateTime();
        Boleta boleta = new Boleta(totalServicio,"--",horaBoleta,peticionHora);
        usuarioServicio.saveBoleta(boleta);
        result.put("mensaje","Hora realizada con exito");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @Secured("ROLE_BARBERO")
    @PostMapping("barbero/horaAceptada")
    public ResponseEntity<?> horaAceptada(@RequestBody Integer id)
    {
        Map<String,Object> result = new HashMap<>();
        usuarioServicio.horaAceptada(id);
        result.put("mensaje","Hora aceptada con exito");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("administrador")
    public String vistaAdministrador(Model model,HttpSession session)
    {
        ArrayList<String> sueldosEmpleados = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("###,###.##");
        List<Empleado> empleados = usuarioServicio.findAllEmpleadoOrderByEstaActivo();
        empleados.stream().forEach(empleado -> sueldosEmpleados.add(format.format(empleado.getSueldoEmpleado())));
        model.addAttribute("sueldos",sueldosEmpleados);
        model.addAttribute("empleados",empleados);
        model.addAttribute("persona",((Empleado)session.getAttribute("empleado")).getPersona());
        return "administrador";
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("administrador/crearEmpleado")
    public String vistacrearEmpleado(Model model,HttpSession session)
    {
        Map<String,String> generos = new HashMap<>();
        generos.put("M","Masculino");
        generos.put("F","Femenino");
        Empleado empleado = new Empleado();
        session.setAttribute("tipoEmpleados",usuarioServicio.findAllTipoEmpleados());
        model.addAttribute("generos",generos);
        model.addAttribute("empleado",empleado);
        return "crearEmpleado";
    }
    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping("administrador/crearEmpleado")
    public String crearEmpleado(@Valid @ModelAttribute("empleado")Empleado empleado,
                                BindingResult  bindingResult,HttpSession session)
    {

        ArrayList<String> errores = new ArrayList<>();
        if(bindingResult.hasErrors())
        {
            bindingResult.getFieldErrors().stream().forEach(fieldError -> errores.add(fieldError.getDefaultMessage()));
            return "crearEmpleado";
        }
        empleado.setPasswordEmpleado(empleado.getPasswordEmpleado().trim());
        empleado.setPasswordConfirmEmpleado(empleado.getPasswordConfirmEmpleado().trim());
        if(!empleado.getPasswordEmpleado().equals(empleado.getPasswordConfirmEmpleado()) || empleado.getPasswordEmpleado().equals("") && empleado.getPasswordConfirmEmpleado().equals(""))
        {
            return "crearEmpleado";
        }
        if(empleado.getTipoEmpleado().getIdTipo()>((List<TipoEmpleado>)session.getAttribute("tipoEmpleados")).size())
        {
            return "crearEmpleado";
        }
        empleado.setEstaActivo(true);
        empleado = usuarioServicio.saveEmpleado(empleado);
        return "redirect:/intranet/administrador";
    }
    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping(value = "administrador/activarEmpleado",produces = "application/json")
    public ResponseEntity<?> activarEmpleado(@RequestBody Integer id) {
        Map<String,Object> result = new HashMap<>();
        usuarioServicio.activeEmpleado(id);
        result.put("mensaje","Empleado activado con exito");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping(value = "administrador/desactivarEmpleado",produces = "application/json")
    public ResponseEntity<?> desactivarEmpleado(@RequestBody Integer id) {
        Map<String,Object> result = new HashMap<>();
        usuarioServicio.desactiveEmpleado(id);
        result.put("mensaje","Empleado desactivado con exito");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }



    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("administrador/editarEmpleado/{id}")
    public String vistaEditarEmpleado(@PathVariable(value = "id",required = false)String id,
                                      Model model,
                                      HttpSession session,
                                      HttpServletRequest httpRequest)
    {
        Map<String,String> generos = new HashMap<>();
        Integer idInt;
        Empleado empleado;
        try {
            idInt = Integer.parseInt(id);
        }
        catch (Exception e)
        {
            return "redirect:/intranet/administrador";
        }
        if(idInt<0 || idInt.equals(null))
        {
            return "redirect:/intranet/administrador";
        }
        empleado = usuarioServicio.findById(idInt);
        if(empleado.getEstaActivo().equals(false))
        {
            return "redirect:/intranet/administrador";
        }
        generos.put("M","Masculino");
        generos.put("F","Femenino");
        session.setAttribute("idEmpleado",empleado.getIdEmpleado());
        session.setAttribute("idPersona",empleado.getPersona().getIdPersona());
        session.setAttribute("passwordEmpleado",empleado.getPasswordEmpleado());
        session.setAttribute("tipoEmpleados",usuarioServicio.findAllTipoEmpleados());
        model.addAttribute("empleado",empleado);
        model.addAttribute("generos",generos);
        return "editarEmpleado";
    }
    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping("administrador/editarEmpleado")
    public String editarEmpleado(@Valid @ModelAttribute("empleado") Empleado empleado,
                                 BindingResult bindingResult,
                                 HttpSession session)
    {
        ArrayList<String> errores = new ArrayList<>();
        if(bindingResult.hasErrors())
        {
            bindingResult.getFieldErrors().stream().forEach(fieldError -> errores.add(fieldError.getDefaultMessage()));
            return "editarEmpleado";
        }
        empleado.getPersona().setIdPersona((Integer) session.getAttribute("idPersona"));
        empleado.setIdEmpleado((Integer) session.getAttribute("idEmpleado"));
        empleado.setPasswordEmpleado((empleado.getPasswordEmpleado().trim().equals(""))?(String) session.getAttribute("passwordEmpleado"):empleado.getPasswordEmpleado().trim());
        empleado.setPasswordConfirmEmpleado((empleado.getPasswordConfirmEmpleado().equals(""))?(String) session.getAttribute("passwordEmpleado"):empleado.getPasswordConfirmEmpleado().trim());
        if(!empleado.getPasswordEmpleado().equals(empleado.getPasswordConfirmEmpleado()) || empleado.getPasswordEmpleado().equals("") && empleado.getPasswordConfirmEmpleado().equals("")) {
            return "editarEmpleado";
        }
        if(empleado.getTipoEmpleado().getIdTipo()>((List<TipoEmpleado>)session.getAttribute("tipoEmpleados")).size())
        {
            return "editarEmpleado";
        }
        empleado.setEstaActivo(true);
        empleado=usuarioServicio.saveEmpleado(empleado);
        session.removeAttribute("idPersona");
        session.removeAttribute("idEmpleado");
        session.removeAttribute("passwordEmpleado");
        session.removeAttribute("tipoEmpleados");
        return "redirect:/intranet/administrador";
    }



    @Secured("ROLE_INVENTARIO")
    @GetMapping({"inventario/{page}","inventario","inventario/"})
    public String vistaInventario(Principal principal,
                                  Model model,
                                  @PathVariable(name = "page",required = false)String page,
                                  HttpSession session,
                                  HttpServletResponse response) throws UnsupportedEncodingException {

        Integer pageInt;
        Long totalProductos;
        Empleado empleado;
        ResponseEntity<RestResponsePagina<Producto>> productosJSON;
        try {
            pageInt = Integer.parseInt(page);
        }
        catch (Exception e)
        {
            return "redirect:/intranet/inventario/1";
        }
        pageInt=(pageInt==null)?1:pageInt;
        if(pageInt<0)
        {
            return "redirect:/intranet/inventario/1";
        }
        try {
            productosJSON = apiServicio.traerProductos(pageInt);
        }
        catch (Exception e)
        {
            Cookie cookie = new Cookie("errorServidor", URLEncoder.encode("No se pudo establecer conexión oon el servidor","UTF-8"));
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/logout";
        }
        if(pageInt>productosJSON.getBody().getTotalPages())
        {
            return "redirect:/intranet/inventario/1";
        }
        List<Producto> productos = productosJSON.getBody().getContent();
        totalProductos = productosJSON.getBody().getTotalElements();
        empleado = usuarioServicio.findById(Integer.parseInt(principal.getName()));
        model.addAttribute("productos",productos);
        model.addAttribute("empleado",empleado);
        model.addAttribute("page",productosJSON.getBody().getPageable());
        model.addAttribute("totalPaginas",productosJSON.getBody().getTotalPages());
        session.setAttribute("totalProductos",totalProductos);
        model.addAttribute("persona",((Empleado)session.getAttribute("empleado")).getPersona());
        return "inventario";
    }

    @Secured("ROLE_GERENTE")
    @GetMapping("gerente")
    public String vistaGerente(Model model,HttpSession session)
    {
        model.addAttribute("persona",((Empleado)session.getAttribute("empleado")).getPersona());
        return "gerente";
    }

    @Secured("ROLE_GERENTE")
    @GetMapping("gerente/informes")
    public String vistaInformes(Model model)
    {
        Locale locale = new Locale("es","ES");
        List<Integer> añosBoletas = usuarioServicio.findAllDistinctBoletas();
        Map<String,String> mesesBoletas = usuarioServicio.findAllMesesBoletas();
        mesesBoletas = mesesBoletas.entrySet().stream().map(v->{
            Month month = Month.of(Integer.parseInt(v.getKey()));
            v.setValue(month.getDisplayName(TextStyle.FULL,locale).substring(0,1).toUpperCase().concat(month.getDisplayName(TextStyle.FULL,locale).substring(1)));
            return v;
        }).collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
        model.addAttribute("añosBoletas",añosBoletas);
        model.addAttribute("mesesBoletas",mesesBoletas);
        return "informes";
    }

    @Secured("ROLE_GERENTE")
    @GetMapping(value = "gerente/informes/finanzas")
    public String recargarInformeFinanzas()
    {
        return "redirect:/intranet/gerente/informes";
    }
    @Secured("ROLE_GERENTE")
    @PostMapping(value = "gerente/informes/finanzas")
    public String informeFinanzas(Model model,
                                  @RequestParam(name = "cmbAñoBoleta")String cmbAñoBoleta,
                                  @RequestParam(name = "cmbMesBoleta")String cmbMesBoleta) {
        Integer año;
        Integer mes;
        String mesPalabra;
        Locale locale = new Locale("es","ES");
        Month month;
        List<Boleta> boletas;
        try {
            año = Integer.parseInt(cmbAñoBoleta);
            mes = Integer.parseInt(cmbMesBoleta);
        } catch (Exception e)
        {
            return "redirect:/intranet/gerente/informes";
        }
        month=Month.of(mes);
        mesPalabra= month.getDisplayName(TextStyle.FULL,locale);
        mesPalabra=mesPalabra.substring(0,1).toUpperCase().concat(mesPalabra.substring(1));
        boletas = usuarioServicio.findAllByAnoAndByMes(año,mes);

        if(boletas.isEmpty())
        {
            return "redirect:/intranet/gerente/informes";
        }
        model.addAttribute("mes",mesPalabra);
        model.addAttribute("año",año);
        model.addAttribute("boletas",boletas);
        return "FinanzaVistaPDF";
    }

    @Secured("ROLE_GERENTE")
    @GetMapping(value = "gerente/informes/atencionBarberos")
    public String informeFinanzas(Model model) {
        List<String[]> barberos = usuarioServicio.barberoConMasAtenciones();
        model.addAttribute("barberos",barberos);
        return "BarberoAtencionPDF";
    }


    @Secured("ROLE_INVENTARIO")
    @GetMapping("inventario/crearProducto")
    public String vistaCrearProducto(Model model)
    {
        List<Marca> marcas = apiServicio.traerMarcas().getBody();
        List<Categoria> categorias = apiServicio.traerCategorias().getBody();
        Producto producto = new Producto();
        model.addAttribute("marcas",marcas);
        model.addAttribute("categorias",categorias);
        model.addAttribute("producto",producto);
        return "crearProducto";
    }
    @Secured("ROLE_INVENTARIO")
    @PostMapping("inventario/crearProducto")
    public String crearProducto(@Valid @ModelAttribute("producto") Producto producto,
                                BindingResult result,
                                @RequestParam(name = "file")MultipartFile foto) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<String> errores = new ArrayList<>();
        String extension;
        extension = foto.getContentType();
        if (!EXTENSIONES.contains(extension) || foto.getSize()>MAXIMO_PESO_IMAGEN) {
            return "redirect:/intranet/inventario/crearProducto";
        }
        if (result.hasErrors())
        {
            errores.addAll(result.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            return "crearProducto";
        }
        Object productoCreado = apiServicio.crearProducto(producto).getBody().get("producto");
        producto = objectMapper.convertValue(productoCreado,Producto.class);
        boolean respuesta = apiServicio.crearImagen(producto,foto);
        return "redirect:/intranet/inventario/1";
    }
    @Secured("ROLE_INVENTARIO")
    @GetMapping({"inventario/editarProducto/{id}","inventario/editarProducto","inventario/editarProducto/"})
    public String vistaEditarProducto(Model model,
                                      @PathVariable(name = "id", required = false) String idString,
                                      HttpSession session)
    {
        Producto producto;
        Integer idInt;
        List<Marca> marcas;
        List<Categoria> categorias;
        Long totalProductos = (Long)session.getAttribute("totalProductos");
        try {
            idInt = Integer.parseInt(idString);
        }
        catch (Exception e)
        {
            return "redirect:/intranet/inventario";
        }
        idInt=(idInt==null)?1:idInt;
        if(idInt>totalProductos)
        {
            return "redirect:/intranet/inventario";
        }
        marcas = apiServicio.traerMarcas().getBody();
        categorias = apiServicio.traerCategorias().getBody();
        producto = apiServicio.traerProducto(idInt).getBody();
        session.setAttribute("idProducto",idInt);
        model.addAttribute("marcas",marcas);
        model.addAttribute("categorias",categorias);
        model.addAttribute("producto",producto);
        return "editarProducto";
    }

    @Secured("ROLE_INVENTARIO")
    @PostMapping("inventario/editarProducto")
    public String editarProducto(@Valid @ModelAttribute("producto")Producto producto,
                                 BindingResult result,
                                 HttpSession session,
                                 @RequestParam(name = "file",required = false)MultipartFile foto)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<String> errores = new ArrayList<>();
        String extension = foto.getContentType();
        if (!foto.isEmpty() && !EXTENSIONES.contains(extension) || !foto.isEmpty() && foto.getSize()>MAXIMO_PESO_IMAGEN )
        {
            return "redirect:/intranet/inventario/editarProducto/".concat(String.valueOf(producto.getId()));
        }
        if(result.hasErrors())
        {
            return "editarProducto";
        }
        producto.setId(new Long((Integer)session.getAttribute("idProducto")));
        ResponseEntity<Map<String,Object>> respuesta = apiServicio.editarProducto(producto,producto.getId());
        Object productoEditado = respuesta.getBody().get("producto");
        producto = objectMapper.convertValue(productoEditado,Producto.class);
        if(!foto.isEmpty())
        {
            Boolean estaCreado = apiServicio.crearImagen(producto,foto);
        }
        session.removeAttribute("idProducto");
        return "redirect:/intranet/inventario";
    }


}
