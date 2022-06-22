package com.erikender.webapp.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Entity connected to the Characters table.  Contains values for each individual character.
 */

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "characters")
public class MyCharacter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "char_id")
	private int char_id;

	// ID of the user the Character belongs to.  Allows for unique characters across multiple users.
	@Column(name = "user_id")
	private int user_id;

	// Character's default name, serves as a pseudo PK but not unique
	@Column(name = "name")
	private String name;

	// Character's model
	@Column(name = "model")
	private String model;

	// Character's HP
	@Column(name = "health")
	private int health;

	// Character's MP
	@Column(name = "mana")
	private int mana;

	// Amount of XP the character currently has
	@Column(name = "xp")
	private int xp;

	// Character's current level
	@Column(name = "level")
	private int level;

	// Character's altered name, if applicable
	@Column(name = "altered_name")
	private String alteredName;

	// Amount of gold a character is carrying.  Only used by Hero character.
	@Column(name = "gold")
	private int gold;

	public MyCharacter(int user_id, String name, String model, int health, int mana, int xp, int level, String alteredName) {
		this.user_id = user_id;
		this.name = name;
		this.model = model;
		this.health = health;
		this.mana = mana;
		this.xp = xp;
		this.level = level;
		this.alteredName = alteredName;
	}

	// Getters and setters
	public int getChar_id() {
		return char_id;
	}

	public void setChar_id(int char_id) {
		this.char_id = char_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAlteredName() {
		return alteredName;
	}

	public void setAlteredName(String alteredName) {
		this.alteredName = alteredName;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
}
