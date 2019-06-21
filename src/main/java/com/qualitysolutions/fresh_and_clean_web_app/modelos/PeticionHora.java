package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PeticionHoras")
public class PeticionHora implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_peticion")
    private Integer idPeticion;
    @Column(name = "hora_atencion",nullable = false)
    private LocalDateTime horaAtencion;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "servicios_peticion_horas",
            joinColumns = { @JoinColumn(name = "id_peticion") },
            inverseJoinColumns = { @JoinColumn(name = "id_servicio") })
    private List<Servicio> servicios;
    @Column(nullable = false)
    private String estado;
    public PeticionHora() {
    }

    public PeticionHora(LocalDateTime horaAtencion, @NotNull Cliente cliente, @NotNull Empleado empleado, List<Servicio> servicios, String estado) {
        this.horaAtencion = horaAtencion;
        this.cliente = cliente;
        this.empleado = empleado;
        this.servicios = servicios;
        this.estado = estado;
    }

    public Integer getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(Integer idPeticion) {
        this.idPeticion = idPeticion;
    }

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Transient
    private String serviciosHTML = "<td\n" +
            "style=\"border: 1px solid #dee2e6; border-bottom: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top;\">";

    public String obtenerServiciosCorreo(PeticionHora peticionHora)
    {
        peticionHora.getServicios().forEach(servicio -> serviciosHTML+=String.format("$%s : %s <br/>",servicio.getPrecioServicio().toString(),servicio.getNombreServicio()));
        serviciosHTML+="</td>";
        return serviciosHTML;
    }
}
