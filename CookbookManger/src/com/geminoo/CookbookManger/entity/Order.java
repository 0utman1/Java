/**
 * 
 */
package com.geminoo.CookbookManger.entity;

import java.util.Date;
import java.util.List;

/**
 * @author chen
 *
 */
public class Order {
	private Date OrderTime;
	private List<CartItems> cartItems;
	private String address;
	
	public long getOrderTime() {
		return new Date().getTime();
	}
	public void setOrderTime(Date orderTime) {
		OrderTime = orderTime;
	}
	public List<CartItems> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
