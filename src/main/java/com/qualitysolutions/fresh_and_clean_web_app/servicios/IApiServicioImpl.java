package com.qualitysolutions.fresh_and_clean_web_app.servicios;


import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Categoria;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Marca;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.Producto;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice.RestResponsePagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class IApiServicioImpl implements IApiServicio {

    @Autowired
    RestTemplate restTemplate;

    private String url;

    @Override
    public ResponseEntity<RestResponsePagina<Producto>> traerProductos(Integer page) {
        url = String.format("http://localhost:8081/api/productos/pagina/%d",page-1);
        ParameterizedTypeReference<RestResponsePagina<Producto>> responseType = new ParameterizedTypeReference<RestResponsePagina<Producto>>() { };
        ResponseEntity<RestResponsePagina<Producto>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        return  result;
    }

    @Override
    public ResponseEntity<Map<String, Object>> crearProducto(Producto producto) {
        url = "http://localhost:8081/api/productos";
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        HttpEntity<Producto> peticionEntity = new HttpEntity<>(producto);
        ResponseEntity<Map<String,Object>> respuesta = restTemplate.exchange(url,HttpMethod.POST,peticionEntity,typeRef);
        return  respuesta;
    }

    @Override
    public Boolean crearImagen(Producto producto, MultipartFile foto) {
        url = "http://localhost:8081/api/productos/upload";
        FileSystemResource file = new FileSystemResource(convert(foto));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("archivo",file);
        body.add("id",producto.getId());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body,headers);
        restTemplate.postForObject(url,requestEntity,String.class);
        file.getFile().delete();
        return true;
    }

    @Override
    public ResponseEntity<List<Marca>> traerMarcas() {
        url = "http://localhost:8081/api/marcas";
        ParameterizedTypeReference<List<Marca>> responseType = new ParameterizedTypeReference<List<Marca>>() { };
        ResponseEntity<List<Marca>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        return result;
    }

    @Override
    public ResponseEntity<List<Categoria>> traerCategorias() {
        url = "http://localhost:8081/api/categorias";
        ParameterizedTypeReference<List<Categoria>> responseType = new ParameterizedTypeReference<List<Categoria>>() { };
        ResponseEntity<List<Categoria>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        return result;
    }

    @Override
    public ResponseEntity<Producto> traerProducto(Integer id) {
        url = String.format("http://localhost:8081/api/productos/%d",id);
        ResponseEntity<Producto> result = restTemplate.exchange(url,HttpMethod.GET,null,Producto.class);
        return  result;
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarProducto(Producto producto, Long id) {
        url = "http://localhost:8081/api/productos/".concat(String.valueOf(id));
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        HttpEntity<Producto> peticionEntity = new HttpEntity<>(producto);
        ResponseEntity<Map<String,Object>> respuesta = restTemplate.exchange(url,HttpMethod.PUT,peticionEntity,typeRef);
        return  respuesta;
    }

    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convFile;
    }
}
