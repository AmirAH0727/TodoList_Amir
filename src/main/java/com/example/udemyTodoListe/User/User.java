package com.example.udemyTodoListe.User;

import com.example.udemyTodoListe.validition.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import com.example.udemyTodoListe.Todo.Todo;
import org.hibernate.validator.constraints.Length;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(min = 3, message = "Username should have atleast 6 characters")
	@Column(nullable = false)
	private String userName;
	@NotNull
	@Email
	@Column(unique = true, nullable = false)
	private String email;

	@NotNull
	@NotBlank
	@Length (min = 6, message = "Password should have atleast 6 characters")
	@Column(nullable = false)
	private String password;


	@Phone
	private String phone;

	@OneToMany
	@JoinColumn(name = "userId")
	private Set <Todo> todos;

	public String getPhone() {

		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public Set<Todo> getTodos() {
		return todos;
	}

	public void setTodos(Set<Todo> todos) {
		this.todos = todos;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
