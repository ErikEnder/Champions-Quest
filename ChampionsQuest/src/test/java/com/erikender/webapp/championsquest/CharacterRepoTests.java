package com.erikender.webapp.championsquest;

import com.erikender.webapp.model.MyCharacter;
import com.erikender.webapp.repository.MyCharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class CharacterRepoTests {

    @Autowired
    private MyCharacterRepository repo;

    /** Testing repo method to get a character by their ID **/
    @Test
    public void testFindCharById() {
        int id = 1;

        MyCharacter myCharacter = repo.findById(id);

        assertThat(myCharacter).isNotNull();

        System.out.println("Char ID: " + myCharacter.getChar_id() + " User ID: " +  myCharacter.getUser_id() + " Char Name: " + myCharacter.getName());
    }

    /** Testing repo method to find a character by their default name **/
    @Test
    public void testFindByName() {
        String name = "Hero";
        int id = 1;

        MyCharacter character = repo.findByName(name, id);
        System.out.println(character.getName() + " " + character.getChar_id() + " " + character.getUser_id());

    }
}
