package com.erikender.webapp.championsquest;

import com.erikender.webapp.model.Inventory;
import com.erikender.webapp.model.Item;
import com.erikender.webapp.model.User;
import com.erikender.webapp.repository.InventoryRepository;
import com.erikender.webapp.repository.UserRepository;
import com.erikender.webapp.services.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class InventoryServiceTests {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InventoryService inventoryService;

    @Test
    public void testGetUserInventory() {
        User user = userRepository.findByEmail("eender021@hotmail.com");
        Inventory inventory = new Inventory(user.getId(), user, 11);
        List<Item> userInventory = new ArrayList<>();

        if (inventoryRepository.getInventoryByUserId(user.getId()) == null) {
            inventoryRepository.save(inventory);
        }
        else {
            userInventory =  inventoryService.getUserInventory(user.getId());
        }
        assertThat(userInventory).isNotNull();
    }
}
