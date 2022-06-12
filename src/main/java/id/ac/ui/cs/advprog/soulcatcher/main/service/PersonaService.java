package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PersonaService {

    Persona createPersona(String classes);

    List<Persona> getPersona(String username);

    Persona upgradePersona(Persona persona);

    void setDefaultPersona(HttpServletRequest request, HttpServletResponse response, int id);

    Persona getPersonaFromCookie(HttpServletRequest request, HttpServletResponse response);

    Persona getPlayerPersona(Player player, HttpServletRequest request, HttpServletResponse response);

}
