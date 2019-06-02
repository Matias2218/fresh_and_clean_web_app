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
    private Integer idServicio;
    @NotEmpty
    @Column(name = "nombre_servicio",nullable = false,length = 40)
    private String nombreServicio;
    @NotEmpty
    @Column(name = "descripcion_servicio",nullable = false,length = 140)
    private String descripcionServicio;
    @NotEmpty
    @Column(name = "precio_servicio",nullable = false)
    private Integer precioServicio;
    @NotEmpty
    @Column(name = "estado_servicio",nullable = false)
    private Character estadoServicio;
    @NotEmpty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;
    @NotEmpty
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peticion_hora")
    private  PeticionHora peticionHora;

}
