package com.qualitysolutions.fresh_and_clean_web_app.dao;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface IBoletaDao extends CrudRepository<Boleta,Integer>
{
    @Query(value = "select distinct (YEAR(b.fecha_boleta)) from boletas b",nativeQuery = true)
    List<Integer> findAllDistinctBoletas();

    @Query(value = "select distinct (month(b.fecha_boleta)),(monthname(b.fecha_boleta)) from boletas b",nativeQuery = true)
    List<Object[]> findAllMesesBoletas();

    @Query(value = "select * from boletas b where year(b.fecha_boleta)=?1 and  month(b.fecha_boleta)=?2",nativeQuery = true)
    List<Boleta> findAllByAnoAndByMes(Integer año,Integer mes);
}
