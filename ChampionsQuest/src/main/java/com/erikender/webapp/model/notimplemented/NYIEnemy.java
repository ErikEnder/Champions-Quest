package com.erikender.webapp.model.notimplemented;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Entity attached to Enemy data.  Not currently implemented in final build **/
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table( name = "enemies" )
public class NYIEnemy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "enemy_id")
	private int enemy_id;

	// Enemy's name
	@Column(name = "name")
	private String name;

	// Enemy's health
	@Column(name = "health")
	private int health;

	// Enemy's mana
	@Column(name = "mana")
	private int mana;

	// Amount of XP the enemy gives
	@Column(name = "xp")
	private int xp;

	public NYIEnemy(int enemy_id, String name, int health, int mana, int xp) {
		this.enemy_id = enemy_id;
		this.name = name;
		this.health = health;
		this.mana = mana;
		this.xp = xp;
	}

	// Setters and getters
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
