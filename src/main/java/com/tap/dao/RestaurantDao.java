package com.tap.dao;

import java.util.List;


import com.tap.model.Restaurant;


public interface RestaurantDao {
	void addRestaurant(Restaurant rastaurant);
	Restaurant getRestaurant(int id);
	void updateRestaurant(Restaurant rastaurant);
	void deleteRestaurant(int id);
	List<Restaurant> getAllRestaurant();
}
