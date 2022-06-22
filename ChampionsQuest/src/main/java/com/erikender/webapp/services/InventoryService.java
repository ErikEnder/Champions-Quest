package com.erikender.webapp.services;

import com.erikender.webapp.model.*;
import com.erikender.webapp.repository.InventoryRepository;
import com.erikender.webapp.repository.MyCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/** Service class for Inventory-based functionality **/
@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    MyCharacterService characterService;

    /**
     * Method that collects a User's inventory into a list for later use.
     * @param user_id User whose inventory is being accessed
     * @return The List of the User's items from their inventory
     */
    public List<Item> getUserInventory(int user_id) {
        // Creates list for storing Inventory
        List<Integer> userInventory = new ArrayList<>();
        // Populates user's Inventory
        inventoryRepository.getInventoryByUserId(user_id).forEach(userInventory::add);

        // Creates List for storing Items from Inventory
        // Necessary due to Inventory being a collection of Users and their associated Items, dirty and
        // hard to deal with
        List<Item> userInventoryItems = new ArrayList<>();
        for (int i = 0; i < userInventory.size(); i++) {
            // Creates a new Item object that takes the Item from the Inventory
            Item item = itemService.getItemById(userInventory.get(i));

            // Stores the new Item into the List of Items
            userInventoryItems.add(item);
        }
        return userInventoryItems;
    }

    /**
     * Method for selling items from the User's inventory
     * @param item_id Item being sold
     */
    @Transactional
    public void sellItem(int item_id) {
        // Get character whose gold will be increased upon selling (main character by default)
        MyCharacter mainCharacter = characterService.findByName("Hero", userDetailsService.getLoggedId());
        Item soldItem = itemService.getItemById(item_id);

        // Assigning the Inventory's Primary Keys
        InventoryPK inventoryKeys = new InventoryPK();
        inventoryKeys.setItem_id(item_id);
        inventoryKeys.setUser_id(userDetailsService.getLoggedId());
        //

        // Set character's gold to be their current + the price of the item being sold
        characterService.increaseCharWealth(mainCharacter, soldItem);

        // Remove sold item from the inventory
        inventoryRepository.sellFromInventory(inventoryKeys.getUser_id(), inventoryKeys.getItem_id());
    }

    /**
     * Buys an item from the shop and stores it into the User's inventory
     * @param item_id Item being purchased
     */
    @Transactional
    public void buyItem(int item_id) {
        // Getting relevant IDs
        User user = userDetailsService.getLoggedUser();
        MyCharacter mainCharacter = characterService.findByName("Hero", userDetailsService.getLoggedId());
        Item boughtItem = itemService.getItemById(item_id);
        //

        // Stores True if Character has enough gold, otherwise stores False
        boolean checkWealth = characterService.decreaseCharWealth(mainCharacter, boughtItem);

        // Creates a new Inventory object containing the User and the Item
        Inventory newItem = new Inventory(user.getId(), user, item_id);

        // If character has needed gold, purchase item.
        if (checkWealth == true && inventoryRepository.getItemByIds(user.getId(), boughtItem.getItem_id()) == null) {
            inventoryRepository.save(newItem);
            System.out.println("Item purchased successfully!");
        }
        // Otherwise do not purchase item.
        else {
            System.out.println("Item not purchased.");
        }
    }
}
