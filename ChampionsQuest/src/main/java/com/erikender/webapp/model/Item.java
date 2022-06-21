package com.erikender.webapp.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private int item_id;

	@Column(name = "shop_id")
	private int shop_id;

	@Column(name = "name")
	private String name;

	@Column(name = "attack")
	private int attack;

	@Column(name = "defense")
	private int defense;

	@Column(name = "price")
	private int price;

	@Column(name = "description")
	private String desc;

	public Item (int shop_id, String name, int attack, int defense, int price, String desc) {
		this.shop_id = shop_id;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.price = price;
		this.desc = desc;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
