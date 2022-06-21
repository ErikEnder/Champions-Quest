package com.erikender.webapp.championsquest;

import com.erikender.webapp.model.Inventory;
import com.erikender.webapp.repository.InventoryRepository;
import com.erikender.webapp.services.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class InventoryRepositoryTests {

    @Autowired
    private InventoryRepository repo;

    InventoryService inventoryService = new InventoryService();

    /** @Query("SELECT i FROM Inventory i WHERE i.userId = ?1")
        List<Inventory> getInventoryByUserId(int id); **/

    @Test
    public void testGetInventoryByUserId() {

        List<Integer> inventory = repo.getInventoryByUserId(1);

        System.out.println(inventory);
    }
}
