package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.ConsumableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsumableServiceImplTest {

    @Mock
    private ConsumableRepository consumableRepository;

    @InjectMocks
    private ConsumableServiceImpl consumableService;

    private Consumable consumable;

    @BeforeEach
    public void setUp() {
        String name = "Apple";
        String description = "Memulihkan 100 HP points";
        consumable = new Consumable(name, description);
    }

    @Test
    void testCreateConsumable() {
        consumableService.createConsumable("Apple", "Memulihkan 100 HP points");
        verify(consumableRepository, times(1)).save(consumable);
    }

    @Test
    void testGetConsumables() {
        List<Consumable> consumableList = consumableRepository.findAll();
        lenient().when(consumableService.getConsumables()).thenReturn(consumableList);
        List<Consumable> consumableListResult = consumableService.getConsumables();
        assertIterableEquals(consumableList, consumableListResult);
    }

}
