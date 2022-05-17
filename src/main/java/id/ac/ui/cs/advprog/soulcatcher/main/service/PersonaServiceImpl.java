package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.core.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Knight;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public Persona createPersona(String classes, PersonaInventory personaInventory) {
        Classes character = null;
        String name = "";
        if (classes.equals("knight")) {
            character = new Knight();
            name = "raiden";
        }
        Persona persona = new Persona(character, name);
        addPersona(persona, personaInventory);

        return personaRepository.save(persona);
    }

    @Override
    public Persona addPersona(Persona persona, PersonaInventory personaInventory) {
        List<Persona> personaList = personaInventory.getPersonaList();
        personaList.add(persona);
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> getPersona(String username) {
        return personaRepository.findAll();
    }
}
