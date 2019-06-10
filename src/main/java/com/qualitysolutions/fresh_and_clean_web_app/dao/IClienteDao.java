package com.qualitysolutions.fresh_and_clean_web_app.dao;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente,Integer> {
}
