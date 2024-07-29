package com.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.myapp.entity.Employee;
import com.myapp.service.EmployeeService;

@Controller
public class HomeController {

	@Autowired
	EmployeeService service;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "register";
	}

	@PostMapping("/register")
	public String registerProccess(@ModelAttribute Employee employee, Model model) {
		Employee existingEmployee = service.findEmployeeByEmail(employee.getEmail());
		if (existingEmployee != null) {
			model.addAttribute("message", "username already exist ");
			return "register";
		}
		service.registerUser(employee);
		model.addAttribute("message", "User registerd successfuly");
		return "login";

	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "login";
	}

	@PostMapping("/login")
	public String loginPage(@ModelAttribute Employee employee, Model model) {
		Employee existingEmployee = service.findEmployeeByEmail(employee.getEmail());
		if (existingEmployee == null || !existingEmployee.getPassword().equals(employee.getPassword())) {
			model.addAttribute("message", "Invalid username and password");
			return "login";
		}
		model.addAttribute("message", "Login successful");
		model.addAttribute("username", existingEmployee.getFirstName());
		return "about";
	}
}
