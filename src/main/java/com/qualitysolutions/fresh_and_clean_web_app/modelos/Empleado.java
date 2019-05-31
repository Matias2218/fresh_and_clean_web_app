package com.qualitysolutions.fresh_and_clean_web_app.modelos;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Empleados")
public class Empleado implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @Column(name = "username_empleado",length = 60,nullable = false,unique = true)
    @NotEmpty
    private String usernameEmpleado;
    @Column(name = "password_empleado",length = 100,nullable = false)
    @NotEmpty
    private String passwordEmpleado;
    @Column(name = "email_empleado",length = 60,nullable = false,unique = true)
    @NotEmpty
    private String emailEmpleado;
    @Column(name = "telefono_empleado",length = 14,nullable = false)
    @NotEmpty
    private String telefonoEmpleado;
    @Column(name = "sueldo_empleado", nullable = false)
    private Integer sueldoEmpleado;
    @Column(name = "bono_empleado")
    private Integer bonoEmpleado;
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="persona_id")
    private Persona persona;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tipo_empleado_id")
    private TipoEmpleado tipoEmpleado;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getUsernameEmpleado() {
        return usernameEmpleado;
    }

    public void setUsernameEmpleado(String usernameEmpleado) {
        this.usernameEmpleado = usernameEmpleado;
    }

    public String getPasswordEmpleado() {
        return passwordEmpleado;
    }

    public void setPasswordEmpleado(String passwordEmpleado) {
        this.passwordEmpleado = passwordEmpleado;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public Integer getSueldoEmpleado() {
        return sueldoEmpleado;
    }

    public void setSueldoEmpleado(Integer sueldoEmpleado) {
        this.sueldoEmpleado = sueldoEmpleado;
    }

    public Integer getBonoEmpleado() {
        return bonoEmpleado;
    }

    public void setBonoEmpleado(Integer bonoEmpleado) {
        this.bonoEmpleado = bonoEmpleado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
