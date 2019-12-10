package com.userManagementService.ServiceUserCredentials.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userManagementService.ServiceUserCredentials.pojo.UserBasicDetails;
import com.userManagementService.ServiceUserCredentials.pojo.UserPrincipal;


@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private UserOperationService userOperationService; 
	
	public MyUserDetailsService(UserOperationService userOperationService) {
		this.userOperationService = userOperationService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				 
				UserBasicDetails hbsc = userOperationService.findByUsername(username);
				if (hbsc == null) {
				      throw new UsernameNotFoundException("No user found for "+ username + ".");
			    }
				UserPrincipal userPrincipal = new UserPrincipal(hbsc);
				return userPrincipal;
	}
	

}
