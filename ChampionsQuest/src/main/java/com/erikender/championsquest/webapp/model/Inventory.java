package com.erikender.championsquest.webapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/** Entity attached to each individual User's player inventory.
 * Comprised of a composite key using user_id and item_id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@IdClass(InventoryPK.class) // Tells the Inventory what its composite Primary Key is.
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {
    @Id
    @JoinColumn(name = "user_id", insertable = false, updatable = false) // Connects to User table via this
    private int user_id;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false) // Assigns User object to the inventory
    private User user;

    @Id
    @JoinColumn(name = "item_id", insertable = false, updatable = false) // Connects to Item table via this
    private int item_id;

    // Getters and setters
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
