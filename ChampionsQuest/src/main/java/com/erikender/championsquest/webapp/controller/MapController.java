package com.erikender.championsquest.webapp.controller;

import com.erikender.championsquest.webapp.services.MyCharacterService;
import com.erikender.championsquest.webapp.services.MyUserDetailsService;
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

    /** Maps the...map page. Also assigns the Hero character to the model to display a unique name on the page **/
    @GetMapping("/map")
    public String modelAndView(Model model) {
        model.addAttribute("characterOne",
                characterService.findByName("Hero", userDetailsService.getLoggedId()));

        return "map";
    }
}
