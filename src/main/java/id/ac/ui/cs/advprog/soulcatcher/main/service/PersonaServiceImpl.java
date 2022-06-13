package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.core.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Knight;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Mage;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Priest;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
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
        } else {
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

        if (persona.getPersonaClass().equals("knight")) {
                character = new Knight(persona.getHp(), persona.getDamage(), persona.getLevel());
                name = "raiden";
        } else if (persona.getPersonaClass().equals("mage")) {
                character = new Mage(persona.getHp(), persona.getDamage(), persona.getLevel());
                name = "albus";
        } else {
                character = new Priest(persona.getHp(), persona.getDamage(), persona.getLevel());
                name = "althea";
        }

        character.upgrade();
        var newPersona = new Persona(name, character.getHp(), character.getDamage(), character.getLevel(), persona.getPersonaClass());
        newPersona.setId(id);
        return personaRepository.save(newPersona);

    }

    @Override
    public Persona updatePersonaFragment(int id, int newFragments) {
        var persona = personaRepository.findById(id);
        var oldFragments = persona.getSoulFragment();
        persona.setSoulFragment(oldFragments + newFragments);
        return persona;
    }

    @Override
    public void setDefaultPersona(Player player, int id) {
        player.setPersonaId(id);
        playerRepository.save(player);
    }

    public Persona getPlayerPersona(Player player) {
        Persona persona;
        if (player.getPersonaId() == 0) {
            var playerPersonaInventory = player.getPersonaInventory();
            if (!playerPersonaInventory.getPersonaList().isEmpty()) {
                persona = playerPersonaInventory.getPersonaList().get(0);
                setDefaultPersona(player, persona.getId());
            } else {
                return null;
            }
        } else {
            int id = player.getPersonaId();
            persona = personaRepository.findById(id);
        }
        return persona;
    }




}
