package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.TipoEmpleado;

import java.util.List;
import java.util.Map;

public interface IUsuarioServicio {

    List<Empleado> findAllEmpleadoOrderByEstaActivo();
    List<TipoEmpleado> findAllTipoEmpleados();
    Empleado saveEmpleado(Empleado empleado);
    Boolean activeEmpleado(Integer id);
    Boolean desactiveEmpleado(Integer id);
    void deleteEmpleadoById(Integer id);
    Empleado findById(Integer id);
    List<Boleta> findAllBoletas();
    List<Integer> findAllDistinctBoletas();
    Map<String,String> findAllMesesBoletas();
    List<String[]> barberoConMasAtenciones();
    List<Boleta> findAllByAnoAndByMes(Integer año,Integer mes);
 }
