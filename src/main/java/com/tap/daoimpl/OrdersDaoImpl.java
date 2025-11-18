package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrdersDao;
import com.tap.model.Orders;
import com.tap.util.DBConnection;

public class OrdersDaoImpl implements OrdersDao {

	private static final String INSERT_ORDERS = "INSERT into `Orders` (`userid`, `restaurantid`, `orderdate`, `totalamount`, `status`, `paymentmode`) "
													+ "values(?, ?, ?, ?, ?, ?)";
	
	private static final String SELECT_ORDERS = "SELECT * from `orders` where `orderid`=?";
	
	private static final String DELETE_QUERY = "DELETE from `orders` where `orderid`=? ";

	private static final String GET_ALL_ORDERS = "SELECT * from `orders`";

	private static final String UPDATE_ORDERS = "UPDATE `orders` set `orderid`=?, `userid`=?, `restaurantid`=?, `orderdate`=? where `totalamount`=?";
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ADD ORDERS	 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public int addOrders(Orders orders) {
		
		int orderId = 0;
		
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_ORDERS, PreparedStatement.RETURN_GENERATED_KEYS);) {
			
			statement.setInt(1, orders.getUserid());
			statement.setInt(2, orders.getRestaurantid());
			statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			statement.setDouble(4, orders.getTotalamount());
			statement.setString(5, orders.getStatus());
			statement.setString(6, orders.getPaymentmode());
			
			int res = statement.executeUpdate();
			if (res > 0) {
	            
	            try(ResultSet rs = statement.getGeneratedKeys();){
	            	if (rs.next()) {
		                orderId = rs.getInt(1);
		                System.out.println("✅ Order inserted successfully with ID: " + orderId);
		            }
	            }
	        }
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return orderId;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ORDERS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public Orders getOrders(int id) {
		
		Orders orders = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ORDERS);) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				orders = new Orders (result.getInt("orderid")
						, result.getInt("userid")
						, result.getInt("restaurantid")
						, result.getTimestamp("orderdate")
						, result.getInt("totalamount")
						, result.getString("status")
						, result.getString("paymentmode"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
		
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> UPDATE ORDERS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void updateOrders(Orders orders) {
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ORDERS);) {

			statement.setInt(1, orders.getOrderid());
			statement.setInt(2, orders.getUserid());
			statement.setInt(3, orders.getRestaurantid());
			statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			statement.setDouble(5, orders.getTotalamount());

			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DELETE ORDERS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void deleteOrders(int id) {
		
		Orders orders = getOrders(id);

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {

			statement.setInt(1, id);

			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ALL ORDERS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public List<Orders> getAllOrders() {
		
		Orders orders = null;

		List<Orders> list = new ArrayList<Orders>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS);) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				orders = new Orders (result.getInt("orderid")
						, result.getInt("userid")
						, result.getInt("restaurantid")
						, result.getTimestamp("orderdate")
						, result.getInt("totalamount")
						, result.getString("status")
						, result.getString("paymentmode"));

				list.add(orders);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
