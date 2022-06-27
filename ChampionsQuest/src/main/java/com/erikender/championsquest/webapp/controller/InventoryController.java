package com.erikender.championsquest.webapp.controller;

import com.erikender.championsquest.webapp.services.InventoryService;
import com.erikender.championsquest.webapp.services.MyCharacterService;
import com.erikender.championsquest.webapp.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InventoryController {

    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    MyCharacterService characterService;
    @Autowired
    InventoryService inventoryService;

    /** Sets up basic mapping/attributes of Inventory page **/
    @GetMapping("/inventory")
    public String inventoryPage(Model model) {
        model.addAttribute("characterOne",
                characterService.findByName("Hero", userDetailsService.getLoggedId()));
        model.addAttribute("inventory", inventoryService.getUserInventory(userDetailsService.getLoggedId()));

        return "inventory";
    }

    /** Calls the sellItem function that will perform calculations and remove an item from the Player's inventory **/
    @RequestMapping(value = "/inventory/delete/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable int id) {
        inventoryService.sellItem(id);

        return "redirect:/inventory";
    }
}
