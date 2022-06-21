package com.erikender.webapp.repository;

import com.erikender.webapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT i FROM Item i WHERE i.item_id = ?1")
    Item findById(int item_id);

    @Query("SELECT i FROM Item i WHERE i.shop_id = ?1")
    List<Item> findAllByShopId(int shop_id);

    @Query("SELECT i FROM Item i WHERE i.name = ?1")
    Item findByName(String name);
}
