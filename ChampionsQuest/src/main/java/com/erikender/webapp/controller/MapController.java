package com.erikender.webapp.controller;

import com.erikender.webapp.services.MyUserDetailsService;
import com.erikender.webapp.services.MyCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    MyCharacterService characterService;

    @GetMapping("/map")
    public String modelAndView(Model model) {
        model.addAttribute("characterOne",
                characterService.findByName("Hero", userDetailsService.getLoggedId()));

        return "map";
    }
}
