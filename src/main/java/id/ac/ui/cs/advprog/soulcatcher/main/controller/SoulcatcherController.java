package id.ac.ui.cs.advprog.soulcatcher.main.controller;

import id.ac.ui.cs.advprog.soulcatcher.Security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.service.InventoryService;
import id.ac.ui.cs.advprog.soulcatcher.main.service.PlayerService;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
import id.ac.ui.cs.advprog.soulcatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

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

    private Player player;

    @GetMapping("/dashboard")
    public String index(@CookieValue(name="jwttoken") String token) {

        String username = jwtUtils.getUserNameFromJwtToken(token);

        var user = userService.getUserByUsername(username);
        User userValue = null;

        if(user.isPresent()) {
            userValue = user.get();
            player = playerService.getPlayer(userValue.getUsername());
            userValue.setPlayer(player);
            userService.save(user);
        }

        return "dashboard";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        if(player != null) {
            model.addAttribute("consumables", player.getPlayerInventory().getConsumableList());
            return "inventory";
        } else {
            return "redirect:/dashboard";
        }
    }

    @GetMapping(value = "/inventory/{consumableId}/delete-consumable")
    public String deleteConsumable(@PathVariable Integer consumableId) {
        var inventory = player.getPlayerInventory();
        inventoryService.deleteConsumableFromInventory(inventory, consumableId);

        return "redirect:/inventory";
    }
}
