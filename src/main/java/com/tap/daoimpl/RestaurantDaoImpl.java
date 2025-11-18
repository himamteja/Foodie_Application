package com.tap.daoimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;
import com.tap.util.DBConnection;

public class RestaurantDaoImpl implements RestaurantDao {

	private static final String INSERT_QUERY = "INSERT into `restaurant` (`restaurantid`, `name`, `address`, `phone`, `rating`, `cusinetype`, `isactive`, `eta`, `adminuserid`, `imagepath`)"
			+ "values (?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_QUERY = "SELECT * from `restaurant` where `restaurantid`=?";

	private static final String UPDATE_QUERY = "UPDATE `restaurant` set name = ?, address = ?, phone = ?, rating = ?, cusinetype = ?, "
			+ "isactive = ?, eta = ?, adminuserid = ?, imagepath = ? WHERE restaurantid = ?";

	private static final String DELETE_QUERY = "DELETE from `restaurant` where `restaurantid`=?";

	private static final String GET_ALL_USERS = "SELECT * from `restaurant`";

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ADD RESTAURANT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void addRestaurant(Restaurant restaurant) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);){

			statement.setInt(1, restaurant.getRestaurantid());
			statement.setString(2, restaurant.getName());
			statement.setString(3, restaurant.getAddress());
			statement.setInt(4, restaurant.getPhone());
			statement.setFloat(5, restaurant.getRating());
			statement.setString(6, restaurant.getCusinetype());
			statement.setBoolean(7, restaurant.isIsactive());
			statement.setString(8, restaurant.getEta());
			statement.setInt(9, restaurant.getAdminuserid());
			statement.setString(10, restaurant.getImagepath());

			int res = statement.executeUpdate();

			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET RESTAURANT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public Restaurant getRestaurant(int id) {

		Restaurant restaurant = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				restaurant = new Restaurant (result.getInt("restaurantid")
						, result.getString("name")
						, result.getString("address")
						, result.getInt("phone")
						, result.getFloat("rating")
						, result.getString("cusinetype")
						, result.getBoolean("isactive")
						, result.getString("eta")
						, result.getInt("adminuserid")
						, result.getString("imagepath"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurant;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> UPDATE RESTAURANT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void updateRestaurant(Restaurant restaurant) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {

			statement.setString(1, restaurant.getName());
			statement.setString(2, restaurant.getAddress());
			statement.setInt(3, restaurant.getPhone());
			statement.setFloat(4, restaurant.getRating());
			statement.setString(5, restaurant.getCusinetype());
			statement.setBoolean(6, restaurant.isIsactive());
			statement.setString(7, restaurant.getEta());
			statement.setInt(8, restaurant.getAdminuserid());
			statement.setString(9, restaurant.getImagepath());
			statement.setInt(10, restaurant.getRestaurantid());

			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DELETE RESTAURANT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void deleteRestaurant(int id) {

		Restaurant rastaurant = getRestaurant(id);

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {

			statement.setInt(1, id);

			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ALL RESTAURANT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public List<Restaurant> getAllRestaurant() {

		Restaurant rastaurant = null;

		List<Restaurant> list = new ArrayList<Restaurant>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS);) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				rastaurant = new Restaurant (result.getInt("restaurantid")
						, result.getString("name")
						, result.getString("address")
						, result.getInt("phone")
						, result.getFloat("rating")
						, result.getString("cusinetype")
						, result.getBoolean("isactive")
						, result.getString("eta")
						, result.getInt("adminuserid")
						, result.getString("imagepath"));

				list.add(rastaurant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
