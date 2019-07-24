package com.skp.casntemplt.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skp.casntemplt.demo.service.impl.EmployeeService;
import com.skp.casntemplt.model.Employee;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empServ;
	
	@RequestMapping(value="/employee/list", method = RequestMethod.POST)
	public ResponseEntity<List<Employee>> getAllEmployeeDetails() {

		List<Employee> empsList = empServ.getAllEmployeeDetails();
		return ResponseEntity.ok().body(empsList);

	}
	
	@RequestMapping(value="/employee/show/{id}", method = RequestMethod.POST)
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable String id) {

		Employee empobj = empServ.getEmployeeDetails(Integer.parseInt(id));
		return ResponseEntity.ok().body(empobj);

	}
	
	@RequestMapping(value="/employee/update", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateEmployeeDetails(@RequestBody Employee employeeToUpdate) {
		
		int empId = empServ.updateEmployeeDetails(employeeToUpdate);
		return ResponseEntity.ok(Integer.valueOf(empId));
	}
	

}
