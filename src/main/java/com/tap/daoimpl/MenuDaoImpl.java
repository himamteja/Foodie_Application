package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.MenuDao;
import com.tap.model.Menu;
import com.tap.model.Orders;
import com.tap.util.DBConnection;

public class MenuDaoImpl implements MenuDao {

	private static final String INSERT_MENU = "INSERT into `menu` (`menuid`, `restaurantid`, `itemname`, `description`, `price`, `ratings`, `isavailable`, `imagepath`) "
												+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SELECT_MENU = "SELECT * from `menu` where `menuid`=?";
	
	private static final String DELETE_MENU = "DELETE from `menu` where `menuid`=?";

	private static final String GET_ALL_Menu = "SELECT * from `menu`";

	private static final String UPDATE_MENU = "UPDATE `menu` set `restaurantid`=?, `itemname`=?, `description`=?, `price`=? where `menuid`=?";

	private static final String GET_ALL_MENU_BY_RESTAURANT_ID = "SELECT * from `menu` where `restaurantid`=?";
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ADD MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void addMenu(Menu menu) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_MENU);) {
			
			statement.setInt(1, menu.getMenuid());
			statement.setInt(2, menu.getRestaurantid());
			statement.setString(3, menu.getItemname());
			statement.setString(4, menu.getDescription());
			statement.setInt(5, menu.getPrice());
			statement.setFloat(6, menu.getRatings());
			statement.setBoolean(7, menu.getIsavailable());
			statement.setString(8, menu.getImagepath());
			
			int res = statement.executeUpdate();
			
			System.out.println(res);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public Menu getMenu(int id) {
		
		Menu menu = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_MENU);) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				menu = new Menu (result.getInt("menuid")
						, result.getInt("restaurantid")
						, result.getString("itemname")
						, result.getString("description")
						, result.getInt("price")
						, result.getFloat("ratings")
						, result.getBoolean("isavailable")
						, result.getString("imagepath"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menu;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> UPDATE MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	@Override
	public void updateMenu(Menu menu) {
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MENU);) {

			statement.setInt(1, menu.getRestaurantid());
			statement.setString(2, menu.getItemname());
			statement.setString(3, menu.getDescription());
			statement.setInt(4, menu.getPrice());
			statement.setInt(5, menu.getMenuid());

			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DELETE MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	@Override
	public void deleteMenu(int id) {
		
		Menu menu = getMenu(id);

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MENU);) {

			statement.setInt(1, id);

			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ALL MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	@Override
	public List<Menu> getAllMenu() {
		
		Menu menu = null;

		List<Menu> list = new ArrayList<Menu>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_ALL_Menu);) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
						menu = new Menu (result.getInt("menuid")
								, result.getInt("restaurantid")
								, result.getString("itemname")
								, result.getString("description")
								, result.getInt("price")
								, result.getFloat("ratings")
								, result.getBoolean("isavailable")
								, result.getString("imagepath"));

				list.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}



// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ALL MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public List<Menu> getAllMenuByRestaurantId(int id) {

		Menu menu2 = null;

		List<Menu> list = new ArrayList<Menu>();

		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_MENU_BY_RESTAURANT_ID);

			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while(result.next()) {

				menu2 = new Menu (result.getInt("menuid")
						, result.getInt("restaurantid")
						, result.getString("itemname")
						, result.getString("description")
						, result.getInt("price")
						, result.getFloat("ratings")
						, result.getBoolean("isavailable")
						, result.getString("imagepath"));

				list.add(menu2);		

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}