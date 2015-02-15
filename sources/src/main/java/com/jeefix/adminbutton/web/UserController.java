/**
 * 
 */
package com.jeefix.adminbutton.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeefix.adminbutton.model.User;
import com.jeefix.adminbutton.services.UserService;

/**
 * @author Siva
 *
 */
@ManagedBean
@SessionScoped
@Component
public class UserController {

	@Autowired
	private UserService userService;
	
	private List<User> users = null;
	
	public UserController() {
		
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<User> getUsers() {
		if(users == null){
			users = userService.findAllUsers();
		}
		return users;
	}
}
