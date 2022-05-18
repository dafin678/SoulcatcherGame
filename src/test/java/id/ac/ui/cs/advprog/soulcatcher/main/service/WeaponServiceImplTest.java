package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaSoul;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Weapon;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaSoulRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.WeaponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeaponServiceImplTest {
    @Mock
    private WeaponRepository weaponRepository;

    @InjectMocks
    private WeaponServiceImpl weaponService;

    private Weapon weapon;

    private Inventory inventories;

    @BeforeEach
    public void setUp() {
        weapon= new Weapon("Napoleon","Sword",250);
    }

    @Test
    void testCreateWeapon() {
        weaponService.createWeapon(weapon.getWeaponName(),weapon.getWeaponType());
        verify(weaponRepository, times(1)).save(weapon);
    }

    @Test
    void testGetWeapon() {
        List<Weapon> weaponList = weaponRepository.findAll();
        lenient().when(weaponService.getWeapon()).thenReturn(weaponList);
        List<Weapon> weaponListResult = weaponService.getWeapon();
        assertIterableEquals(weaponList, weaponListResult);
    }
}
