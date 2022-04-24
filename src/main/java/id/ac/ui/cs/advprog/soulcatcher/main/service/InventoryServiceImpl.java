package id.ac.ui.cs.advprog.soulcatcher.main.service;


import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.ConsumableRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ConsumableRepository consumableRepository;

    @Autowired
    ConsumableService consumableService;

    @Override
    public Inventory createInventory(String username) {
        Inventory playerInventory = new Inventory(username);

        Consumable sunsettia = consumableService.createConsumable("Sunsettia", "Memulihkan 100 HP points");
        Consumable apple = consumableService.createConsumable("Apple", "Memulihkan 100 mana points");
        Consumable starshroom = consumableService.createConsumable("Starshroom ", "Memulihkan 200 HP points");

        addConsumableToInventory(playerInventory, sunsettia);
        addConsumableToInventory(playerInventory, starshroom);
        addConsumableToInventory(playerInventory, apple);

        return inventoryRepository.save(playerInventory);
    }

    @Override
    public Inventory addConsumableToInventory(Inventory inventory, Consumable consumable) {
        List<Consumable> consumableList = inventory.getConsumableList();
        consumableList.add(consumable);

        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory deleteConsumableFromInventory(Inventory inventory, Integer consumableId) {
        Consumable consumable = consumableRepository.getById(consumableId);
        List<Consumable> consumableList = inventory.getConsumableList();
        Iterator<Consumable> itr = consumableList.iterator();

        while(itr.hasNext()) {
            Integer id = itr.next().getId();
            if(id == consumableId) {
                itr.remove();
            }
        }

        return inventoryRepository.save(inventory);
    }
}
