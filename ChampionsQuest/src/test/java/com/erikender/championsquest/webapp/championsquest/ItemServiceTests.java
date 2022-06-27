package com.erikender.championsquest.webapp.championsquest;

import com.erikender.championsquest.webapp.model.Item;
import com.erikender.championsquest.webapp.repository.ItemRepository;
import com.erikender.championsquest.webapp.services.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ItemServiceTests {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    /** Test that checks to see you can access the items in the repo and assign them to a list for further use **/
    @Test
    public void testGetShopItems() {
        List<Item> shopItems = itemService.getShopItems(1);

        assertThat(shopItems).isNotNull();
    }

    @Test
    public void testGetItemById() {
        Item item = itemService.getItemById(11);

        assertThat(item).isNotNull();
    }
}
