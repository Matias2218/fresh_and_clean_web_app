package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "email_cliente",nullable = false,length = 60)
    @NotEmpty
    private String emailCliente;
    @Column(name = "telefono_cliente",nullable = false,length = 14)
    @NotEmpty
    private String telefonoCliente;
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id")
    private Persona persona;

}
