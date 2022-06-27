package com.erikender.championsquest.webapp.repository;

import com.erikender.championsquest.webapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


// Repository for Item objects
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    // Returns an Item based on the given ID
    @Query("SELECT i FROM Item i WHERE i.item_id = ?1")
    Item findById(int item_id);

    // Returns every item in the Shop
    @Query("SELECT i FROM Item i WHERE i.shop_id = ?1")
    List<Item> findAllByShopId(int shop_id);

    // Returns an Item given its name
    @Query("SELECT i FROM Item i WHERE i.name = ?1")
    Item findByName(String name);
}
