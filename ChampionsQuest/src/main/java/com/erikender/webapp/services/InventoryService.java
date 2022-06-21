package com.erikender.webapp.services;

import com.erikender.webapp.model.*;
import com.erikender.webapp.repository.InventoryRepository;
import com.erikender.webapp.repository.MyCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<Item> getUserInventory(int user_id) {
        List<Integer> userInventory = new ArrayList<>();
        inventoryRepository.getInventoryByUserId(user_id).forEach(userInventory::add);

        List<Item> userInventoryItems = new ArrayList<>();
        for (int i = 0; i < userInventory.size(); i++) {
            Item item = itemService.getItemById(userInventory.get(i));
            userInventoryItems.add(item);
        }

        return userInventoryItems;
    }

    @Transactional
    public void sellItem(int item_id) {
        // Get character whose gold will be increased upon selling (main character by default)
        MyCharacter mainCharacter = characterService.findByName("Hero", userDetailsService.getLoggedId());
        Item soldItem = itemService.getItemById(item_id);
        InventoryPK inventoryKeys = new InventoryPK();

        inventoryKeys.setItem_id(item_id);
        inventoryKeys.setUser_id(userDetailsService.getLoggedId());


        // Set character's gold to be their current + the price of the item being sold
        characterService.increaseCharWealth(mainCharacter, soldItem);

        // Remove sold item from the inventory
        inventoryRepository.sellFromInventory(inventoryKeys.getUser_id(), inventoryKeys.getItem_id());

        System.out.println("Successfully sold: " + itemService.getItemById(item_id));
    }

    @Transactional
    public void buyItem(int item_id) {
        User user = userDetailsService.getLoggedUser();
        MyCharacter mainCharacter = characterService.findByName("Hero", userDetailsService.getLoggedId());
        Item boughtItem = itemService.getItemById(item_id);

        boolean checkWealth = characterService.decreaseCharWealth(mainCharacter, boughtItem);

        Inventory newItem = new Inventory(user.getId(), user, item_id);

        if (checkWealth == true && inventoryRepository.getItemByIds(user.getId(), boughtItem.getItem_id()) == null) {
            inventoryRepository.save(newItem);
            System.out.println("Item purchased successfully!");
        }
        else {
            System.out.println("Item not purchased.");
        }
    }
}
