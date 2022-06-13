package id.ac.ui.cs.advprog.soulcatcher.main.controller;

import id.ac.ui.cs.advprog.soulcatcher.authentication.security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.main.core.vo.CharDetail;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Persona;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.model.dto.BattleRewardDTO;
import id.ac.ui.cs.advprog.soulcatcher.main.service.*;
import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaSoulService personaSoulService;

    @Autowired
    ConsumableService consumableService;

    private Player player;

    private static final String LOGIN_REDIRECT_VAR = "redirect:/login";

    private static final String SOUL_FRAGMENT_VAR = "Soul Fragment";


    @GetMapping("/dashboard")
    public String index(@CookieValue(name="jwttoken", defaultValue = "") String token, HttpServletRequest request, HttpServletResponse response, Model model) {
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
            var persona = personaService.getPlayerPersona(player);
            if (persona != null) {
                model.addAttribute(persona);
            }
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
        return "redirect:/dashboard";
    }

    @GetMapping("/inventory/weapons")
    public String listWeapon(Model model) {
        if(player != null) {
            model.addAttribute("weapons", player.getPlayerInventory().getWeaponList());
            return "weapon_list";
        } else {
            return LOGIN_REDIRECT_VAR;
        }
    }

    @GetMapping(value = "/inventory/{weaponName}/delete-weapon")
    public String deleteWeapon(@PathVariable String weaponName) {
        if(player == null) {
            return LOGIN_REDIRECT_VAR;
        }
        var inventory = player.getPlayerInventory();
        inventoryService.deleteWeaponToInventory(inventory, weaponName);

        return "redirect:/inventory/weapons";
    }

    @GetMapping(value = "/persona-inventory")
    public String listPersona(Model model) {
        if(player != null) {
            model.addAttribute("personas", personaInventoryService.getPersonaList(player.getName()));
            return "persona_list";
        } else {
            return LOGIN_REDIRECT_VAR;
        }
    }

    @GetMapping(value = "/equip/{id}")
    public String equip(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        personaService.setDefaultPersona(player, id);
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/battle")
    public String battle() {
        if(player == null) {
            return LOGIN_REDIRECT_VAR;
        }
        return "battle";
    }

    @RequestMapping(path = "/char-details", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<CharDetail> getCharDetails(HttpServletRequest request, HttpServletResponse response) {
        var persona = personaService.getPlayerPersona(player);
        var character = new CharDetail(persona.getId(), persona.getHp(), persona.getDamage(), persona.getName());
        return ResponseEntity.ok(character);
    }

    @PostMapping(value = "/update-fragment/{newFragment}", produces = "application/json")
    public @ResponseBody ResponseEntity<BattleRewardDTO> updateFragment(@PathVariable Integer newFragment, @RequestBody Integer personaId) {
        personaService.updatePersonaFragment(personaId, newFragment);

        List<String> name = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();

        name.add(SOUL_FRAGMENT_VAR);
        quantity.add(newFragment);

        return ResponseEntity.ok(new BattleRewardDTO(name, quantity));
    }

    @PostMapping(value = "/assign-persona-soul", produces = "application/json")
    public @ResponseBody ResponseEntity<BattleRewardDTO> assignSoulReward() {
        inventoryService.addPersonaSoulToInventory(player.getPlayerInventory(), personaSoulService.createPersonaSoul());

        List<String> name = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();

        name.add(SOUL_FRAGMENT_VAR);
        quantity.add(3);

        name.add("Persona Soul");
        quantity.add(1);

        return ResponseEntity.ok(new BattleRewardDTO(name, quantity));
    }

    @PostMapping(value = "/assign-consumable", produces = "application/json")
    public @ResponseBody ResponseEntity<BattleRewardDTO> assignConsumableReward() {
        var consumableReward = consumableService.createRandomConsumable();
        inventoryService.addConsumableToInventory(player.getPlayerInventory(), consumableReward);

        List<String> name = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();

        name.add(SOUL_FRAGMENT_VAR);
        quantity.add(3);

        name.add(consumableReward.getName());
        quantity.add(1);

        return ResponseEntity.ok(new BattleRewardDTO(name, quantity));
    }

}
