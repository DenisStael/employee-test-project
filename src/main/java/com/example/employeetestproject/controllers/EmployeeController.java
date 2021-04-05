package com.example.employeetestproject.controllers;

import java.util.List;

import com.example.employeetestproject.exceptions.ResourceNotFoundException;
import com.example.employeetestproject.models.Employee;
import com.example.employeetestproject.persistence.EmployeePersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Denis Stael
 */

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeePersistence employeePersistence;

	// All
	@GetMapping("/employees")
	List<Employee> all() {
		return employeePersistence.findAll();
	}

	// Show
	@GetMapping("/employees/{id}")
	Employee show(@PathVariable Long id) {
		Employee employee = new Employee();

		return employeePersistence.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(employee.getClass().getSimpleName(), id));
	}

	// Create
	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee employee) {
		return employeePersistence.save(employee);
	}

	// Update
	@PutMapping("/employees/{id}")
	Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		Employee employee = new Employee();
		String className = employee.getClass().getSimpleName();

		employee = employeePersistence.findById(id).orElseThrow(() -> new ResourceNotFoundException(className, id));

		if (newEmployee.getName() != null) {
			employee.setName(newEmployee.getName());
		}

		if (newEmployee.getLastName() != null) {
			employee.setLastName(newEmployee.getLastName());
		}

		if (newEmployee.getEmail() != null) {
			employee.setEmail(newEmployee.getEmail());
		}

		if (newEmployee.getNisNumber() != null) {
			employee.setNisNumber(newEmployee.getNisNumber());
		}

		return employeePersistence.save(employee);
	}

	// Delete
	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		Employee employee = new Employee();
		String className = employee.getClass().getSimpleName();

		employee = employeePersistence.findById(id).orElseThrow(() -> new ResourceNotFoundException(className, id));

		employeePersistence.delete(employee);
	}
}
