package com.qualitysolutions.fresh_and_clean_web_app.modelos;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
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
    @NotBlank(message = "El username no puede estar vacio")
    @Size(min = 1,max = 60,message = "El nombre de usuario no puedo contener mas de 60 caracteres")
    private String usernameEmpleado;
    @Size(max = 100,message = "La contraseña de usuario no puedo contener mas de 100 caracteres")
    @Column(name = "password_empleado",length = 100,nullable = false)
    private String passwordEmpleado;
    @Transient
    @Size(max = 100,message = "La contraseña de usuario no puedo contener mas de 100 caracteres")
    @Column(name = "password_empleado",length = 100,nullable = false)
    private String passwordConfirmEmpleado;
    @Column(name = "email_empleado",length = 60,nullable = false,unique = true)
    @Email
    @NotBlank(message = "El email no púede estar vacio")
    @Size(min = 1,max = 60,message = "El email no puedo contener mas de 60 caracteres")
    private String emailEmpleado;
    @Column(name = "telefono_empleado",length = 14,nullable = false)
    @NotBlank(message = "El telefono no puede estar vacio")
    @Size(min = 1,max = 14,message = "El telefono no puede contener mas de 14 caracteres")
    private String telefonoEmpleado;
    @Column(name = "sueldo_empleado", nullable = false)
    @Min(value = 1,message = "El sueldo no puede ser menor que 1")
    private Integer sueldoEmpleado;
    @Column(name = "bono_empleado")
    private Integer bonoEmpleado;
    @Valid
    @NotNull
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id")
    private Persona persona;
    @Valid
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tipo_empleado_id")
    private TipoEmpleado tipoEmpleado;
    @Column(name = "esta_activo", nullable = false,columnDefinition = "TINYINT(1)")
    private Boolean estaActivo;


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

    public String getPasswordConfirmEmpleado() {
        return passwordConfirmEmpleado;
    }

    public void setPasswordConfirmEmpleado(String passwordConfirmEmpleado) {
        this.passwordConfirmEmpleado = passwordConfirmEmpleado;
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
    public Boolean getEstaActivo() { return  estaActivo;}
    public void setEstaActivo(Boolean estaActivo) {this.estaActivo= estaActivo;}
}
