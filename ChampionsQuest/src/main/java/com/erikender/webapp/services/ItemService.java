package com.erikender.webapp.services;

import com.erikender.webapp.model.Item;
import com.erikender.webapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepo;

    public List<Item> getShopItems(int shop_id) {
        List<Item> shopItems = new ArrayList<>();
        itemRepo.findAllByShopId(shop_id).forEach(shopItems::add);

        return shopItems;
    }

    public Item getItemById(int itemId) {
        Item item = itemRepo.findById(itemId);

        return item;
    }
}
