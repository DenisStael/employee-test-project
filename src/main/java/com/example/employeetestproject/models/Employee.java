package com.example.employeetestproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.employeetestproject.exceptions.UnprocessableEntityException;

import org.apache.commons.validator.routines.EmailValidator;

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
	private String nisNumber;

	public Employee() {
	}

	public Employee(String name, String lastName, String email, String nisNumber) {
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

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "nis_number", nullable = false)
	public String getNisNumber() {
		return nisNumber;
	}

	public void setNisNumber(String nisNumber) {
		this.nisNumber = nisNumber;
	}

	public void validateModelAttributes() {
		if (this.name.length() < 2 || this.name.length() > 30) {
			messageError("Name cannot contain less than 2 or more than 30 characters");
		}

		if (this.lastName.length() < 2 || this.lastName.length() > 50) {
			messageError("Last name cannot contain less than 2 or more than 50 characters");
		}

		if (!emailValidation()) {
			messageError("Email must be valid");
		}

		if (!nisNumberOnlyNumbers()) {
			messageError("NIS/PIS must contain only numbers");
		}
	}

	private boolean nisNumberOnlyNumbers() {
		try {
			Integer.parseInt(this.nisNumber);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	private boolean emailValidation() {
		return EmailValidator.getInstance().isValid(this.email);
	}

	private void messageError(String message) {
		throw new UnprocessableEntityException(message);
	}

}
