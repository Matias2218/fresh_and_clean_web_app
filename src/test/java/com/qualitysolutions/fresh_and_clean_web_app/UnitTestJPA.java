package com.qualitysolutions.fresh_and_clean_web_app;

import com.qualitysolutions.fresh_and_clean_web_app.dao.IPersonaDao;
import com.qualitysolutions.fresh_and_clean_web_app.dao.IPeticionHoraDao;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Persona;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UnitTestJPA {

    @Autowired
    IPersonaDao personaDao;
    @Autowired
    IPeticionHoraDao peticionHoraDao;
    @Autowired
    TestEntityManager entityManager;


    @Test
    public void testSavePersona() {
        Persona persona = new Persona("Matias", "Maldonado", LocalDate.now(), 'M');
        persona = entityManager.persist(persona);
        Persona personaDesdeBD = personaDao.findById(persona.getIdPersona()).orElse(null);
        assertThat(personaDesdeBD).isEqualTo(persona);
    }
    @Test
    public void testGetPersona() {
        Persona persona = new Persona("Matias", "Maldonado", LocalDate.now(), 'M');
        persona = entityManager.persist(persona);
        Persona personaDesdeBD = personaDao.findById(persona.getIdPersona()).orElse(null);
        assertThat(personaDesdeBD).isEqualTo(persona);
    }
    @Test
    public void testUpdatePersona() {
        Persona persona = new Persona("Matias", "Maldonado", LocalDate.now(), 'M');
        persona = entityManager.persist(persona);
        persona.setNombre("Juan");
        persona = entityManager.persist(persona);
        Persona personaDesdeBD = personaDao.findById(persona.getIdPersona()).orElse(null);
        assertThat(personaDesdeBD.getNombre()).isEqualTo("Juan");
    }
    @Test
    public void testDeletePersona() {
        Persona persona = new Persona("Matias", "Maldonado", LocalDate.now(), 'M');
        persona = entityManager.persist(persona);
        personaDao.delete(persona);
    }






}
