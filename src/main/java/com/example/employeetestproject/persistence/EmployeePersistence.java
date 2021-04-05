package com.example.employeetestproject.persistence;

import com.example.employeetestproject.models.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePersistence extends JpaRepository<Employee, Long>{
  
}
