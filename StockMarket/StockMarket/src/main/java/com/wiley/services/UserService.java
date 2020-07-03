package com.wiley.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.dto.LoginDTO;
import com.wiley.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public int insertRecords(String uname,String password,String email)
	{
		return userRepository.insertUser(uname, password, email);
	}
	public int deleteRecords(int uId)
	{
		return userRepository.deleteUser(uId);
	}
	public int updateRecords(String email,String password)
	{
		return userRepository.updateUser(email, password);
	}
	
	public int loginUser(String userName,String password)
	{
		
		List<LoginDTO> l = userRepository.findAllUsers();
		
		for(LoginDTO i : l)
		{
			if(i.getUserName().equals(userName) && i.getPassword().equals(password))
			{
				return i.getUserId();
			}
		}
		return 0;
	}
}
