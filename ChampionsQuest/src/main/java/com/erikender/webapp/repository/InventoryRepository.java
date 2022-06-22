package com.erikender.webapp.repository;

import com.erikender.webapp.model.Inventory;
import com.erikender.webapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository for Player inventories**/
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

    // Returns a User's player inventory
    @Query("SELECT i.item_id FROM Inventory i WHERE i.user_id = ?1")
    List<Integer> getInventoryByUserId(int id);

    // Returns an Item given its ID and the player whose inventory it belongs to
    @Query("SELECT i FROM Inventory i WHERE i.user_id = :user_id AND i.item_id = :item_id")
    Item getItemByIds(int user_id, int item_id);

    // Removes an item from a player's inventory
    @Modifying
    @Query("DELETE FROM Inventory i WHERE i.user_id = :user_id AND i.item_id = :item_id")
    void sellFromInventory(int user_id, int item_id);
}
