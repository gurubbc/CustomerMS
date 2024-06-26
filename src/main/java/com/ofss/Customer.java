package com.ofss;

public class Customer {

	private int customerId;
	private String customerName;
	private Address address;
	private int stockIds[];
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int customerId, String customerName, Address address, int[] stockIds) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.stockIds = stockIds;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int[] getStockIds() {
		return stockIds;
	}
	public void setStockIds(int[] stockIds) {
		this.stockIds = stockIds;
	}
	
	
}
