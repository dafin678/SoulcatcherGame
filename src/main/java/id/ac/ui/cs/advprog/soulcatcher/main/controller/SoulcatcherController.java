package id.ac.ui.cs.advprog.soulcatcher.main.controller;

import id.ac.ui.cs.advprog.soulcatcher.authentication.security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.service.InventoryService;
import id.ac.ui.cs.advprog.soulcatcher.main.service.PersonaInventoryService;
import id.ac.ui.cs.advprog.soulcatcher.main.service.PlayerService;
import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SoulcatcherController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserService userService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    PersonaInventoryService personaInventoryService;

    private Player player;

    private Persona persona;



    private static final String LOGIN_REDIRECT_VAR = "redirect:/login";

    @GetMapping("/dashboard")
    public String index(@CookieValue(name="jwttoken", defaultValue = "") String token) {
        String username;
        try {
            username = jwtUtils.getUserNameFromJwtToken(token);
        } catch (Exception e) {
            return LOGIN_REDIRECT_VAR;
        }
        var user = userService.getUserByUsername(username);
        User userValue = null;

        if(user.isPresent()) {
            userValue = user.get();
            player = playerService.getPlayer(userValue.getUsername());
            userValue.setPlayer(player);
            userService.save(user);
        } else {
            return LOGIN_REDIRECT_VAR;
        }

        return "dashboard";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        if(player != null) {
            model.addAttribute("consumables", player.getPlayerInventory().getConsumableList());
            return "inventory";
        } else {
            return LOGIN_REDIRECT_VAR;
        }
    }

    @GetMapping(value = "/inventory/{consumableId}/delete-consumable")
    public String deleteConsumable(@PathVariable Integer consumableId) {
        if(player == null) {
            return LOGIN_REDIRECT_VAR;
        }
        var inventory = player.getPlayerInventory();
        inventoryService.deleteConsumableFromInventory(inventory, consumableId);

        return "redirect:/inventory";
    }

    @GetMapping("/inventory/persona-souls")
    public String personaSouls(Model model) {
        if(player != null) {
            model.addAttribute("souls", player.getPlayerInventory().getPersonaSoulList());
            return "persona_souls_list";
        } else {
            return LOGIN_REDIRECT_VAR;
        }
    }

    @GetMapping(value = "/inventory/{soulId}/delete-soul")
    public String deleteSoul(@PathVariable Integer soulId) {
        if(player == null) {
            return LOGIN_REDIRECT_VAR;
        }
        var inventory = player.getPlayerInventory();
        inventoryService.deletePersonaSoulFromInventory(inventory, soulId);

        return "redirect:/inventory/persona-souls";
    }

    @GetMapping(value = "/inventory/posses/{soulId}", produces = "application/json")
    public @ResponseBody ResponseEntity<String> posses(@PathVariable Integer soulId) {
            String result = inventoryService.posses(soulId, player);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/inventory/upgrade")
    public String upgradePersona(Model model){
        if (persona.getSoulFragment() < 2){
            return LOGIN_REDIRECT_VAR;
        }
        return "redirect:/inventory/upgrade";
    }

    @GetMapping("/inventory/weapons")
    public String listWeapon(Model model) {
        if(player != null) {
            model.addAttribute("weapons", player.getPlayerInventory().getWeaponList());
            return "weapon_list";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/inventory/{weaponName}/delete-weapon")
    public String deleteWeapon(@PathVariable String weaponName) {
        if(player == null) {
            return "redirect:/login";
        }
        var inventory = player.getPlayerInventory();
        inventoryService.deleteWeaponToInventory(inventory, weaponName);

        return "redirect:/inventory/weapons";
    }

    @GetMapping(value = "/persona-inventory")
    public String listPersona(Model model) {
        if(player != null) {
            model.addAttribute("personas", player.getPersonaInventory().getPersonaList());
            return "persona_list";
        } else {
            return LOGIN_REDIRECT_VAR;
        }
    }
}
