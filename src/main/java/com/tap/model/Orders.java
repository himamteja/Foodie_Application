package com.tap.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Orders {
	private int orderid;
	private int userid;
	private int restaurantid;
	private Timestamp orderdate;
	private double totalamount;
	private String status;
	private String paymentmode;
	
	public Orders() {
		
	}

	public Orders(int orderid, int userid, int restaurantid, Timestamp orderdate, double totalamount, String status,
			String paymentmode) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.restaurantid = restaurantid;
		this.orderdate = orderdate;
		this.totalamount = totalamount;
		this.status = status;
		this.paymentmode = paymentmode;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	@Override
	public String toString() {
		return "Orders [orderid=" + orderid + ", userid=" + userid + ", restaurantid=" + restaurantid + ", orderdate="
				+ orderdate + ", totalamount=" + totalamount + ", status=" + status + ", paymentmode=" + paymentmode
				+ "]";
	}
	
	
}
