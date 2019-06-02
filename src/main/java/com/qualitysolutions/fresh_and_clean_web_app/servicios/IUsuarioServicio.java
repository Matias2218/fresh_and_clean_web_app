package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import java.util.List;
import java.util.Map;

public interface IUsuarioServicio {

    Empleado findById(Integer id);
    List<Boleta> findAllBoletas();
    List<Integer> findAllDistinctBoletas();
    Map<String,String> findAllMesesBoletas();
    List<String[]> barberoConMasAtenciones();
    List<Boleta> findAllByAnoAndByMes(Integer año,Integer mes);
 }
