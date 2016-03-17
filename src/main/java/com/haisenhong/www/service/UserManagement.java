package com.haisenhong.www.service;

import java.util.List;

import com.haisenhong.www.model.User;

public interface UserManagement {

	public boolean saveUser(User user);
	public User getUser(long id);
	public List<User> getAllUsers();
	public boolean updateUser(long id, User user);
	public boolean deleteUser(long id);
	public boolean deleteAll();
	public boolean isUserExists(long id);
	
}
