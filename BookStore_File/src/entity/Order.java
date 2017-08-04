/**
 * 
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author chen
 *
 */
public class Order implements Serializable{
	private String oderNum;
	private	Date oderDate;
	private String userName;
	private String adress;
	private Set<CartItems> orderitems;
	
	public String getOderNum() {
		return oderNum;
	}
	public void setOderNum(String oderNum) {
		this.oderNum = oderNum;
	}
	public Date getOderDate() {
		return oderDate;
	}
	public void setOderDate(Date oderData) {
		this.oderDate = oderData;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Set<CartItems> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(Set<CartItems> orderitems) {
		this.orderitems = orderitems;
	}
}
