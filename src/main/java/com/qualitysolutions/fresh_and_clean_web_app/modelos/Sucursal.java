package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Sucursales")
@Audited

public class Sucursal implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sucursal")
    private Integer idSucursal;
    @NotEmpty
    @Column(name = "nombre_sucursal",length = 50,nullable = false)
    private String nombreSucursal;
    @NotEmpty
    @Column(name = "email_sucursal",length = 60,nullable = false)
    private String emailSucursal;
    @NotEmpty
    @Column(name = "telefono_sucursal",length = 14,nullable = false)
    private String telefonoSucursal;
    @NotEmpty
    @Column(name = "direccion_sucursal",length = 100,nullable = false)
    private String direccionSucusal;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
