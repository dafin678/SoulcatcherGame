package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.core.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Knight;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Mage;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Priest;
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
        Classes character = null;
        var name = "";

        if (classes.equals("knight")) {
            character = new Knight();
            name = "raiden";
        } else if (classes.equals("mage")) {
            character = new Mage();
            name = "albus";
        } else if (classes.equals("priest")) {
            character = new Priest();
            name = "althea";
        }

        var persona = new Persona(character, name);
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> getPersona(String username) {
        return personaRepository.findAll();
    }

    @Override
    public Persona upgradePersona(Persona persona) {
        persona.getPersonaClass().upgrade();
        return persona;

    }
}
