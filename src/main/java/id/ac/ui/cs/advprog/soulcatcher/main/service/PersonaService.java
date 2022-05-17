package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;

import java.util.List;

public interface PersonaService {
    Persona createPersona(String classes, PersonaInventory personaInventory);
    Persona addPersona(Persona persona, PersonaInventory personaInventory);
    List<Persona> getPersona(String username);
}
