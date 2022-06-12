package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;

import java.util.List;

public interface PersonaService {

    Persona createPersona(String classes);

    List<Persona> getPersona(String username);

    Persona upgradePersona(Persona persona, int id);

}
