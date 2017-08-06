/**
 * 
 */
package com.geminoo.CookbookManger.mian;


import com.geminoo.CookbookManger.entity.Menu;
import com.geminoo.CookbookManger.service.CookBookService;

/**
 * @author chen
 *
 */
public class CookBookManger {
	public static void main(String[] args) {
		Menu m = new Menu();
		CookBookService cs = new CookBookService();
		while (true) {
			// 显示菜单
			m.showMenu();
			// 获取操作选项
			String operator = m.getOperator();
			// System.out.println(operator);
			switch (operator) {
			case "1":
				cs.addCookbook();
				break;
			case "2":
				cs.removeCookbook();
				break;
			case "3":
				cs.editCookbook();
				break;
			case "4":
				m.SubMenu();
				break;
			case "5":
				cs.showAllCookboos();
				break;
			case "6":
				cs.addCart();
				break;
			case "7":
				cs.showCart();
				break;
			case "8":
				cs.createOrder();
				break;
			case "9":
				cs.showOrder();
				break;
			case "10":
				cs.systemExit();
				break;
			default:
				System.out.println("输入错误，请重新输入！！");
				break;
			}
		}
	}
}
