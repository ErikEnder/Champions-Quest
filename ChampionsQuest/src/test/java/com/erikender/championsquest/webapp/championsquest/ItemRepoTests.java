package com.erikender.championsquest.webapp.championsquest;

import com.erikender.championsquest.webapp.model.Item;
import com.erikender.championsquest.webapp.repository.ItemRepository;
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
public class ItemRepoTests {

    @Autowired
    private ItemRepository repo;

    /*  @Query("SELECT i FROM Item i WHERE i.item_id = ?1")
    Item findById(int item_id); */

    /** Test that checks to see that you can find an item by its ID**/
    @Test
    public void testFindById() {
        Item item = repo.findById(11);

        assertThat(item).isNotNull();

        System.out.println("Item ID: " + item.getItem_id() + " Item Name: " + item.getName());
        System.out.println("Item Attack: " + item.getAttack() + " Item Defense: " + item.getDefense() +
                " Item Price: " + item.getPrice() + " Item Description: " + item.getDesc());
    }

    /*   @Query("SELECT i FROM Item i WHERE i.shop_id = ?1")
     List<Item> findAllByShopId(int shop_id);  */

    /** Test that checks to see you can retrieve all of the items in a shop by the shop's ID **/
    @Test
    public void testFindAllByShop() {
        List<Item> shop = repo.findAllByShopId(1);

        assertThat(shop).isNotNull();

        System.out.println("Shop Size: " + shop.size());
        System.out.println("Shop Items: " + shop.toString());
    }

    /* @Query("SELECT i FROM Item i WHERE i.name = ?1")
     Item findByName(String name); */

    /** Test that checks to see you can find an item by its name **/
    @Test
    public void testFindByName() {
        Item item = repo.findByName("Steel Sword");

        assertThat(item).isNotNull();

        System.out.println("Item ID: " + item.getItem_id());
    }


}
