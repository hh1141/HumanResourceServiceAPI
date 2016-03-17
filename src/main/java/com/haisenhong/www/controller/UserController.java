package com.haisenhong.www.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.haisenhong.www.model.User;
import com.haisenhong.www.service.UserManagement;
import com.haisenhong.www.service.UserManagementService;

@RestController
public class UserController {
	
//	@Autowired
//	UserManagement userService;
	UserManagement userService = new UserManagementService();
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		User user = userService.getUser(id);
		if (user == null) {
			System.out.println("User with id: " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//		if (userService.isUserExists(user.getId())) {
//			System.out.println("A user with name: " + user.getName() + " already exist");
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}
//		user.setId(counter.getAndIncrement());
		userService.saveUser(user);
		long id = UserManagementService.curId;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		if (!userService.isUserExists(id)) {
			System.out.println("User with id: " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.updateUser(id, user);
		User currentUser = userService.getUser(id);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		if(!userService.isUserExists(id)) {
			System.out.println("User with id: " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllUsers() {
		userService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	public UserManagement getUserService() {
		return userService;
	}
	
	public void setUserService(UserManagement userService) {
		this.userService = userService;
	}
	
}
