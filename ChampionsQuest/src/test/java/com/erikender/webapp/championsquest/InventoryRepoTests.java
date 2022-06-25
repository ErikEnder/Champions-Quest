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
public class InventoryRepoTests {

    @Autowired
    private InventoryRepository inventoryRepository;

    /* @Query("SELECT i FROM Inventory i WHERE i.userId = ?1")
        List<Inventory> getInventoryByUserId(int id); */

    /** Tests repo method that returns user inventory by User ID **/
    @Test
    public void testGetInventoryByUserId() {

        List<Integer> inventory = inventoryRepository.getInventoryByUserId(1);
        assertThat(inventory).isNotNull();

        System.out.println(inventory);
    }

    /** Tests InventoryService method that gets a User's inventory by UserID and appends it to a List **/
    @Test
    public void testGetUserInventory() {
        List<Integer> userInventory = new ArrayList<>();
        inventoryRepository.getInventoryByUserId(1).forEach(userInventory::add);

        assertThat(userInventory).isNotNull();
    }
}
