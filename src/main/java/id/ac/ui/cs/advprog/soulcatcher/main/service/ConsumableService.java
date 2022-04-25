package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;

import java.util.List;

public interface ConsumableService {
    Consumable createConsumable(String name, String description);

    List<Consumable> getConsumables();
}
