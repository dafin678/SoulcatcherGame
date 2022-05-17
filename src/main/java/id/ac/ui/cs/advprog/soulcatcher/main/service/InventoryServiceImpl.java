package id.ac.ui.cs.advprog.soulcatcher.main.service;


import id.ac.ui.cs.advprog.soulcatcher.main.model.*;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.ConsumableRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.InventoryRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ConsumableRepository consumableRepository;

    @Autowired
    ConsumableService consumableService;

    @Autowired
    PersonaSoulService personaSoulService;

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaInventoryService personaInventoryService;

    @Override
    public Inventory createInventory(String username) {
        var playerInventory = new Inventory(username);

        var sunsettia = consumableService.createConsumable("Sunsettia", "Memulihkan 100 HP points");
        var apple = consumableService.createConsumable("Apple", "Memulihkan 100 mana points");
        var starshroom = consumableService.createConsumable("Starshroom ", "Memulihkan 200 HP points");
        var personaSoul = personaSoulService.createPersonaSoul();

        addConsumableToInventory(playerInventory, sunsettia);
        addConsumableToInventory(playerInventory, starshroom);
        addConsumableToInventory(playerInventory, apple);
        addPersonaSoulToInventory(playerInventory, personaSoul);

        return inventoryRepository.save(playerInventory);
    }

    @Override
    public Inventory addConsumableToInventory(Inventory inventory, Consumable consumable) {
        List<Consumable> consumableList = inventory.getConsumableList();
        consumableList.add(consumable);

        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory addPersonaSoulToInventory(Inventory inventory, PersonaSoul personaSoul) {
        List<PersonaSoul> personaSoulList = inventory.getPersonaSoulList();
        personaSoulList.add(personaSoul);

        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory deleteConsumableFromInventory(Inventory inventory, Integer consumableId) {
        List<Consumable> consumableList = inventory.getConsumableList();
        Iterator<Consumable> itr = consumableList.iterator();

        while(itr.hasNext()) {
            Integer id = itr.next().getId();
            if(id.equals(consumableId)) {
                itr.remove();
            }
        }

        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory deletePersonaSoulFromInventory(Inventory inventory, Integer personaSoulId) {
        List<PersonaSoul> personaSoulList = inventory.getPersonaSoulList();
        Iterator<PersonaSoul> itr = personaSoulList.iterator();

        while(itr.hasNext()) {
            Integer id = itr.next().getId();
            if(id.equals(personaSoulId)) {
                itr.remove();
            }
        }

        return inventoryRepository.save(inventory);
    }

    @Override
    public String posses(Integer personaSoulId, Player player) {
        Random rand = new Random();
        String[] classes = {"knight", "mage", "priest"};
        String randomClass = classes[rand.nextInt(classes.length)];
        Persona persona = personaService.createPersona(randomClass);
        Boolean isDuplicate = personaInventoryService.isPersonaDuplicate(player.getPersonaInventory(), persona);
        deletePersonaSoulFromInventory(player.getPlayerInventory(), personaSoulId);

        if(!isDuplicate) {
            personaInventoryService.addPersonaToInventory(player.getPersonaInventory(), persona);
            return "success";

        } else {
            return "duplicate";
        }
    }

}
