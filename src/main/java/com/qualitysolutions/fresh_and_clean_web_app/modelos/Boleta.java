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
    private Integer idBoleta;
    @Column(name = "monto_total",nullable = false)
    private Integer montoTotal;
    @NotEmpty
    @Column(name = "descripcion_boleta",nullable = false,length = 140)
    private String descripcionBoleta;
    @Column(name = "fecha_boleta",nullable = false)
    private LocalDateTime fechaBoleta;
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_peticion")
    private PeticionHora idPeticion;

    public Boleta()
    {

    }
    public Boleta(@NotEmpty Integer montoTotal, @NotEmpty String descripcionBoleta, @NotEmpty LocalDateTime fechaBoleta, @NotNull PeticionHora idPeticion) {
        this.montoTotal = montoTotal;
        this.descripcionBoleta = descripcionBoleta;
        this.fechaBoleta = fechaBoleta;
        this.idPeticion = idPeticion;
    }

    public Integer getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(Integer idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getDescripcionBoleta() {
        return descripcionBoleta;
    }

    public void setDescripcionBoleta(String descripcionBoleta) {
        this.descripcionBoleta = descripcionBoleta;
    }

    public LocalDateTime getFechaBoleta() {
        return fechaBoleta;
    }

    public void setFechaBoleta(LocalDateTime fechaBoleta) {
        this.fechaBoleta = fechaBoleta;
    }

    public PeticionHora getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(PeticionHora idPeticion) {
        this.idPeticion = idPeticion;
    }
}
