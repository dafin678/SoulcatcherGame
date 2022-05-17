package id.ac.ui.cs.advprog.soulcatcher.main.service;


import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaSoul;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;

public interface InventoryService {
    Inventory createInventory(String name);

    Inventory addConsumableToInventory(Inventory inventory, Consumable consumable);

    Inventory addPersonaSoulToInventory(Inventory inventory, PersonaSoul personaSoul);

    Inventory deleteConsumableFromInventory(Inventory inventory, Integer consumableId);

    Inventory deletePersonaSoulFromInventory(Inventory inventory, Integer personaSoulId);

    String posses(Integer personaSoulId, Player player);
}
