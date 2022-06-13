package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;

public interface PlayerService {
    Player createPlayer(String name);

    Player getPlayer(String username);

}
