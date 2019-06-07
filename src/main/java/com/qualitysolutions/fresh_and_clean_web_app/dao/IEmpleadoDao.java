package com.qualitysolutions.fresh_and_clean_web_app.dao;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEmpleadoDao extends CrudRepository<Empleado,Integer>
{
     @Query(value="select * from Empleados where username_empleado=?1 and esta_activo=1",nativeQuery = true)
     Empleado findUsuarioByUsernameAndEstaActivo(String usernameEmpleado);
     List<Empleado> findAllByOrderByEstaActivoDesc();
     @Modifying
     @Query(value = "update empleados set esta_activo = 1 where id_empleado=?1",nativeQuery = true)
     void activeEmpleado(Integer id);
     @Modifying
     @Query(value = "update empleados set esta_activo = 0 where id_empleado=?1",nativeQuery = true)
     void desactiveEmpleado(Integer id);
}
