package id.ac.ui.cs.advprog.soulcatcher.main.controller;

import id.ac.ui.cs.advprog.soulcatcher.authentication.Security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.authentication.service.UserServiceImpl;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.service.InventoryServiceImpl;
import id.ac.ui.cs.advprog.soulcatcher.main.service.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class SoulcatcherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private PlayerServiceImpl playerService;

    @MockBean
    private InventoryServiceImpl inventoryService;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private SoulcatcherController controller;

    @Test
    public void whenDashboardIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/dashboard"))
                .andExpect(handler().methodName("index"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void whenDashboardIsAccessedShouldReturnDashboard() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        player.setPlayerInventory(new Inventory("Bintang"));
        ReflectionTestUtils.setField(controller, "player", player);

        User user = new User("Bintang", "Bintang@mail.com", "Bintang");

        when(jwtUtils.getUserNameFromJwtToken("")).thenReturn("Bintang");
        when(userService.getUserByUsername("Bintang")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/dashboard"))
                .andExpect(handler().methodName("index"))
                .andExpect(view().name("dashboard"));
        verify(playerService, times(1)).getPlayer("Bintang");
        verify(userService, times(1)).save(Optional.of(user));
    }

    @Test
    public void whenInventoryIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/inventory"))
                .andExpect(handler().methodName("inventory"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void whenInventoryIsAccessedShouldReturnConsumablesList() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        player.setPlayerInventory(new Inventory("Bintang"));
        ReflectionTestUtils.setField(controller, "player", player);

        mockMvc.perform(get("/inventory"))
                .andExpect(handler().methodName("inventory"))
                .andExpect(model().attributeExists("consumables"))
                .andExpect(view().name("inventory"));
    }

    @Test
    public void whenDeleteConsumableIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/inventory/1/delete-consumable"))
                .andExpect(handler().methodName("deleteConsumable"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void whenDeleteConsumableAccessedShouldCallInventoryService() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        player.setPlayerInventory(new Inventory("Bintang"));
        ReflectionTestUtils.setField(controller, "player", player);

        mockMvc.perform(get("/inventory/1/delete-consumable"))
                .andExpect(handler().methodName("deleteConsumable"))
                .andExpect(view().name("redirect:/inventory"));
        verify(inventoryService, times(1)).deleteConsumableFromInventory(player.getPlayerInventory(),1);
    }

}
