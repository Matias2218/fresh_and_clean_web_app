package com.qualitysolutions.fresh_and_clean_web_app.dao;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadoDao extends CrudRepository<Empleado,Integer>
{
     Empleado findByUsernameEmpleado(String usernameEmpleado);
}
