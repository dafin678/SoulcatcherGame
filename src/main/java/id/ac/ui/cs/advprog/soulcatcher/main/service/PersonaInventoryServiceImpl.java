package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
//        Persona persona = personaService.createPersona("knight");
//        addPersonaToInventory(personaInventory, persona);
        return personaInventoryRepository.save(personaInventory);
    }

    @Override
    public PersonaInventory addPersonaToInventory(PersonaInventory personaInventory, Persona persona) {
        List<Persona> personaList = personaInventory.getPersonaList();
        personaList.add(persona);
        return personaInventoryRepository.save(personaInventory);
    }

    @Override
    public boolean isPersonaDuplicate(PersonaInventory personaInventory, Persona persona) {
        List<Persona> personaList = personaInventory.getPersonaList();
        Iterator<Persona> itr = personaList.iterator();

        while(itr.hasNext()) {
            String name = itr.next().getName();
            if(name.equals(persona.getName())) {
                return true;
            }
        }
        return false;
    }

}
