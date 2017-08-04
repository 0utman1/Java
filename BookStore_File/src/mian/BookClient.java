/**
 * 
 */
package mian;

import java.io.FileNotFoundException;
import java.io.IOException;

import service.BookService;

/**
 * @author chen
 *
 */
public class BookClient {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		BookService bs = new BookService();

		while (true) {
			// 显示菜单
			bs.showMenu();
			// 获取操作选项
			String operator = bs.getOperator();

			// System.out.println(operator);
			switch (operator) {
			case "1":
				bs.showAllBook();
				break;
			case "2":
				bs.addBook();
				break;
			case "3":
				bs.removeBook();
				break;
			case "4":
				bs.showAllBook();
				bs.addCart();
				break;
			case "5":
				bs.showCart();
				break;
			case "6":
				bs.showCart();
				bs.createOrder();
				break;
			case "7":
				bs.showOrder();
				break;
			case "8":
				bs.systemExit();
				
				break;
			default:
				System.out.println("输入错误，请重新输入！！");
				break;
			}

		}

	}
}
