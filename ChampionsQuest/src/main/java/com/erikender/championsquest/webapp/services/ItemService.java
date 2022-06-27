package com.erikender.championsquest.webapp.services;

import com.erikender.championsquest.webapp.repository.ItemRepository;
import com.erikender.championsquest.webapp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** Service class for Item-based functionality **/
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepo;

    /**
     * Method for getting every shop item
     * @param shop_id ID of the shop (will always be 1)
     * @return The shop items
     */
    public List<Item> getShopItems(int shop_id) {
        List<Item> shopItems = new ArrayList<>();
        itemRepo.findAllByShopId(shop_id).forEach(shopItems::add);

        return shopItems;
    }

    /**
     * Gets an Item by its ID
     * @param itemId Item's ID
     * @return The item
     */
    public Item getItemById(int itemId) {
        Item item = itemRepo.findById(itemId);

        return item;
    }
}
