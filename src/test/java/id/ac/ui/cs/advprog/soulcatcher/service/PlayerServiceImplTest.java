package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private InventoryServiceImpl inventoryService;

    @Spy
    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player player;

    @BeforeEach
    public void setUp() {
        String name = "Bintang";
        player = new Player();
        player.setName(name);
        player.setUsername(name);
    }

    @Test
    void testCreatePlayer() {
        playerService.createPlayer("Bintang");
        verify(inventoryService, times(1)).createInventory("Bintang");
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void testGetPlayer() {
        playerService.getPlayer("A");
        verify(playerRepository, times(1)).findPlayerByUsername("A");
        lenient().when(playerRepository.findPlayerByUsername("B")).thenReturn(null);
        playerService.getPlayer("B");
        verify(playerService, times(1)).createPlayer("B");
    }
}
