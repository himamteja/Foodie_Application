package com.tap.dao;

import java.util.List;
import com.tap.model.User;

public interface UserDao {
	
	int addUser(User user);
	User getUser(String username);
	void updateUser(User user);
	void deleteUser(int id);
	List<User> getAllUsers();
	
}
