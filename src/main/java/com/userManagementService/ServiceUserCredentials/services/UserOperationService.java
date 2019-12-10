package com.userManagementService.ServiceUserCredentials.services;

import java.util.List;

import java.util.Optional;

import com.userManagementService.ServiceUserCredentials.pojo.UserBasicDetails;

public interface UserOperationService {
	
	public UserBasicDetails addUser(UserBasicDetails user);
	
	public List<UserBasicDetails> authUser(String username,String password);

	public boolean existsById(String id);

	public Optional<UserBasicDetails> findById(String id);

	public UserBasicDetails findByUsername(String username);
//
//	public String authUser(UserLoginView userLoginView);
	

}
