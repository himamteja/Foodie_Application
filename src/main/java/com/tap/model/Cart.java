package com.tap.model;

public class Cart {
	
	private int itemid;
	private String itemname;
	private int price;
	private int quantity;
	private String imagepath;
	
	public Cart() {
		
	}
	
	public Cart(int itemid, String itemname, int price, int quantity, String imagepath) {
		super();
		this.itemid = itemid;
		this.itemname = itemname;
		this.price = price;
		this.quantity = quantity;
		this.imagepath = imagepath;
	}

	public int getItemid() {
		return itemid;
	}


	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public double getTotalPrice() {
	    return price * quantity;
	}

	@Override
	public String toString() {
		return "Cart [itemid=" + itemid + ", itemname=" + itemname + ", price=" + price + ", quantity=" + quantity
				+ ", imagepath=" + imagepath + "]";
	}
	

}
