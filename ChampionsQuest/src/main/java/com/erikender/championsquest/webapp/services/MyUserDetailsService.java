package com.erikender.championsquest.webapp.services;

import com.erikender.championsquest.webapp.model.User;
import com.erikender.championsquest.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** Service class for User-based functionality **/
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Method for getting a user by their username/email address
     * @param email the username identifying the user whose data is required.
     * @return The User's details
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        // If user is not found in the Repository, throw exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new MyUserDetails(user);
    }

    /**
     * Method for getting the currently logged-in User's information
     * @return The User who is currently logged in.
     */
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName);
        return user;
    }

    /**
     * Method for getting the currently logged-in User's ID
     * @return Current ID
     */
    public int getLoggedId() {
        User user = getLoggedUser();
        int id = user.getId();

        return id;
    }

}
