package com.qualitysolutions.fresh_and_clean_web_app.dao;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPersonaDao extends CrudRepository<Persona,Integer> {

    @Query(value = "select p.nombre,p.apellido,count(b.id_boleta) as cantidad_atencion from personas p \n" +
            "join empleados e on (e.persona_id=p.id_persona)\n" +
            "join peticion_horas pe on(pe.empleado_id=e.id_empleado)\n" +
            "join boletas b on(b.id_peticion=pe.id_peticion)\n" +
            "where e.tipo_empleado_id=1\n" +
            "group by p.nombre,p.apellido",nativeQuery = true)
    List<String[]> barberoConMasAntenciones();
}
