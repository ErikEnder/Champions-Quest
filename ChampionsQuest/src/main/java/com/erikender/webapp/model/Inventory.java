package com.erikender.webapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@IdClass(InventoryPK.class)
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {
    @Id
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private int user_id;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Id
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private int item_id;

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
