package com.erikender.webapp.services;

import com.erikender.webapp.dto.FormDto;
import com.erikender.webapp.exceptions.SQLSyntaxErrorException;
import com.erikender.webapp.model.Item;
import com.erikender.webapp.model.MyCharacter;
import com.erikender.webapp.model.User;
import com.erikender.webapp.repository.MyCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Service class for Character-based functionality **/
@Service
public class MyCharacterService {

    @Autowired
    MyCharacterRepository repo;
    @Autowired
    MyUserDetailsService userDetailsService;

    /**
     * Method creates default characters and assigns them to a new User upon account creation.
     * @param user The current new User
     * @param repo The Character repository
     */
    public void assignCharacters(User user, MyCharacterRepository repo) {
        int userId = user.getId();
        String ign = user.getInGameName();

        // Creating default characters for a new User
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

        newCharOne.setGold(50);

        // Save newly created characters
        repo.save(newCharOne);
        repo.save(newCharTwo);
        repo.save(newCharThree);
        repo.save(newCharFour);
    }

    /**
     * Method to find a character by their default name
     * @param name Default name of the character
     * @param userId Current logged-in user's ID
     * @return
     */
    public MyCharacter findByName(String name, int userId) {
        MyCharacter character = repo.findByName(name, userId);

        return character;
    }

    /**
     * Method for taking User input from Camp page and applying it to their party.
     * @param dto Data Transfer Object for HTML Form
     */
    public void alterCharacters(FormDto dto) {
        // Finds the characters assigned to the current user
        MyCharacter charOne = findByName("Hero", userDetailsService.getLoggedId());
        MyCharacter charTwo = findByName("Companion", userDetailsService.getLoggedId());
        MyCharacter charFour = findByName("Hound", userDetailsService.getLoggedId());

        // Sets character altered names based on input given in DTO
        String altNameOne = dto.getHeroAN();
        String altNameTwo = dto.getCompAN();
        String altNameFour = dto.getDogAN();

        if (altNameOne.length() > 30 || altNameTwo.length() > 30 || altNameFour.length() > 30)throw new SQLSyntaxErrorException();
        // Sets the character models from DTO input
        String modelOne = dto.getHeroModel();
        String modelTwo = dto.getCompModel();
        String modelFour = "";

        // Calls method to apply the changes
        charOne = changeNameAndModel(charOne, altNameOne, modelOne);
        charTwo = changeNameAndModel(charTwo, altNameTwo, modelTwo);
        charFour = changeNameAndModel(charFour, altNameFour, modelFour);

        // Saves altered characters
        repo.save(charOne);
        repo.save(charTwo);
        repo.save(charFour);
    }

    /**
     * Method that takes values from alterCharacters and applies them given met criteria
     * @param character Character being passed
     * @param newName Character's altered name
     * @param newModel Character's model value
     * @return The character post-changes
     */
    public MyCharacter changeNameAndModel(MyCharacter character, String newName, String newModel) {

        // If character's altered name field is unchanged from default, no changes.
        if (newName == null || newName.equals("") || newName.trim().equals("")) {
            System.out.println("Name: " + character.getAlteredName() + " - STATUS: Unchanged.");
        }
        else {
            // Otherwise change their altered name
            System.out.print("Name: " + character.getAlteredName() + " - Changed to: ");
            character.setAlteredName(newName);
            System.out.print(character.getAlteredName());
            System.out.println();
        }

        // Same thing as altered name, just with their model
        if (newModel == null || newModel.equals("") || newModel.trim().equals("")) {
            System.out.println("Model: " + character.getModel() + " - STATUS: Unchanged.");
        }
        else {
            System.out.print("Model: " + character.getModel() + " - Changed to: ");
            character.setModel(newModel);
            System.out.print(character.getModel());
            System.out.println();
        }
        return character;
    }

    /**
     * Method to increase a Character's gold after selling an item
     * @param character Character whose gold is increased (will always be main character)
     * @param item Item being sold
     */
    public void increaseCharWealth(MyCharacter character, Item item) {
        int charGold = character.getGold();
        int itemPrice = item.getPrice();

        // Increases character's gold by the item's price then sets it.
        character.setGold(charGold + itemPrice);

        repo.save(character);
    }

    /**
     * Method that decreases a character's gold upon buying an item
     * @param character Character whose gold is decreased (will always be main character)
     * @param item Item being purchased
     * @return True or False depending on whether the character has enough gold to purchase the item
     */
    public boolean decreaseCharWealth(MyCharacter character, Item item) {
        int charGold = character.getGold();
        int itemPrice = item.getPrice();

        // If character has more gold than needed
        if (charGold >= itemPrice) {
            // Sets their gold to current - item's price
            character.setGold(charGold - itemPrice);
            return true;
        }
        else {
            // If character doesn't have enough gold, do not purchase item
            System.out.println("Not enough gold!");
            return false;
        }
    }
}
