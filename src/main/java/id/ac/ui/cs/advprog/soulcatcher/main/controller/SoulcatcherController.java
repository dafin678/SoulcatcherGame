package id.ac.ui.cs.advprog.soulcatcher.main.controller;

import id.ac.ui.cs.advprog.soulcatcher.Security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.service.InventoryService;
import id.ac.ui.cs.advprog.soulcatcher.main.service.PlayerService;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
import id.ac.ui.cs.advprog.soulcatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String index(@CookieValue(name="jwttoken", defaultValue = "") String token) {
        String username;
        try {
            username = jwtUtils.getUserNameFromJwtToken(token);
        } catch (Exception e) {
            return "redirect:/login";
        }
        var user = userService.getUserByUsername(username);
        User userValue = null;

        if(user.isPresent()) {
            userValue = user.get();
            player = playerService.getPlayer(userValue.getUsername());
            userValue.setPlayer(player);
            userService.save(user);
        } else {
            return "redirect:/login";
        }

        return "dashboard";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        if(player != null) {
            model.addAttribute("consumables", player.getPlayerInventory().getConsumableList());
            return "inventory";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/inventory/{consumableId}/delete-consumable")
    public String deleteConsumable(@PathVariable Integer consumableId) {
        if(player == null) {
            return "redirect:/login";
        }
        var inventory = player.getPlayerInventory();
        inventoryService.deleteConsumableFromInventory(inventory, consumableId);

        return "redirect:/inventory";
    }
}
