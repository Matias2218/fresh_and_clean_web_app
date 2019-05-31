package com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

public class Producto  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 1,max = 80,message = "El nombre no puedo contener mas de 80 caracteres")
    private String nombre;
    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min = 1,max = 255,message = "La descripcion no puede contener mas de 255 caracteres")
    private String descripcion;
    private String imagen;
    @Valid
    private Marca marca;
    @Valid
    private Categoria categoria;
    @NotNull(message = "El stock no puede estar vacio")
    @Min(value = 1,message = "El stock no puede ser menor que 1")
    @Max(value = 300,message = "El stock no puede ser mayor que 300")
    private int stock;

    public Producto() {
    }

    public Producto(Long id, @NotBlank(message = "El nombre no puede estar vacio") @Size(min = 1, max = 80, message = "El nombre no puedo contener mas de 80 caracteres") String nombre, @NotBlank(message = "La descripcion no puede estar vacia") @Size(min = 1, max = 255, message = "La descripcion no puede contener mas de 255 caracteres") String descripcion, String imagen, Marca marca, Categoria categoria, @NotNull(message = "El stock no puede estar vacio") @Min(value = 1, message = "El stock no puede ser menor que 1") @Max(value = 300, message = "El stock no puede ser mayor que 300") int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.marca = marca;
        this.categoria = categoria;
        this.stock = stock;
    }

    public Producto(@NotBlank(message = "El nombre no puede estar vacio") @Size(min = 1, max = 80, message = "El nombre no puedo contener mas de 80 caracteres") String nombre, @NotBlank(message = "La descripcion no puede estar vacia") @Size(min = 1, max = 255, message = "La descripcion no puede contener mas de 255 caracteres") String descripcion, Marca marca, Categoria categoria, @NotNull(message = "El stock no puede estar vacio") @Min(value = 1, message = "El stock no puede ser menor que 1") @Max(value = 300, message = "El stock no puede ser mayor que 300") int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
