/**
 * 
 */
package com.skp.casntemplt.demo.service.impl;

import java.util.List;

import com.skp.casntemplt.models.Employee;



/**
 * @author user
 *
 */

public interface EmployeeService {
	
	public List<Employee> getAllEmployeeDetails();
	
	
	public Employee getEmployeeDetails(int empId);

	 
	public int updateEmployeeDetails(Employee empToUpdate);
	
	public String insertAnEmployee(Employee employee);
	
	public String deleteEmployeeDetails(int empId);

}
