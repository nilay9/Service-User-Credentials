package com.userManagementService.ServiceUserCredentials.pojo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class UserBasicDetails {
	
	@Id
	@NotNull
	private String id;
	@Field
	@NotNull
	private String name;
	@Field
	@NotNull
	private String username;
	@Field
	@NotNull
	private String password;
	@Field
	@NotNull
	private String userroles;
	
	public UserBasicDetails() {}

	public UserBasicDetails(String id, String name, String username, String password, String userroles) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.userroles = userroles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUserroles() {
		return userroles;
	}

	public void setUserroles(String userroles) {
		this.userroles = userroles;
	}
}
