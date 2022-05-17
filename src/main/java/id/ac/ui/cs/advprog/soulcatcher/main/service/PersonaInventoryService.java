package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;

public interface PersonaInventoryService {
    PersonaInventory createPersonaInventory(String username);
    PersonaInventory addPersonaToInventory(PersonaInventory personaInventory, Persona persona);
}
