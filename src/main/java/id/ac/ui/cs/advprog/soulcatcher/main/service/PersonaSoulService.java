package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaSoul;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;

import java.util.List;

public interface PersonaSoulService {
    PersonaSoul createPersonaSoul();

    List<PersonaSoul> getPersonaSouls();
}
