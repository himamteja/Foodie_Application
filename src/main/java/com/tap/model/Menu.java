package com.tap.model;

public class Menu {
	private int menuid;
	private int restaurantid;
	private String itemname;
	private String description;
	private int price;
	private float ratings;
	private boolean isavailable;
	private String imagepath;
	
	public Menu() {
		
	}

	public Menu(int menuid, int restaurantid, String itemname, String description, int price, float ratings,
			boolean isavailable, String imagepath) {
		super();
		this.menuid = menuid;
		this.restaurantid = restaurantid;
		this.itemname = itemname;
		this.description = description;
		this.price = price;
		this.ratings = ratings;
		this.isavailable = isavailable;
		this.imagepath = imagepath;
	}

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public boolean getIsavailable() {
		return isavailable;
	}

	public void setIsavailable(boolean isavailable) {
		this.isavailable = isavailable;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	@Override
	public String toString() {
		return "Menu [menuid=" + menuid + ", restaurantid=" + restaurantid + ", itemname=" + itemname + ", description="
				+ description + ", price=" + price + ", ratings=" + ratings + ", isavailable=" + isavailable
				+ ", imagepath=" + imagepath + "]";
	}
	
	
	
}
