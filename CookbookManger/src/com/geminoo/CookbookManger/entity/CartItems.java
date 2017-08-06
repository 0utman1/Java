/**
 * 
 */
package com.geminoo.CookbookManger.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * @author chen
 *
 */
public class CartItems implements Serializable {
	private Cookbook cookBook;
	private int buyCount;
	private double allPrice;
	private Order order;
	public Cookbook getCookBook() {
		return cookBook;
	}

	public void setCookBook(Cookbook cookBook) {
		this.cookBook = cookBook;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public double getAllPrice() {
		return allPrice = cookBook.getPrice() * buyCount;
	}

	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
