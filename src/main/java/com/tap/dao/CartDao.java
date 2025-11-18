package com.tap.dao;

import com.tap.model.Cart;

public interface CartDao {
	
	void addCartItem(Cart cart);
	void updateCartItem(int itemid, int quantity);
	void deleteCartItem(int itemid);

}
