package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Boletas")
public class Boleta implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    public Integer idBoleta;
    @NotEmpty
    @Column(name = "monto_total",nullable = false)
    public Integer montoTotal;
    @NotEmpty
    @Column(name = "descripcion_boleta",nullable = false,length = 140)
    public String descripcionBoleta;
    @NotEmpty
    @Column(name = "fecha_boleta",nullable = false)
    public LocalDateTime fechaBoleta;
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_peticion")
    public PeticionHora idPeticion;
}
