package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;


@Service
public class PersonaInventoryServiceImpl implements PersonaInventoryService {
    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaInventoryRepository personaInventoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PersonaInventory createPersonaInventory(String username) {
        var personaInventory = new PersonaInventory(username);
        return personaInventoryRepository.save(personaInventory);
    }

    @Override
    public PersonaInventory addPersonaToInventory(String name, Persona persona) {
        var personaInventory = personaInventoryRepository.findPersonaInventoriesByName(name);
        List<Persona> personaList = getPersonaList(name);
        personaList.add(persona);
        return personaInventoryRepository.save(personaInventory);
    }

    @Override
    public Persona isPersonaDuplicate(String name, Persona persona) {
        List<Persona> personaList = getPersonaList(name);
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
    public List<Persona> getPersonaList(String name) {
        var personaInventory = personaInventoryRepository.findPersonaInventoriesByName(name);
        var session = entityManager.unwrap(Session.class);
        session.close();
        return personaInventory.getPersonaList();
    }

}
