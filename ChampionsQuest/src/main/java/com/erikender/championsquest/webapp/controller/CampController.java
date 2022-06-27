package com.erikender.championsquest.webapp.controller;

import com.erikender.championsquest.webapp.repository.MyCharacterRepository;
import com.erikender.championsquest.webapp.repository.ShopRepository;
import com.erikender.championsquest.webapp.repository.UserRepository;
import com.erikender.championsquest.webapp.dto.FormDto;
import com.erikender.championsquest.webapp.exceptions.SQLSyntaxErrorException;
import com.erikender.championsquest.webapp.services.InventoryService;
import com.erikender.championsquest.webapp.services.MyUserDetailsService;
import com.erikender.championsquest.webapp.services.ItemService;
import com.erikender.championsquest.webapp.services.MyCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CampController {

    @Autowired
    MyCharacterRepository charRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShopRepository shopRepo;
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    MyCharacterService characterService;
    @Autowired
    ItemService itemService;
    @Autowired
    InventoryService inventoryService;


    /** Method maps Camp page and assigns attributes to the model for dynamic values via Thymeleaf **/
    @RequestMapping(value={"/camp", "/charnameerror"})
    public String modelAndView(Model model) {

        // This block assigns the individual characters
        model.addAttribute("characterOne",
                characterService.findByName("Hero", userDetailsService.getLoggedId()));
        model.addAttribute("characterTwo",
                characterService.findByName("Companion", userDetailsService.getLoggedId()));
        model.addAttribute("characterThree",
                characterService.findByName("Rylock", userDetailsService.getLoggedId()));
        model.addAttribute("characterFour",
                characterService.findByName("Hound", userDetailsService.getLoggedId()));

        // Creates a Form Data Transfer Object to properly grab values for adjustment
        FormDto dto = new FormDto();

        // Adds DTO to the model
        model.addAttribute("dto", dto);

        // Gets the Main Character and the Companion Character based on their default names, as IDs are not entirely predictable
        // based on which user is calling method
        dto.setHeroModel(characterService.findByName("Hero", userDetailsService.getLoggedId()).getModel());
        dto.setCompModel(characterService.findByName("Companion", userDetailsService.getLoggedId()).getModel());

        // Adds Shop to the model
        model.addAttribute("shop", itemService.getShopItems(1));

        return "camp";
    }

    /** Calls the customizeCharacters function that will apply alterations to the characters based on values passed
        from the HTML form
     * @param dto The DTO passing values from HTML to Java
     */
    @PostMapping("/customize_characters")
    public String customizeCharacters(FormDto dto) {
        characterService.alterCharacters(dto);

        return "redirect:/camp";
    }

    /** Calls the buyItem function that will perform calculations and append an item bought from the shop onto
     * the player's inventory
     *
     * @param id The ID of the item being purchased by the player
     */
    @PutMapping(value = "/camp/{id}")
    public String addItem(@PathVariable int id) {
        inventoryService.buyItem(id);

        return "redirect:/camp";
    }

    /**
     * Method that throws exception when user input doesn't fit within the database's ruleset
     * @param exception
     * @return Page telling the user why their input doesn't work.
     */
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public String exception(SQLSyntaxErrorException exception) {
        return "redirect:/charnameerror";
    }

}
