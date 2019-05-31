package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Categoria;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Marca;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Producto;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.RestResponsePagina;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IApiServicio {
    ResponseEntity<RestResponsePagina<Producto>> traerProductos(Integer page);
    ResponseEntity<Map<String,Object>> crearProducto(Producto producto);
    Boolean crearImagen(Producto producto, MultipartFile file);
    ResponseEntity<List<Marca>> traerMarcas();
    ResponseEntity<List<Categoria>> traerCategorias();
    ResponseEntity<Producto> traerProducto(Integer id);
    ResponseEntity<Map<String,Object>> editarProducto(Producto producto, Long id);

}
