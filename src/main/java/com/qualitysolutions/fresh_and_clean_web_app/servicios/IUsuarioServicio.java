package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IUsuarioServicio {

    PeticionHora savePeticion(PeticionHora peticionHora);
    List<Servicio> findAllServicio();
    List<Cliente> findAllCliente();
    List<String> findAllHorasOcupadas(LocalDate fecha, Integer idEmpleado);
    List<Empleado> findAllEmpleadoOrderByEstaActivo();
    List<TipoEmpleado> findAllTipoEmpleados();
    Empleado saveEmpleado(Empleado empleado);
    Boolean activeEmpleado(Integer id);
    Boolean desactiveEmpleado(Integer id);
    void deleteEmpleadoById(Integer id);
    List<Empleado> findAllByTipoEmpleado();
    Empleado findById(Integer id);
    List<Boleta> findAllBoletas();
    List<Integer> findAllDistinctBoletas();
    Map<String,String> findAllMesesBoletas();
    List<String[]> barberoConMasAtenciones();
    List<Boleta> findAllByAnoAndByMes(Integer año,Integer mes);
 }
