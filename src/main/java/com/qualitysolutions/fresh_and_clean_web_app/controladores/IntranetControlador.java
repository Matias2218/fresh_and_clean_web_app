package com.qualitysolutions.fresh_and_clean_web_app.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Categoria;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Marca;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Producto;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.RestResponsePagina;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.IApiServicio;
import com.qualitysolutions.fresh_and_clean_web_app.servicios.IUsuarioServicio;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.time.Month;
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
                    return "redirect:barbero";
                case "ROLE_GERENTE":
                    return "redirect:gerente";
                case "ROLE_INVENTARIO":
                    return "redirect:inventario/1";
                case "ROLE_ADMINISTRADOR":
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
    @GetMapping("barbero")
    public String vistaBarbero(Principal principal,Model model)
    {
        Empleado empleado;
        empleado = usuarioServicio.findById(Integer.parseInt(principal.getName()));
        model.addAttribute("empleado",empleado);
        return "barbero";
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("administrador")
    public String vistaAdministrador(Principal principal,Model model)
    {
        Empleado empleado;
        empleado = usuarioServicio.findById(Integer.parseInt(principal.getName()));
        model.addAttribute("empleado",empleado);
        return "administrador";
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
        return "inventario";
    }

    @Secured("ROLE_GERENTE")
    @GetMapping("gerente")
    public String vistaGerente(Principal principal,Model model)
    {
        Empleado empleado;
        empleado = usuarioServicio.findById(Integer.parseInt(principal.getName()));
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
        return "redirect:/intranet/inventario";
    }


}