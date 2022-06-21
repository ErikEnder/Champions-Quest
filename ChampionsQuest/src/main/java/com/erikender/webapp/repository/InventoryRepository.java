package com.erikender.webapp.repository;

import com.erikender.webapp.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

    @Query("SELECT i.item_id FROM Inventory i WHERE i.user_id = ?1")
    List<Integer> getInventoryByUserId(int id);
}
