package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.model.User;
import com.tap.util.DBConnection;

public class UserDaoImpl implements UserDao {


	private static final String INSERT_QUERY = "INSERT into `user` (`name`, `username`, `password`, `email`, `phone`, `address`, `role`, `createdate`, `lastlogindate`)"
												+ "values (?,?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_QUERY = "SELECT * from `user` where `username` = ?";
	
	private static final String UPDATE_QUERY = "UPDATE `user` set `userid`=?, `name`=?, `username`=?, "
												+ "`password`=?, `email`=?, `phone`=?, `address`=? where `userid`=?";
	
	private static final String GET_ALL_USERS = "SELECT * from `user`";

	private static final String DELETE_QUERY = "DELETE from `user` where `userid`=?";

	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ADD USER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	@Override
	public int addUser(User user) {

		int res = 0;
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);){

			
			statement.setString(1, user.getName());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPhone());
			statement.setString(6, user.getAddress());
			statement.setString(7, user.getRole());
			statement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			statement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			
			System.out.println("1 "+ user.getName());
			System.out.println("2 "+ user.getUsername());
			System.out.println("3 "+ user.getPassword());
			System.out.println("4 "+ user.getEmail());
			System.out.println("5 "+ user.getPhone());
			System.out.println("6 "+ user.getAddress());
			System.out.println("7 "+ user.getRole());
			System.out.println("8 "+ new Timestamp(System.currentTimeMillis()));
			System.out.println("9 "+  new Timestamp(System.currentTimeMillis()));

			res = statement.executeUpdate();

			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return res;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET USER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public User getUser(String username) {
		User user = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);) {

			statement.setString(1, username);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				user = new User (result.getInt("userid")
						, result.getString("name")
						, result.getString("username")
						, result.getString("password")
						, result.getString("email")
						, result.getString("phone")
						, result.getString("address")
						, result.getString("role")
						, result.getTimestamp("createdate")
						, result.getTimestamp("lastlogindate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> UPDATE USER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void updateUser(User user) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			
			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getPhone());
			statement.setString(7, user.getAddress());
			statement.setInt(8, user.getUserId());
			
			int res = statement.executeUpdate();
			System.out.println(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DELETE USER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void deleteUser(int id) {

//		User user = getUser(id);

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
			
			statement.setInt(1, id);
			
			int res = statement.executeUpdate();
			System.out.println(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ALL USER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public List<User> getAllUsers() {
		
		User user = null;
		
		List<User> list = new ArrayList<User>();
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS);) {
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				user = new User (result.getInt("userid")
						, result.getString("name")
						, result.getString("username")
						, result.getString("password")
						, result.getString("email")
						, result.getString("phone")
						, result.getString("address")
						, result.getString("role")
						, result.getTimestamp("createdate")
						, result.getTimestamp("lastlogindate"));
				
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
