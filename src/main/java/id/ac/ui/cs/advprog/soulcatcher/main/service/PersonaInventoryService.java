package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;

import java.util.List;

public interface PersonaInventoryService {
    PersonaInventory createPersonaInventory(String username);
    PersonaInventory addPersonaToInventory(PersonaInventory personaInventory, Persona persona);
    Persona isPersonaDuplicate(PersonaInventory personaInventory, Persona persona);
    List<Persona> getPersonaList(PersonaInventory personaInventory);
}
