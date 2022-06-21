package com.erikender.webapp.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table( name = "enemies" )
public class Enemy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "enemy_id")
	private int enemy_id;

	@Column(name = "name")
	private String name;

	@Column(name = "health")
	private int health;

	@Column(name = "mana")
	private int mana;

	@Column(name = "xp")
	private int xp;

	public Enemy (int enemy_id, String name, int health, int mana, int xp) {
		this.enemy_id = enemy_id;
		this.name = name;
		this.health = health;
		this.mana = mana;
		this.xp = xp;
	}

	public int getEnemy_id() {
		return enemy_id;
	}

	public void setEnemy_id(int enemy_id) {
		this.enemy_id = enemy_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
}
