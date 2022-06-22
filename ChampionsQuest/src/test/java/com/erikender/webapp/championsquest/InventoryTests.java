package com.erikender.webapp.championsquest;

import com.erikender.webapp.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class InventoryTests {

    @Autowired
    private InventoryRepository repo;

    /* @Query("SELECT i FROM Inventory i WHERE i.userId = ?1")
        List<Inventory> getInventoryByUserId(int id); */

    /** Tests repo method that returns user inventory by User ID **/
    @Test
    public void testGetInventoryByUserId() {

        List<Integer> inventory = repo.getInventoryByUserId(1);
        assertThat(inventory).isNotNull();

        System.out.println(inventory);
    }

    /* @Query("SELECT i FROM Inventory i where i.user_id = :user_id AND i.item_id = :item_id")
    Item getItemByIds(int user_id, int item_id); */

    @Test
    public void testGetItemByIds() {

        assertThat(repo.getItemByIds(176, 15)).isNotNull();
    }


    /** Tests InventoryService method that gets a User's inventory by UserID and appends it to a List **/
    @Test
    public void testGetUserInventory() {
        List<Integer> userInventory = new ArrayList<>();
        repo.getInventoryByUserId(1).forEach(userInventory::add);

        assertThat(userInventory).isNotNull();
    }
}
