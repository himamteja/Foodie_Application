package com.tap.daoimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.tap.dao.CartDao;
import com.tap.model.Cart;

public class CartDaoImpl implements CartDao {

	Map<Integer, Cart> cartItemMap = new LinkedHashMap<Integer, Cart>();
	
	@Override
	public void addCartItem(Cart cart) {
		
		int itemId = cart.getItemid();

		if (cartItemMap.containsKey(itemId)) {
			
			Cart existing = cartItemMap.get(itemId);
			
			existing.setQuantity(existing.getQuantity() + cart.getQuantity());
		} else {
			cartItemMap.put(itemId, cart);
		}
	}

	@Override
	public void updateCartItem(int itemid, int quantity) {
		
		if (cartItemMap.containsKey(itemid)) {
			
			if (quantity <= 0) {
				cartItemMap.remove(itemid);
			} else {
				cartItemMap.get(itemid).setQuantity(quantity);
			}
		}
	}

	@Override
	public void deleteCartItem(int itemid) {
	
		cartItemMap.remove(itemid);
	}
	
	public Map<Integer, Cart> getMap() {
		return cartItemMap; 
	}
	
	public double getTotalPrice() {
		
		return cartItemMap.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
		
	}
	
	public void clear() {
		
		cartItemMap.clear();
	}


}
