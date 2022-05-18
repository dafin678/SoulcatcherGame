package id.ac.ui.cs.advprog.soulcatcher.main.controller;

import id.ac.ui.cs.advprog.soulcatcher.authentication.security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.authentication.service.UserServiceImpl;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Inventory;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.service.InventoryServiceImpl;
import id.ac.ui.cs.advprog.soulcatcher.main.service.PlayerServiceImpl;
import id.ac.ui.cs.advprog.soulcatcher.main.service.WeaponServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class SoulcatcherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private PlayerServiceImpl playerService;

    @MockBean
    private InventoryServiceImpl inventoryService;

    @MockBean
    private WeaponServiceImpl weaponService;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private SoulcatcherController controller;

    @Test
    void whenDashboardIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/dashboard"))
                .andExpect(handler().methodName("index"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void whenDashboardIsAccessedShouldReturnDashboard() throws Exception {
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
    void whenInventoryIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/inventory"))
                .andExpect(handler().methodName("inventory"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void whenInventoryIsAccessedShouldReturnConsumablesList() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        player.setPlayerInventory(new Inventory("Bintang"));
        ReflectionTestUtils.setField(controller, "player", player);

        mockMvc.perform(get("/inventory"))
                .andExpect(handler().methodName("inventory"))
                .andExpect(model().attributeExists("consumables"))
                .andExpect(view().name("inventory"));
    }



    @Test
    void whenDeleteConsumableIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/inventory/1/delete-consumable"))
                .andExpect(handler().methodName("deleteConsumable"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void whenDeleteConsumableAccessedShouldCallInventoryService() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        player.setPlayerInventory(new Inventory("Bintang"));
        ReflectionTestUtils.setField(controller, "player", player);

        mockMvc.perform(get("/inventory/1/delete-consumable"))
                .andExpect(handler().methodName("deleteConsumable"))
                .andExpect(view().name("redirect:/inventory"));
        verify(inventoryService, times(1)).deleteConsumableFromInventory(player.getPlayerInventory(),1);
    }

    @Test
    void whenPersonaSoulListIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/inventory/persona-souls"))
                .andExpect(handler().methodName("personaSouls"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void whenPersonaSoulListIsAccessedShouldReturnPersonaSoulList() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        Inventory inventory = new Inventory("Bintang");
        inventory.setPersonaSoulList(new ArrayList<>());
        player.setPlayerInventory(inventory);
        ReflectionTestUtils.setField(controller, "player", player);

        mockMvc.perform(get("/inventory/persona-souls"))
                .andExpect(handler().methodName("personaSouls"))
                .andExpect(model().attributeExists("souls"))
                .andExpect(view().name("persona_souls_list"));
    }

    @Test
    void whenInventoryIsAccesedShouldReturnWeaponList() throws Exception {
        Player player = new Player("Dafin", "Dafin");
        player.setPlayerInventory(new Inventory("Tas Dafin"));
        ReflectionTestUtils.setField(controller,"player",player);

        mockMvc.perform(get("/inventory/weapons"))
                .andExpect(handler().methodName("listWeapon"))
                .andExpect(model().attributeExists("weapons"))
                .andExpect(view().name("weapon_list"));
    }


    @Test
    void whenDeletePersonaSoulIsAccessedWithoutLoginShouldRedirectToLogin() throws Exception {
        ReflectionTestUtils.setField(controller, "player", null);

        mockMvc.perform(get("/inventory/1/delete-soul"))
                .andExpect(handler().methodName("deleteSoul"))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void whenDeletePersonaSoulIsAccessedShouldCallInventoryService() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        player.setPlayerInventory(new Inventory("Bintang"));
        ReflectionTestUtils.setField(controller, "player", player);

        mockMvc.perform(get("/inventory/1/delete-soul"))
                .andExpect(handler().methodName("deleteSoul"))
                .andExpect(view().name("redirect:/inventory/persona-souls"));
        verify(inventoryService, times(1)).deletePersonaSoulFromInventory(player.getPlayerInventory(),1);
    }

    @Test
    void whenPossesIsAccessedShouldCallInventoryService() throws Exception {
        Player player = new Player("Bintang", "Bintang");
        Inventory inventory = new Inventory("Bintang");
        inventory.setPersonaSoulList(new ArrayList<>());
        player.setPlayerInventory(inventory);
        ReflectionTestUtils.setField(controller, "player", player);

        mockMvc.perform(get("/inventory/posses/1"))
                .andExpect(handler().methodName("posses"))
                .andExpect(status().isOk());
        verify(inventoryService, times(1)).posses(1, player);
    }

}
