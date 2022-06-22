package com.erikender.webapp.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Entity attached to Shop table.  Was initially going to be unique across players, but decided against it
 * in the current implementation.
 */
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "shop")
public class Shop {

    // Only 1 shop currently.  ID will always be 1 as of now.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shop_id;

    // Shop's name
    @Column(name = "name")
    private String name;


    public Shop (int shop_id, String name) {
        this.shop_id = shop_id;
        this.name = name;
    }

    // Getters and setters
    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int id) {
        this.shop_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
