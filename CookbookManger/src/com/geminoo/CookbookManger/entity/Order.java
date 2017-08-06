/**
 * 
 */
package com.geminoo.CookbookManger.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author chen
 *
 */
public class Order implements Serializable{
	private String orderNum;
	private String OrderTime;
	private Set<CartItems> orderItems;
	private double allPrice;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderTime() {
		return OrderTime;
	}

	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}

	public Set<CartItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<CartItems> orderItems) {
		this.orderItems = orderItems;
	}

	public double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}

}
