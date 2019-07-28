package com.skp.casntemplt.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.skp.casntemplt.demo.service.impl.EmployeeService;
import com.skp.casntemplt.demo.service.impl.EmployeeServiceImpl;
import com.skp.casntemplt.demo.utils.LogExecutionTime;
import com.skp.casntemplt.models.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empServ;
	
	@RequestMapping(value = "/employee/list", method = RequestMethod.POST)
	@LogExecutionTime
	public ResponseEntity<List<Employee>> getAllEmployeeDetails() {

		return ResponseEntity.ok().body(empServ.getAllEmployeeDetails());
	}

	@RequestMapping(value = "/employee/show/{id}", method = RequestMethod.POST)
	@LogExecutionTime
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable String id) {

		return ResponseEntity.ok().body(empServ.getEmployeeDetails(Integer.parseInt(id)));

	}

	@RequestMapping(value = "/employee/update", method = RequestMethod.POST)
	@LogExecutionTime
	public ResponseEntity<Integer> updateEmployeeDetails(@RequestBody Employee employeeToUpdate) {

		int empId = empServ.updateEmployeeDetails(employeeToUpdate);
		return ResponseEntity.ok(Integer.valueOf(empId));
	}
	
	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.POST)
	@LogExecutionTime
	public ResponseEntity<String> deleteEmployeeDetails(@PathVariable String id) {
		String retStr = empServ.deleteEmployeeDetails(Integer.parseInt(id));
		return ResponseEntity.ok().body(retStr);
	}
	
	@RequestMapping(value = "/employee/insert", method = RequestMethod.POST)
	@LogExecutionTime
	public ResponseEntity<String> insertAnEmployeeDetails(@RequestBody Employee oneEmployee) {
		String retStr = empServ.insertAnEmployee(oneEmployee);
		return ResponseEntity.ok().body(retStr);
	}

	@RequestMapping(value = "/employee/test", method = RequestMethod.GET)
	@LogExecutionTime
	public ResponseEntity<String> testEmployeeDetails() {

		return ResponseEntity.ok().body("Good!!");
	}

}
