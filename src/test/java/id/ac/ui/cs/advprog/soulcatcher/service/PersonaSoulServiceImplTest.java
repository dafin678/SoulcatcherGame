package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaSoul;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaSoulRepository;
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
class PersonaSoulServiceImplTest {

    @Mock
    private PersonaSoulRepository personaSoulRepository;

    @InjectMocks
    private PersonaSoulServiceImpl personaSoulService;

    private PersonaSoul personaSoul;

    @BeforeEach
    public void setUp() {
        personaSoul = new PersonaSoul();
    }

    @Test
    void testCreatePersonaSoul() {
        personaSoulService.createPersonaSoul();
        verify(personaSoulRepository, times(1)).save(personaSoul);
    }

    @Test
    void testGetPersonaSouls() {
        List<PersonaSoul> personaSoulList = personaSoulRepository.findAll();
        lenient().when(personaSoulService.getPersonaSouls()).thenReturn(personaSoulList);
        List<PersonaSoul> personaSoulListResult = personaSoulService.getPersonaSouls();
        assertIterableEquals(personaSoulList, personaSoulListResult);
    }

}
