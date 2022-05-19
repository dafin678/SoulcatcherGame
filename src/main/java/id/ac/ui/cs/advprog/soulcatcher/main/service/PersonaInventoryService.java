package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;

import java.util.List;

public interface PersonaInventoryService {
    PersonaInventory createPersonaInventory(String username);
    PersonaInventory addPersonaToInventory(String name, Persona persona);
    Persona isPersonaDuplicate(String name, Persona persona);
    List<Persona> getPersonaList(String name);
}
