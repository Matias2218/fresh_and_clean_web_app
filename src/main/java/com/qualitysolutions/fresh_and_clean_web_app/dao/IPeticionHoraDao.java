package com.qualitysolutions.fresh_and_clean_web_app.dao;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.PeticionHora;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface IPeticionHoraDao extends CrudRepository<PeticionHora,Integer> {

    @Query(value = "SELECT  date_format(hora_atencion,\"%H:%i\") FROM bdbarberia.peticion_horas where Date(hora_atencion)=?1 and empleado_id=?2",nativeQuery = true)
    List<String> barberosHoras(LocalDate fecha,Integer idEmpleado);
}
