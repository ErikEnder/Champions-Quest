package com.erikender.webapp.championsquest;

import com.erikender.webapp.model.MyCharacter;
import com.erikender.webapp.model.User;
import com.erikender.webapp.repository.MyCharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class CharacterRepositoryTests {

    @Autowired
    private MyCharacterRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAssignCharacter() {
        User user = new User();
        user.setId(10);

        //  int charId, int user_id, String name, String model, int health, int mana, int xp, int level)

        MyCharacter newCharAssign = new MyCharacter(user.getId(), "Test", "Knight", 100, 50, 0, 1, "Test");

        MyCharacter savedChar = repo.save(newCharAssign);

        MyCharacter existChar = entityManager.find(MyCharacter.class, savedChar.getChar_id());

        assertThat(existChar.getUser_id()).isEqualTo(user.getId());
    }

    @Test
    public void testFindCharById() {
        int id = 1;

        MyCharacter myCharacter = repo.findById(id);

        assertThat(myCharacter).isNotNull();

        System.out.println("Char ID: " + myCharacter.getChar_id() + " User ID: " +  myCharacter.getUser_id() + " Char Name: " + myCharacter.getName());
    }

    @Test
    public void testAssignCharacters() {
        // int user_id, String email, String password, String firstName, String lastName, String inGameName
        User user = new User(420, "dummy69@gmail.com", "password", "Dumb", "My", "Catdog");
        MyCharacter baseOne = repo.findById(1);

        int id = user.getId();
        String ign = user.getInGameName();

        System.out.println(ign);

        //  int charId, int user_id, String name, String model, int health, int mana, int xp, int level)
        MyCharacter charOne = new MyCharacter(31, ign, "Knight", 100, 50, 0, 1, "Hero");

        repo.save(charOne);

    }

    @Test
    public void testGetHighestId() {
        int id = repo.getHighestId();

        System.out.println(id);
    }

    @Test
    public void testFindByName() {
        String name = "Hero";
        int id = 1;

        MyCharacter character = repo.findByName(name, id);
        System.out.println(character.getName() + " " + character.getChar_id() + " " + character.getUser_id());

    }
}
