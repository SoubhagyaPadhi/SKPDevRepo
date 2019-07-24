package com.skp.casntemplt.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Select.Where;
import com.skp.casntemplt.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private CassandraOperations cassandraTemplate;
	
	

	@Override
	public List<Employee> getAllEmployeeDetails() {
		Select allEmployeesQuery = QueryBuilder.select().from("Employee");
		return cassandraTemplate.select(allEmployeesQuery, Employee.class);
	}

	@Override
	public Employee getEmployeeDetails(int empId) {
		Where whereCl = QueryBuilder.select().from("Employee").where(QueryBuilder.eq("id",empId));
		return cassandraTemplate.selectOne(whereCl, Employee.class);
	}

	@Override
	public int updateEmployeeDetails(Employee empToUpdate) {
		Where whereCl = QueryBuilder.select().from("Employee").where(QueryBuilder.eq("id",empToUpdate.getId()));
		Employee existingEmployee = cassandraTemplate.selectOne(whereCl, Employee.class);
		existingEmployee.setFirstName(empToUpdate.getFirstName());
		existingEmployee.setLastName(empToUpdate.getLastName());
		cassandraTemplate.insert(existingEmployee);
		return existingEmployee.getId();
	}

}
