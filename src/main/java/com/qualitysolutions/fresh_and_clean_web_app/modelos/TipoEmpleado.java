package com.qualitysolutions.fresh_and_clean_web_app.modelos;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "TipoEmpleados")
@Audited

public class TipoEmpleado implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    @Min(value = 1,message = "Debe seleccionar tipo de empleado")
    private Integer idTipo;
    @Column(nullable = false,length = 140)
    private String descripcion;

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
