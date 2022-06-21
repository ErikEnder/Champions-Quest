package com.erikender.webapp.controller;

import com.erikender.webapp.services.InventoryService;
import com.erikender.webapp.services.MyCharacterService;
import com.erikender.webapp.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InventoryController {

    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    MyCharacterService characterService;
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventory")
    public String inventoryPage(Model model) {
        model.addAttribute("characterOne",
                characterService.findByName("Hero", userDetailsService.getLoggedId()));
        model.addAttribute("inventory", inventoryService.getUserInventory(userDetailsService.getLoggedId()));

        return "inventory";
    }

    @RequestMapping(value = "/inventory/delete/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable int id, Model model) {
        inventoryService.sellItem(id);

        return inventoryPage(model);
    }
}
