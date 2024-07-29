package com.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.entity.Employee;
import com.myapp.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo repository;

	public Employee findEmployeeByEmail(String email) {
		return repository.findByEmail(email);

	}

	public void registerUser(Employee employee) {
		repository.save(employee);

	}

}
