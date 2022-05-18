package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        var personaInventory = new PersonaInventory(username);
        return personaInventoryRepository.save(personaInventory);
    }

    @Override
    public PersonaInventory addPersonaToInventory(PersonaInventory personaInventory, Persona persona) {
        List<Persona> personaList = personaInventory.getPersonaList();
        personaList.add(persona);
        return personaInventoryRepository.save(personaInventory);
    }

    @Override
    public Persona isPersonaDuplicate(PersonaInventory personaInventory, Persona persona) {
        List<Persona> personaList = personaInventory.getPersonaList();
        Iterator<Persona> itr = personaList.iterator();

        while(itr.hasNext()) {
            var personaItr = itr.next();
            if(personaItr.getName().equals(persona.getName())) {
                return personaItr;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public List<Persona> getPersonaList(PersonaInventory personaInventory) {
        return personaInventory.getPersonaList();
    }

}
