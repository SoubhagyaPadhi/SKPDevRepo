package com.skp.casntemplt.models;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.DataType;

@Component
@Table(value = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@CassandraType(type = DataType.Name.INT)
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
