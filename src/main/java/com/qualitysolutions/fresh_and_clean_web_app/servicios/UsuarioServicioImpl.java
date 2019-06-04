package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.dao.IBoletaDao;
import com.qualitysolutions.fresh_and_clean_web_app.dao.IEmpleadoDao;
import com.qualitysolutions.fresh_and_clean_web_app.dao.IPersonaDao;
import com.qualitysolutions.fresh_and_clean_web_app.dao.ITipoEmpleadoDao;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.TipoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    @Autowired
    IEmpleadoDao empleadoDao;
    @Autowired
    IBoletaDao boletaDao;
    @Autowired
    ITipoEmpleadoDao tipoEmpleadoDao;
    @Autowired
    IPersonaDao personaDao;


    @Override
    public List<Empleado> findAllEmpleadoOrderByEstaActivo() {
        return empleadoDao.findAllByOrderByEstaActivoDesc();
    }

    @Override
    public List<TipoEmpleado> findAllTipoEmpleados() {
        return (List<TipoEmpleado>) tipoEmpleadoDao.findAll();
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    @Transactional
    public Boolean activeEmpleado(Integer id) {
        empleadoDao.activeEmpleado(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean desactiveEmpleado(Integer id) {
        empleadoDao.desactiveEmpleado(id);
        return true;
    }

    @Override
    public void deleteEmpleadoById(Integer id) {
        empleadoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Integer id) {
        return empleadoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> findAllBoletas() {
        return (List<Boleta>) boletaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> findAllDistinctBoletas() {
        return boletaDao.findAllDistinctBoletas();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String,String> findAllMesesBoletas() {
        Map<String,String> mesesReturn = new HashMap<>();
        List<Object[]> meses = boletaDao.findAllMesesBoletas();
        meses.stream().forEach(o -> {
            mesesReturn.put(o[0].toString(),o[1].toString());
        });
        return mesesReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String[]> barberoConMasAtenciones() {
        return personaDao.barberoConMasAntenciones();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> findAllByAnoAndByMes(Integer año,Integer mes) {
        return boletaDao.findAllByAnoAndByMes(año,mes);
    }
}
