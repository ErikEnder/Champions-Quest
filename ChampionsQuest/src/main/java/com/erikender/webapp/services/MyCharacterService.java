package com.erikender.webapp.services;

import com.erikender.webapp.dto.FormDto;
import com.erikender.webapp.model.Item;
import com.erikender.webapp.model.MyCharacter;
import com.erikender.webapp.model.User;
import com.erikender.webapp.repository.MyCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyCharacterService {

    @Autowired
    MyCharacterRepository repo;

    @Autowired
    MyUserDetailsService userDetailsService;

    public MyCharacter getCharacterInfo(int char_id) {
        MyCharacter character = repo.findById(char_id);

        return character;
    }

    public void assignCharacters(User user, MyCharacterRepository repo) {
        int userId = user.getId();
        String ign = user.getInGameName();

        // Creating character templates for a new User
        MyCharacter newCharOne = new MyCharacter(userId, "Hero", "maleKnight", 100, 50, 0, 1, "Hero");
        MyCharacter newCharTwo = new MyCharacter(userId, "Companion", "femaleKnight", 90, 30, 0, 1, "Companion");
        MyCharacter newCharThree = new MyCharacter(userId, "Rylock", "Wizard", 50, 80, 0, 1, "Rylock");
        MyCharacter newCharFour = new MyCharacter(userId, "Hound", "Dog", 110, 20, 0, 1, "Hound");

        // If the User set a custom in-game name, change their main character's name upon creation.
        if (ign != "") {
            newCharOne.setAlteredName(ign);
        }
        else {
            // If no custom IGN, use default name for main character.
            newCharOne.setAlteredName("Hero");
        }

        newCharOne.setGold(10);

        // Save newly created characters
        repo.save(newCharOne);
        repo.save(newCharTwo);
        repo.save(newCharThree);
        repo.save(newCharFour);
    }

    // Method to find a specific character assigned to a User.
    // Uses the User's ID and the "Default Name" of each character.
    public MyCharacter findByName(String name, int userId) {
        MyCharacter character = repo.findByName(name, userId);

        return character;
    }

    // Method for taking User input from Camp page and applying it to their party.
    public void alterCharacters(FormDto dto) {
        System.out.println("I have been called properly.");

        MyCharacter charOne = findByName("Hero", userDetailsService.getLoggedId());
        MyCharacter charTwo = findByName("Companion", userDetailsService.getLoggedId());
        MyCharacter charFour = findByName("Hound", userDetailsService.getLoggedId());

        System.out.println("Char One Pre-Alter: " + charOne.getAlteredName());
        System.out.println("Char Two Pre-Alter: " + charTwo.getAlteredName());
        System.out.println("Char Four Pre-Alter: " + charFour.getAlteredName());

        String altNameOne = dto.getHeroAN();
        String altNameTwo = dto.getCompAN();
        String altNameFour = dto.getDogAN();

        String modelOne = dto.getHeroModel();
        String modelTwo = dto.getCompModel();
        String modelFour = "";

        charOne = changeNameAndModel(charOne, altNameOne, modelOne);
        charTwo = changeNameAndModel(charTwo, altNameTwo, modelTwo);
        charFour = changeNameAndModel(charFour, altNameFour, modelFour);


        repo.save(charOne);
        repo.save(charTwo);
        repo.save(charFour);

        System.out.println("Char One Post-Alter: " + charOne.getAlteredName());
        System.out.println("Char Two Post-Alter: " + charTwo.getAlteredName());
        System.out.println("Char Four Post-Alter: " + charFour.getAlteredName());

    }

    // Method to alter a character's name and model.  Used to reduce repeat code usage.
    public MyCharacter changeNameAndModel(MyCharacter character, String newName, String newModel) {

        if (newName == null || newName.equals("") || newName.trim().equals("")) {
            System.out.println("Name unchanged.");
        }
        else {
            character.setAlteredName(newName);
        }

        if (newModel == null || newModel.equals("") || newModel.trim().equals("")) {
            System.out.println("Model unchanged.");
        }
        else {
            character.setModel(newModel);
        }

        return character;
    }

    public void increaseCharWealth(MyCharacter character, Item item) {
        int charGold = character.getGold();
        int itemPrice = item.getPrice();

        character.setGold(charGold + itemPrice);

        repo.save(character);

    }
}
