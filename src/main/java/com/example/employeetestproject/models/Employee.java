package com.example.employeetestproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Denis Stael
 */

@Entity
@Table(name = "employees")
public class Employee {
	private long id;
	private String name;
	private String lastName;
	private String email;
	private Integer nisNumber;

	public Employee() {

	}

	public Employee(String name, String lastName, String email, int nisNumber) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.nisNumber = nisNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "last_name", nullable = false, length = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "nis_number", nullable = false)
	public Integer getNisNumber() {
		return nisNumber;
	}

	public void setNisNumber(Integer nisNumber) {
		this.nisNumber = nisNumber;
	}

}
