package com.erikender.championsquest.webapp.championsquest;

import com.erikender.championsquest.webapp.model.User;
import com.erikender.championsquest.webapp.repository.MyCharacterRepository;
import com.erikender.championsquest.webapp.repository.UserRepository;
import com.erikender.championsquest.webapp.services.MyCharacterService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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

        AssertionsForClassTypes.assertThat(myCharacterRepository.findByName("Hero", user.getId())).isNotNull();
        AssertionsForClassTypes.assertThat(myCharacterRepository.findByName("Companion", user.getId())).isNotNull();
        AssertionsForClassTypes.assertThat(myCharacterRepository.findByName("Rylock", user.getId())).isNotNull();
        AssertionsForClassTypes.assertThat(myCharacterRepository.findByName("Hound", user.getId())).isNotNull();

    }
}
