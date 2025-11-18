package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.OrderItemDao;
import com.tap.model.OrderItem;
import com.tap.model.OrderItem;
import com.tap.util.DBConnection;

public class OrderItemDaoImpl implements OrderItemDao {

	
	private static final String INSERT_ORDER_ITEM = "INSERT into `orderitems` (`orderid`, `menuid`, `quantity`, `totalprice`) "
													+ "values(?, ?, ?, ?)";
	
	private static final String SELECT_ORDER_ITEM = "SELECT * from `orderitems` where `orderitemid`=?";

	private static final String DELETE_ORDER_ITEM = "DELETE from `orderitems` where `orderitemid`=?";

	private static final String GET_ALL_ORDER_ITEM = "SELECT * from `orderitems`";

	private static final String UPDATE_ORDER_ITEM = "UPDATE `orderitems` set `orderid`=?, `menuid`=?, `totalprice`=?, `quantity`=? where `orderitemid`=?";

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ADD ORDER_ITEM <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public void addOrderItem(OrderItem orderitem) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_ITEM);) {
			
			statement.setInt(1, orderitem.getOrderid());
			statement.setInt(2, orderitem.getMenuid());
			statement.setInt(3, orderitem.getQuantity());
			statement.setDouble(4, orderitem.getTotalprice());
			
			
			int res = statement.executeUpdate();
			
			System.out.println(res);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ORDER_ITEM <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public OrderItem getOrderItem(int id) {

		OrderItem orderitem = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_ITEM);) {

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				orderitem = new OrderItem (
						 result.getInt("orderid")
						, result.getInt("menuid")
						, result.getInt("quantity")
						, result.getInt("totalprice"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderitem;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> UPDATE ORDER_ITEM <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public void updateOrderItem(OrderItem orderitem) {
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_ITEM);) {

			statement.setInt(1, orderitem.getOrderid());
			statement.setInt(2, orderitem.getMenuid());
			statement.setDouble(3, orderitem.getTotalprice());
			statement.setInt(4, orderitem.getQuantity());
			statement.setInt(5, orderitem.getOrderitemid());
			
			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DELETE ORDER_ITEM <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public void deleteOrderItem(int id) {
		
		OrderItem orderitem = getOrderItem(id);

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_ITEM);) {

			statement.setInt(1, id);

			int res = statement.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GET ALL ORDER_ITEM <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public List<OrderItem> getAllOrderItem() {
		
		OrderItem orderitem = null;

		List<OrderItem> list = new ArrayList<OrderItem>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDER_ITEM);) {

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				orderitem = new OrderItem (
						 result.getInt("orderid")
						, result.getInt("menuid")
						, result.getInt("quantity")
						, result.getInt("totalprice"));

				list.add(orderitem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
