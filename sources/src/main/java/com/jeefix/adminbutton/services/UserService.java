/**
 * 
 */
package com.jeefix.adminbutton.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefix.adminbutton.model.User;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class UserService 
{
	//@Autowired private UserRepository userRepository;
	
	public List<User> findAllUsers() {
		return null;
	}
	
}
