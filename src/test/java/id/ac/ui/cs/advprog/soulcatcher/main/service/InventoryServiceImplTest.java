package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    ConsumableService consumableService;

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        String name = "Bintang";
        inventory = new Inventory();
        inventory.setName(name);
    }

    @Test
    void testCreateInventory() {
        Consumable sunsettia = new Consumable("Sunsettia", "Memulihkan 100 HP points");
        Consumable apple = new Consumable("Apple", "Memulihkan 100 mana points");
        Consumable starshroom = new Consumable("Starshroom ", "Memulihkan 200 HP points");

        inventory.getConsumableList().add(sunsettia);
        inventory.getConsumableList().add(apple);
        inventory.getConsumableList().add(starshroom);

        lenient().when(inventoryService.createInventory("Bintang")).thenReturn(inventory);
        Inventory inventory1 = inventoryService.createInventory("Bintang");
        assertIterableEquals(inventory.getConsumableList(), inventory1.getConsumableList());
    }

    @Test
    void testAddConsumableToInventory() {
        Consumable consumable = new Consumable("A", "B");
        inventory.getConsumableList().add(consumable);

        Inventory inventory1 = new Inventory("Adit");
        inventoryService.addConsumableToInventory(inventory1, consumable);

        assertIterableEquals(inventory.getConsumableList(), inventory1.getConsumableList());
    }

    @Test
    void testDeleteConsumableToInventory() {
        Consumable consumable = new Consumable("A", "B");
        consumable.setId(0);
        inventory.getConsumableList().add(consumable);
        inventoryService.deleteConsumableFromInventory(inventory, consumable.getId());

        Inventory inventory1 = new Inventory("Adit");

        assertIterableEquals(inventory.getConsumableList(), inventory1.getConsumableList());
    }

}
