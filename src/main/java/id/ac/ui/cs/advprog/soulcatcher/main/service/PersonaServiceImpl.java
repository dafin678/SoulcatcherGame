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

    @Autowired
    PersonaInventoryService personaInventoryService;

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

        var persona = new Persona(name, character.getHp(), character.getDamage(), character.getLevel(), classes);
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> getPersona(String username) {
        return personaRepository.findAll();
    }

    @Override
    public Persona upgradePersona(Persona persona, int id) {
        Classes character = null;
        var name = "";

        switch (persona.getPersonaClass()) {
            case "knight":
                character = new Knight(character.getHp(), character.getDamage(), character.getLevel());
                name = "raiden";
                break;
            case "mage":
                character = new Mage(character.getHp(), character.getDamage(), character.getLevel());
                name = "albus";
                break;
            case "priest":
                character = new Priest(character.getHp(), character.getDamage(), character.getLevel());
                name = "althea";
                break;
        }

        character.upgrade();
        var newPersona = new Persona(name, character.getHp(), character.getDamage(), character.getLevel(), persona.getPersonaClass());
        newPersona.setId(id);
        return personaRepository.save(newPersona);

    }
}
