package id.ac.ui.cs.advprog.soulcatcher.main.service;


import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;

public interface InventoryService {
    Inventory createInventory(String name);

    Inventory addConsumableToInventory(Inventory inventory, Consumable consumable);

    Inventory deleteConsumableFromInventory(Inventory inventory, Integer consumableId);
}
