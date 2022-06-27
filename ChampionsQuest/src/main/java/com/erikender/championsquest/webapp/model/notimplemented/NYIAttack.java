package com.erikender.championsquest.webapp.model.notimplemented;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


/** Entity attached to Character/Enemy attacks.  Not currently implemented in the final build **/
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "attacks")
public class NYIAttack {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attack_id")
	private int attack_id;

	// Attack name
	@Column(name = "name")
	private String name;

	// Attack's damage, if any
	@Column(name = "damage")
	private int damage;

	// Attack's healing, if any
	@Column(name = "healing")
	private int healing;

	// Attack's mana cost
	@Column(name = "cost")
	private int cost;
	
	public NYIAttack(int attack_id, String name, int damage, int healing, int cost) {
		this.attack_id = attack_id;
		this.name = name;
		this.damage = damage;
		this.healing = healing;
		this.cost = cost;
	}

	// Setters and getters
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
