package com.erikender.webapp.controller;

import com.erikender.webapp.dto.FormDto;
import com.erikender.webapp.repository.MyCharacterRepository;
import com.erikender.webapp.repository.ShopRepository;
import com.erikender.webapp.repository.UserRepository;
import com.erikender.webapp.services.MyUserDetailsService;
import com.erikender.webapp.services.ItemService;
import com.erikender.webapp.services.MyCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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


    @GetMapping("/camp")
    public String modelAndView(Model model) {
        model.addAttribute("characterOne",
                characterService.findByName("Hero", userDetailsService.getLoggedId()));
        model.addAttribute("characterTwo",
                characterService.findByName("Companion", userDetailsService.getLoggedId()));
        model.addAttribute("characterThree",
                characterService.findByName("Rylock", userDetailsService.getLoggedId()));
        model.addAttribute("characterFour",
                characterService.findByName("Hound", userDetailsService.getLoggedId()));

        FormDto dto = new FormDto();
        dto.setHeroModel(characterService.findByName("Hero", userDetailsService.getLoggedId()).getModel());
        dto.setCompModel(characterService.findByName("Companion", userDetailsService.getLoggedId()).getModel());

        model.addAttribute("dto", dto);

        model.addAttribute("shop", itemService.getShopItems(1));

        return "camp";
    }

    @PostMapping("/customize_characters")
    public String customizeCharacters(FormDto dto, Model model) {
        characterService.alterCharacters(dto);

        return modelAndView(model);
    }


}
