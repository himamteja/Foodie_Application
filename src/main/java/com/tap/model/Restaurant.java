package com.tap.model;

public class Restaurant {
	private int restaurantid;
	private String name;
	private String address;
	private int phone;
	private float rating;
	private String cusinetype;
	private boolean isactive;
	private String eta;

	private int adminuserid;
	private String imagepath;
	
	Restaurant() {
		
	}

	public Restaurant(int restaurantid, String name, String address, int phone, float rating, String cusinetype,
			boolean isactive, String eta, int adminuserid, String imagepath) {
		super();
		this.restaurantid = restaurantid;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cusinetype = cusinetype;
		this.isactive = isactive;
		this.eta = eta;
		this.adminuserid = adminuserid;
		this.imagepath = imagepath;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getCusinetype() {
		return cusinetype;
	}

	public void setCusinetype(String cusinetype) {
		this.cusinetype = cusinetype;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public int getAdminuserid() {
		return adminuserid;
	}

	public void setAdminuserid(int adminuserid) {
		this.adminuserid = adminuserid;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
	@Override
	public String toString() {
		return "Restaurant [restaurantid=" + restaurantid + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", rating=" + rating + ", cusinetype=" + cusinetype + ", isactive=" + isactive + ", eta="
				+ eta + ", adminuserid=" + adminuserid + ", imagepath=" + imagepath + "]";
	}
	
	
}
