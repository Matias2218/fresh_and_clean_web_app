package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IUsuarioServicio {


    PeticionHora savePeticion(PeticionHora peticionHora);
    PeticionHora findByIdPeticion(Integer id);
    Page<PeticionHora> findAllPeticionHoras(Pageable pageable,Integer idEmpleado);
    Page<PeticionHora> findAllPeticionHorasEspera(Pageable pageable,Integer idEmpleado);
    Page<PeticionHora> findByPeticionHorasEspera(Pageable pageable,Integer idEmpleado,String horaAtencion);
    Page<PeticionHora> findByFechaIgualMayorAceptada(Pageable pageable,Integer idEmpleado,String horaAtencion);
    Boolean rechazarHora(Integer id);
    Boolean horaRelizada(Integer id);
    Boolean horaAceptada(Integer id);
    List<Servicio> findAllServicio();
    List<Servicio> findAllServicioById(ArrayList<Integer> ids);
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
    Boleta saveBoleta(Boleta boleta);
 }
