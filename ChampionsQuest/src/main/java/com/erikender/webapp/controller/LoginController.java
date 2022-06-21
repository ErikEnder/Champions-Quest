package com.erikender.webapp.controller;

import com.erikender.webapp.model.User;
import com.erikender.webapp.repository.MyCharacterRepository;
import com.erikender.webapp.repository.UserRepository;
import com.erikender.webapp.services.MyCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MyCharacterRepository characterRepository;

    @Autowired
    MyCharacterService characterService;

    @GetMapping("/signup")
    public String viewSignUp(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/process_signup")
    public String processUserSignup(HttpServletRequest request, User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String basePassword = user.getPassword();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        characterService.assignCharacters(user, characterRepository);
        authWithHttpServletRequest(request, user.getEmail(), basePassword);

        return "login";
    }

    @GetMapping("/login")
    public String viewLogin() {

        return "login";
    }

    @PostMapping("/process_login")
    public String processUserLogin(HttpServletRequest request, User user) {
        String username = user.getEmail();
        String password = user.getPassword();
        authWithHttpServletRequest(request, username, password);

        return "map";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
