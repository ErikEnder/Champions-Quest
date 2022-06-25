package com.erikender.webapp.championsquest;

import com.erikender.webapp.model.MyCharacter;
import com.erikender.webapp.model.User;
import com.erikender.webapp.repository.MyCharacterRepository;
import com.erikender.webapp.repository.UserRepository;
import com.erikender.webapp.services.MyCharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CharacterServiceTests {

    @Autowired
    private MyCharacterRepository myCharacterRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyCharacterService myCharacterService;

    /** Testing service method to assign characters to a new user
     * Also tests findByName, technically **/
    @Test
    public void testAssignCharacters() {
        // int user_id, String email, String password, String firstName, String lastName, String inGameName
        User user = new User("dummy1@gmail.com", "password", "Dummy", "Bot", "Robin Hood");

        // If user doesn't exist then save them
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        }
        else {
            // Delete then save to avoid any search complications in repo
            userRepository.delete(user);
            userRepository.save(user);
        }

        myCharacterService.assignCharacters(user, myCharacterRepository);

        assertThat(myCharacterRepository.findByName("Hero", user.getId())).isNotNull();
        assertThat(myCharacterRepository.findByName("Companion", user.getId())).isNotNull();
        assertThat(myCharacterRepository.findByName("Rylock", user.getId())).isNotNull();
        assertThat(myCharacterRepository.findByName("Hound", user.getId())).isNotNull();

    }
}
