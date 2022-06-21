package com.erikender.webapp.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "attacks")
public class Attack {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attack_id")
	private int attack_id;

	@Column(name = "name")
	private String name;

	@Column(name = "damage")
	private int damage;

	@Column(name = "healing")
	private int healing;

	@Column(name = "cost")
	private int cost;
	
	public Attack(int attack_id, String name, int damage, int healing, int cost) {
		this.attack_id = attack_id;
		this.name = name;
		this.damage = damage;
		this.healing = healing;
		this.cost = cost;
	}

	public int getAttack_id() {
		return attack_id;
	}

	public void setAttack_id(int attack_id) {
		this.attack_id = attack_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealing() {
		return healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
