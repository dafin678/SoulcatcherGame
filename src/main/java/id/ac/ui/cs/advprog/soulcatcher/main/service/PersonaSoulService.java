package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaSoul;

import java.util.List;

public interface PersonaSoulService {
    PersonaSoul createPersonaSoul();

    List<PersonaSoul> getPersonaSouls();
}
