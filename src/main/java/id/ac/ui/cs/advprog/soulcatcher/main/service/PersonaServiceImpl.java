package id.ac.ui.cs.advprog.soulcatcher.main.service;


import id.ac.ui.cs.advprog.soulcatcher.main.core.factory.personafactory.PersonaFactory;
import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.Knight;
import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.PersonaType;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PersonaInventoryRepository personaInventoryRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Persona createPersona(String classes) {

        String name = "raiden";

        Classes character = PersonaFactory.createPersona(classes);

        Persona persona = new Persona(character, name);
        return personaRepository.save(persona);
    }



    @Override
    public List<Persona> getPersona(String username) {
        return personaRepository.findAll();
    }
}
