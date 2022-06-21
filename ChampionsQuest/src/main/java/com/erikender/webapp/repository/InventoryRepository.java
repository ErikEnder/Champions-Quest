package com.erikender.webapp.repository;

import com.erikender.webapp.model.Inventory;
import com.erikender.webapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

    @Query("SELECT i.item_id FROM Inventory i WHERE i.user_id = ?1")
    List<Integer> getInventoryByUserId(int id);

    @Query("SELECT i FROM Inventory i where i.user_id = :user_id AND i.item_id = :item_id")
    Item getItemByIds(int user_id, int item_id);

    @Modifying
    @Query("DELETE FROM Inventory i WHERE i.user_id = :user_id AND i.item_id = :item_id")
    void sellFromInventory(int user_id, int item_id);
}
