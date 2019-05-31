package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Sucursales")
public class Sucursal implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sucursal")
    public Integer idSucursal;
    @NotEmpty
    @Column(name = "nombre_sucursal",length = 50,nullable = false)
    public String nombreSucursal;
    @NotEmpty
    @Column(name = "email_sucursal",length = 60,nullable = false)
    public String emailSucursal;
    @NotEmpty
    @Column(name = "telefono_sucursal",length = 14,nullable = false)
    public String telefonoSucursal;
    @NotEmpty
    @Column(name = "direccion_sucursal",length = 100,nullable = false)
    public String direccionSucusal;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    public Empresa empresa;
}
