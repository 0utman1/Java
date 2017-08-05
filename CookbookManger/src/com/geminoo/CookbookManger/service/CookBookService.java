/**
 * 
 */
package com.geminoo.CookbookManger.service;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.geminoo.CookbookManger.entity.CartItems;
import com.geminoo.CookbookManger.entity.Cookbook;
import com.geminoo.CookbookManger.util.GenerateCode;

/**
 * @author chen
 *
 */
public class CookBookService {
	FileOperateService fs = new FileOperateService();;
	List<Cookbook> cbooks = fs.getAllCookbooks();
	Scanner sc = new Scanner(System.in);
	Cookbook cb;

	CartItems cartItems = new CartItems();
	Set<CartItems> sets;

	public void showAllCookboos() {

		if (!cbooks.isEmpty()) {
			System.out.println("编号\t\t 菜名\t\t价格");
			for (Cookbook b : cbooks) {
				System.out.println(b.getId() + "\t\t" + b.getName() + "\t\t" + b.getPrice());
			}
		} else {
			System.out.println("卖完啦！啥都没有了。。");
		}
	}

	public void addCookbook() {
		System.out.println("请输入菜品名字：");
		String name = sc.next();
		System.out.println("请输入价格:");
		double price = sc.nextDouble();
		Cookbook cb = new Cookbook(GenerateCode.getInstance().getId(), name, price);
		fs.insert(cb);
		System.out.println("新增成功！！");

	}

	public void editCookbook() {
		showAllCookboos();
		System.out.println("请输入要修改的菜品编号：");
		int newId = sc.nextInt();
		System.out.println("请输入新菜名：");
		String newName = sc.next();
		System.out.println("请输入新菜价：");
		double newPrice = sc.nextDouble();
		fs.updata(new Cookbook(newId, newName, newPrice));

	}

	public void removeCookbook() {
		showAllCookboos();
		System.out.println("请输入要删除的菜品编号：");
		int id = sc.nextInt();
		fs.delete(fs.search(id));

	}

	public void searchByType(String type) {
		if (type == "1") {
			findCookById();
		}
		if (type == "2") {
			findCookByName();
		}
		if (type == "3") {
			findCookByPrice();
		}
		if (type == "4") {
			System.out.println("退回到上层菜单。。");
			return;
		}
		System.out.println("\t编号\t\t 菜名\t\t价格");
		System.out.println("\t" + cb.getId() + "\t\t" + cb.getName() + "\t\t" + cb.getPrice());
	}

	public void findCookById() {
		System.out.println("请输入要查找的编号：");
		int id = sc.nextInt();
		cb = fs.searchByType(id);
	}

	public void findCookByName() {
		System.out.println("请输入要查找的菜名：");
		String name = sc.next();
		cb = fs.searchByType(name);
	}

	public void findCookByPrice() {
		System.out.println("请输入要查找的价格：");
		double price = sc.nextDouble();
		cb = fs.searchByType(price);
	}

	public void addCart() {
		System.out.println("请点餐：（编号）：");
		int code = sc.nextInt();
		cb = fs.searchByType(code);

		System.out.println("请输入要购买份数：");
		int buyCount = sc.nextInt();

		// 封装购物车对象
		cartItems.setCookBook(cb);
		cartItems.setBuyCount(buyCount);
		// 如果第一次添加
		if (sets.isEmpty()) {
			sets.add(cartItems);
		} else {
			// 购物车中已有商品,判断是否重复
			for (CartItems ci : sets) {
				// 重复购买,更改数量
				if (ci.getCookBook().equals(cartItems.getCookBook())) {
					ci.setBuyCount(ci.getBuyCount() + buyCount);
				}
			}
			// 未重复
			sets.add(cartItems);
		}
	}

	public void showCart() {
		double allPrice = 0;
		if (sets.isEmpty()) {
			System.out.println("啥都没有，快去点菜吧。。。");
		}
		System.out.println("已点菜品：");
		System.out.println("编号\t\t 菜名\t\t价格");
		for (CartItems ci : sets) {
			System.out.println(ci.getCookBook().getId() + "\t\t" + ci.getCookBook().getName() + "\t\t"
					+ ci.getCookBook().getPrice());
			allPrice = ci.getBuyCount() * ci.getCookBook().getPrice();
		}
		cartItems.setAllPrice(allPrice);
		System.out.println("总计" + allPrice + "元");
	}

	public void createOrder(){
		
	}
	
	public void showOrder(){
		
	}
	
	public void systemExit() {
		System.out.println("欢迎下次光临!!!");
		System.exit(0);
	}

	public List<Cookbook> getCbooks() {
		return cbooks;
	}

}
