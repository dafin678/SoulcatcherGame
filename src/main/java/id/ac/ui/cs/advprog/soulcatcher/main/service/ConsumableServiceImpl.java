package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.ConsumableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsumableServiceImpl implements ConsumableService {

    @Autowired
    ConsumableRepository consumableRepository;

    @Override
    public Consumable createConsumable(String name, String description) {
        var consumable = new Consumable(name, description);
        return consumableRepository.save(consumable);
    }

    @Override
    public List<Consumable> getConsumables() {
        return consumableRepository.findAll();
    }
}
