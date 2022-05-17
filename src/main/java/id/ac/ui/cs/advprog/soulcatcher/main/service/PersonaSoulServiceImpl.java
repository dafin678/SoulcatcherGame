package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.*;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaSoulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaSoulServiceImpl implements PersonaSoulService {

    @Autowired
    PersonaSoulRepository personaSoulRepository;

    @Override
    public PersonaSoul createPersonaSoul() {
        var personaSoul = new PersonaSoul();
        return personaSoulRepository.save(personaSoul);
    }

    @Override
    public List<PersonaSoul> getPersonaSouls() {
        return personaSoulRepository.findAll();
    }
}
