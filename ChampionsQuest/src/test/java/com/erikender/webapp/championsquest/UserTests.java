package com.erikender.webapp.championsquest;

import com.erikender.webapp.model.User;
import com.erikender.webapp.repository.UserRepository;
import com.erikender.webapp.services.MyUserDetails;
import com.erikender.webapp.services.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    /** Test that ensures creating a new user method works **/
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("testemail@test.com");
        user.setPassword("testing");
        user.setFirstName("Dummy");
        user.setLastName("Account");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    /** Test method that ensures finding by email method in repository works **/
    @Test
    public void testFindByEmail() {
        String email = "eender021@hotmail.com";

        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }

    /* public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     User user = userRepository.findByEmail(email);
     if (user == null) {
     throw new UsernameNotFoundException("User not found.");
     }
     return new MyUserDetails(user);
     } */


    /** Test that ensures LoadUserByUsername method in MyUserDetailsService works **/
    @Test
    public void testLoadUserByUsername () {
        String email = "eender021@hotmail.com";
        User user = repo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        assertThat(user.getEmail()).isEqualTo(email);
    }
}
