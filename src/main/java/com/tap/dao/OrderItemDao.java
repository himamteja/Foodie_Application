package com.tap.dao;

import java.util.List;
import com.tap.model.OrderItem;

public interface OrderItemDao {
	
	void addOrderItem(OrderItem orderitem);
	OrderItem getOrderItem(int id);
	void updateOrderItem(OrderItem orderitem);
	void deleteOrderItem(int id);
	List<OrderItem> getAllOrderItem();
	
}
