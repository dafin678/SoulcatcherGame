package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.core.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Knight;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Mage;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Priest;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaRepository;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PersonaInventoryRepository personaInventoryRepository;
    
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Persona createPersona(String classes) {
        Classes character = null;
        var name = "";

        if (classes.equals("knight")) {
            character = new Knight();
            name = "raiden";
        } else if (classes.equals("mage")) {
            character = new Mage();
            name = "albus";
        } else if (classes.equals("priest")) {
            character = new Priest();
            name = "althea";
        }

        var persona = new Persona(name, character.getHp(), character.getDamage(), character.getLevel(), classes);
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> getPersona(String username) {
        return personaRepository.findAll();
    }

    @Override
    public Persona upgradePersona(Persona persona) {
//        persona.getPersonaClass().upgrade();
        return persona;

    }

    @Override
    public void setDefaultPersona(HttpServletRequest request, HttpServletResponse response, int id) {
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("persona")) {
//                    cookie.setValue(Integer.toString(id));
//                    response.addCookie(cookie);
//                    cookie.setPath("/");
//                    System.out.println(id);
//                    System.out.println(cookie.getValue());
//                }
//            }
//        }
        Cookie ck = new Cookie("persona", "");
        ck.setMaxAge(0);
        response.addCookie(ck);
        Cookie newCk = new Cookie("persona", Integer.toString(id));
        response.addCookie(newCk);
    }

    @Override
    public Persona getPersonaFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("persona")) {
                    int id = Integer.parseInt(cookie.getValue());
                    return personaRepository.findById(id);
                }
            }
        }
        return null;
    }

    public Persona getPlayerPersona(Player player, HttpServletRequest request, HttpServletResponse response) {
        Persona persona = getPersonaFromCookie(request, response);
        if (persona == null) {
            PersonaInventory playerPersonaInventory = player.getPersonaInventory();
            if (playerPersonaInventory.getPersonaList().size() > 0) {
                persona = playerPersonaInventory.getPersonaList().get(0);
                Cookie ck = new Cookie("persona", Integer.toString(persona.getId()));
                response.addCookie(ck);
            } else {
                return null;
            }
        }
        return persona;
    }




}
