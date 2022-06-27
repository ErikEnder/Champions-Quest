package com.erikender.championsquest.webapp.controller;

import com.erikender.championsquest.webapp.exceptions.SQLIntegrityConstraintViolationException;
import com.erikender.championsquest.webapp.exceptions.SQLSyntaxErrorException;
import com.erikender.championsquest.webapp.model.User;
import com.erikender.championsquest.webapp.repository.MyCharacterRepository;
import com.erikender.championsquest.webapp.repository.UserRepository;
import com.erikender.championsquest.webapp.services.MyCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /** Maps the signup page **/
    @RequestMapping(value={"/signup", "/registerfailed", "/invalidinfo"})
    public String viewSignUp(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    /** Takes in the user input from the signup form.
     * Takes the base password and encrypts it with BCrypt, then saves it to the Database.
     * Saves the user and assigns a new set of 4 default characters to them.
     * Process the request using the User's email and unencrypted password
     *
     * @param request The servlet request for the User's authentication processing
     * @param user The User object containing the information gathered from the Form
     * @return Points the user back to the login page upon successful account creation
     */
    @PostMapping("/process_signup")
    public String processUserSignup(HttpServletRequest request, User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)throw new SQLIntegrityConstraintViolationException();
        if (user.getEmail().length() > 50)throw new SQLSyntaxErrorException();
        if (user.getFirstName().length() > 30 || user.getLastName().length() > 30 || user.getInGameName().length() > 30)throw new SQLSyntaxErrorException();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String basePassword = user.getPassword();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        characterService.assignCharacters(user, characterRepository);
        authWithHttpServletRequest(request, user.getEmail(), basePassword);

        return "login";
    }

    /** Maps the login page **/
    @GetMapping("/login")
    public String viewLogin() {

        return "login";
    }

    /** Takes the inputted email and password then passes them to the authentication method
     * On successful authentication, logs the user in **/
    @PostMapping("/process_login")
    public String processUserLogin(HttpServletRequest request, User user) {
        String username = user.getEmail();
        String password = user.getPassword();
        authWithHttpServletRequest(request, username, password);

        return "map";
    }

    /** If a logged in user presses the logout link, logs them out and returns them to the login page **/
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /** Method handling authentication requests sent by Users attempting to login **/
    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that throws exception when user input doesn't fit within the database's ruleset
     * @param exception
     * @return Page telling the user why their input doesn't work.
     */
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public String exception(SQLSyntaxErrorException exception) {
        return "redirect:/invalidinfo";
    }

}
