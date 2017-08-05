/**
 * 
 */
package com.geminoo.CookbookManger.entity;

import java.util.Set;

/**
 * @author chen
 *
 */
public class CartItems {
	private Cookbook cookBook;
	private int buyCount;
	private double allPrice;

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
	
}
