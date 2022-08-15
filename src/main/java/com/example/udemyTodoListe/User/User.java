package com.example.udemyTodoListe.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import com.example.udemyTodoListe.Todo.Todo;

import java.util.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Email
	@Column(unique = true, nullable = false)
	private String email;

	@NotNull
	@NotBlank
	@Size(min = 6, message = "Password should have atleast 6 characters")
	@Column(nullable = false)
	private String password;

	@NotNull
	@Size(min = 3, message = "Username should have atleast 6 characters")
	@Column(nullable = false)
	private String username;

	@OneToMany
	@JoinColumn(name = "userId")
	private Set <Todo> todos;

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Todo> getTodos() {
		return todos;
	}

	public void setTodos(Set<Todo> todos) {
		this.todos = todos;
	}
}
