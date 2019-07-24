/**
 * 
 */
package com.skp.casntemplt.demo.service.impl;

import java.util.List;

import com.skp.casntemplt.model.Employee;


/**
 * @author user
 *
 */

public interface EmployeeService {
	
	public List<Employee> getAllEmployeeDetails();
	
	
	public Employee getEmployeeDetails(int empId);

	 
	public int updateEmployeeDetails(Employee empToUpdate);

}
