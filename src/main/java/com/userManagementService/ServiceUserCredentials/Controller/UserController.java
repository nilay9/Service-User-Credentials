package com.userManagementService.ServiceUserCredentials.Controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userManagementService.ServiceUserCredentials.pojo.UserBasicDetails;
import com.userManagementService.ServiceUserCredentials.services.UserOperationService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserOperationService userOperationService;
	
	@PostMapping("/add")
	public UserBasicDetails addUserDetails(@RequestBody UserBasicDetails userDetails) {
		
		return userOperationService.addUser(userDetails);
	}

	@GetMapping("admin/{id}")
	public Optional<UserBasicDetails> getUserDetails(@PathVariable String id) {
		System.out.print(id);
		 if (userOperationService.existsById(id)) {
	            return userOperationService.findById(id);
	        } else
	            return Optional.empty();
	}
	
	@GetMapping("/name/{name}")
	public UserBasicDetails getUserNameDetail(@PathVariable String name) {
		
		return userOperationService.findByUsername(name);        
	}

}
