/**
 * 
 */
package entity;

import java.io.Serializable;

/**
 * @author chen
 *
 */
public class CartItems implements Serializable{
	

	//private static final long serialVersionUID = -7930580275531493610L;
	private Book book;
	private int buyCount;
	private Order order;

	public CartItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItems(Book book, int buyCount) {
		super();
		this.book = book;
		this.buyCount = buyCount;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
