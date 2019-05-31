package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "Servicios")
public class Servicio implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    public Integer idServicio;
    @NotEmpty
    @Column(name = "nombre_servicio",nullable = false,length = 40)
    public String nombreServicio;
    @NotEmpty
    @Column(name = "descripcion_servicio",nullable = false,length = 140)
    public String descripcionServicio;
    @NotEmpty
    @Column(name = "precio_servicio",nullable = false)
    public Integer precioServicio;
    @NotEmpty
    @Column(name = "estado_servicio",nullable = false)
    public Character estadoServicio;
    @NotEmpty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sucursal")
    public Sucursal sucursal;
    @NotEmpty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peticion_hora")
    public  PeticionHora peticionHora;

}
