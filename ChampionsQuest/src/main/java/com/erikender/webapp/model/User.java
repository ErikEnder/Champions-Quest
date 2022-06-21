package com.erikender.webapp.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(unique = true, nullable = false, name = "email")
	private String email;

	@Column(name = "password", nullable = false, length = 64)
	private String password;

	@Column(name = "first_name", length = 30)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(name = "in_game_name", length = 30)
	private String inGameName;

	@ManyToMany
	@JoinTable(
			name = "inventory",
				joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<Item> playerItems = new ArrayList<>();

	public User (int id, String email, String password, String firstName, String lastName, String inGameName) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.inGameName = inGameName;
	}

	public int getId() {
		return id;
	}

	public void setId(int user_id) {
		this.id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInGameName() {
		return inGameName;
	}

	public void setInGameName(String inGameName) {
		this.inGameName = inGameName;
	}

	public void addItem(Item item) {
		playerItems.add(item);
	}

	public List<Item> getPlayerItems() {
		return playerItems;
	}
}
