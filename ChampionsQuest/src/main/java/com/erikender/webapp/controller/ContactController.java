package com.erikender.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    /** Maps the contact page **/
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
}
