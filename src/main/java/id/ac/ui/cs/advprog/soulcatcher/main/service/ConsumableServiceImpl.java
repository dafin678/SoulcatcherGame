package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.ConsumableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class ConsumableServiceImpl implements ConsumableService {

    @Autowired
    ConsumableRepository consumableRepository;

    Random random = new Random();

    @Override
    public Consumable createConsumable(String name, String description) {
        var consumable = new Consumable(name, description);
        return consumableRepository.save(consumable);
    }

    @Override
    public List<Consumable> getConsumables() {
        return consumableRepository.findAll();
    }

    @Override
    public Consumable createRandomConsumable() {
        Integer randInt = random.nextInt(3);

        if(randInt.equals(0)) {
            return createConsumable("Sunsettia", "Memulihkan 100 HP points");

        } else if(randInt.equals(1)) {
            return createConsumable("Apple", "Memulihkan 100 mana points");

        } else {
            return createConsumable("Starshroom ", "Memulihkan 200 HP points");
        }
    }
}
