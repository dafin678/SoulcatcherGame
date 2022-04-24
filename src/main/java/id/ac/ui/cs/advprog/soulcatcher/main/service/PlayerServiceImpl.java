package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.InventoryRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public Player createPlayer(String username) {
        var player = new Player(username, username);

        var inventory = inventoryService.createInventory(username);

        player.setPlayerInventory(inventory);

        return playerRepository.save(player);
    }

    @Override
    public Player getPlayer(String username) {
        Player player = playerRepository.findPlayerByUsername(username);
        if (player == null) {
            player = createPlayer(username);
        }
        return player;
    }
}
