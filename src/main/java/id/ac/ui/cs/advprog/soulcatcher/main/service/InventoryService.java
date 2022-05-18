package id.ac.ui.cs.advprog.soulcatcher.main.service;


import id.ac.ui.cs.advprog.soulcatcher.main.model.*;

public interface InventoryService {
    Inventory createInventory(String name);

    Inventory addWeaponToInventory(Inventory inventory, Weapon weapon);

    Inventory deleteWeaponToInventory(Inventory inventory, String weaponName);

    Inventory addConsumableToInventory(Inventory inventory, Consumable consumable);

    Inventory addPersonaSoulToInventory(Inventory inventory, PersonaSoul personaSoul);

    Inventory deleteConsumableFromInventory(Inventory inventory, Integer consumableId);

    Inventory deletePersonaSoulFromInventory(Inventory inventory, Integer personaSoulId);

    String posses(Integer personaSoulId, Player player);
}
