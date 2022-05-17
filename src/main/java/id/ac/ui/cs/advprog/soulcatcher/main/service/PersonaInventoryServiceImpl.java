package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.core.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaInventoryServiceImpl implements PersonaInventoryService {
    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaInventoryRepository personaInventoryRepository;

    @Override
    public PersonaInventory createPersonaInventory(String username) {
        PersonaInventory personaInventory = new PersonaInventory(username);

        Persona persona = personaService.createPersona("knight", personaInventory);
//        addPersonaToInventory(personaInventory, persona);
        return personaInventoryRepository.save(personaInventory);
    }

    //yang ini gak kepake dulu sementara
    @Override
    public PersonaInventory addPersonaToInventory(PersonaInventory personaInventory, Persona persona) {
        List<Persona> personaList = personaInventory.getPersonaList();
//        System.out.println(persona.getName());
        personaList.add(persona);
        return personaInventoryRepository.save(personaInventory);
    }

}
