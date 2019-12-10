package com.userManagementService.ServiceUserCredentials.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userManagementService.ServiceUserCredentials.Dao.UserDetailsDao;
import com.userManagementService.ServiceUserCredentials.pojo.UserBasicDetails;


@Service
public class UserOperationServiceImpl implements UserOperationService{
	
	@Autowired
	UserDetailsDao userDetailsDao;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserBasicDetails addUser(UserBasicDetails user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		return userDetailsDao.save(user);
	}

	@Override
	public List<UserBasicDetails> authUser(String username, String password) {
		return userDetailsDao.existsByUsernameAndPassword(username,password);
	}

	@Override
	public boolean existsById(String id) {
		return userDetailsDao.existsById(id);
	}

	@Override
	public Optional<UserBasicDetails> findById(String id) {
		return userDetailsDao.findById(id);
	}

	@Override
	public UserBasicDetails findByUsername(String username) {
		return userDetailsDao.findByUsername(username);
	}

//	@Override
//	public String authUser(UserLoginView userLoginView) {
////		userLoginView.setPassword(bCryptPasswordEncoder.encode(userLoginView.getPassword()));
//		List<UserBasicDetails> list = userDetailsDao.existsByUsernameAndPassword(userLoginView.getUsername(), userLoginView.getPassword());
//		if(list.size() > 0) {
//			return "Login Successful , You May Proceed!";
//		}
//		return "Login Credentials Invalid";
//	}

	

	

}
