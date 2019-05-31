package com.qualitysolutions.fresh_and_clean_web_app.modelos.webservice;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class Categoria implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @NotNull(message = "La categoria no pueda estar vacio")
    @Min(value = 1,message = "La categoria no pueda estar vacio")
	private Long id;
    private String nombre;

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

    public Categoria() {
    }

    public Categoria(Long id) {
        this.id = id;
    }
}

