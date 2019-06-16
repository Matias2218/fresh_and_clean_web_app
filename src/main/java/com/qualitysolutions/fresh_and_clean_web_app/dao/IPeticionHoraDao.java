package com.qualitysolutions.fresh_and_clean_web_app.dao;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.PeticionHora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IPeticionHoraDao extends JpaRepository<PeticionHora,Integer> {

    @Query(value = "SELECT  date_format(hora_atencion,\"%H:%i\") FROM bdbarberia.peticion_horas where Date(hora_atencion)=?1 and empleado_id=?2",nativeQuery = true)
    List<String> barberosHoras(LocalDate fecha,Integer idEmpleado);

    @Query(value = "select * from peticion_horas where date(hora_atencion)>=curdate() and empleado_id= :idEmpleado and estado='aceptada'",nativeQuery = true)
    Page<PeticionHora> findAllFechaIgualMayor(Pageable pageable,@Param("idEmpleado")Integer idEmpleado);

    @Modifying
    @Query(value = "update peticion_horas set estado= \"rechazada\" where id_peticion=?1",nativeQuery = true)
    void rechazarHora(Integer id);

    @Modifying
    @Query(value = "update peticion_horas set estado= \"realizada\" where id_peticion=?1",nativeQuery = true)
    void horaRealizada(Integer id);
}
