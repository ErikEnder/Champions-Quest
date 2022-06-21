package com.erikender.webapp.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shop_id;

    @Column(name = "name")
    private String name;


    public Shop (int shop_id, String name) {
        this.shop_id = shop_id;
        this.name = name;
    }

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
