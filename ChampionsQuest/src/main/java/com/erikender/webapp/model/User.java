package com.erikender.webapp.model;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity attached to Users table.  Contains values for each individual User.
 */

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	// User's e-mail/username
	@Column(unique = true, nullable = false, name = "email")
	private String email;

	// User's password
	@Column(name = "password", nullable = false, length = 64)
	private String password;

	// User's first name, not required.
	@Column(name = "first_name", length = 30)
	private String firstName;

	// User's last name, not required.
	@Column(name = "last_name", length = 30)
	private String lastName;

	// User's in-game name, not required.
	@Column(name = "in_game_name", length = 30)
	private String inGameName;

	// Showing User's connection to Items through Inventory and assigning them to the playerItems list
	@ManyToMany
	@JoinTable(
			name = "inventory",
				joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<Item> playerItems = new ArrayList<>();

	public User (String email, String password, String firstName, String lastName, String inGameName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.inGameName = inGameName;
	}

	// Getters and setters
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
