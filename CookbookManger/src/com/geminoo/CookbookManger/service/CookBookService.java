/**
 * 
 */
package com.geminoo.CookbookManger.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.geminoo.CookbookManger.entity.CartItems;
import com.geminoo.CookbookManger.entity.Cookbook;
import com.geminoo.CookbookManger.entity.Order;
import com.geminoo.CookbookManger.util.DataUtil;
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
	// 购物车集合
	CartItems cartItem = new CartItems();
	Set<CartItems> cartItems = new HashSet<>();
	// 订单集合
	List<Order> orders = new ArrayList<Order>();
	// 订单文件
	File ordersFile = new File("orders.txt");

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

		switch (type) {
		case "1":
			findCookById();
			break;
		case "2":
			findCookByName();
			break;
		case "3":
			findCookByPrice();
			break;
		case "4":
			systemExit();
			break;
		}
	}

	public void findCookById() {
		System.out.println("请输入要查找的编号：");
		int id = sc.nextInt();
		cb = fs.searchByType(id);
		System.out.println("\t编号\t\t 菜名\t\t价格");
		System.out.println("\t" + cb.getId() + "\t\t" + cb.getName() + "\t\t" + cb.getPrice());

	}

	public void findCookByName() {
		System.out.println("请输入要查找的菜名：");
		String name = sc.next();
		cb = fs.searchByType(name);
		System.out.println("\t编号\t\t 菜名\t\t价格");
		System.out.println("\t" + cb.getId() + "\t\t" + cb.getName() + "\t\t" + cb.getPrice());

	}

	public void findCookByPrice() {
		System.out.println("请输入要查找的价格：");
		double price = sc.nextDouble();
		cb = fs.searchByType(price);
		System.out.println("\t编号\t\t 菜名\t\t价格");
		System.out.println("\t" + cb.getId() + "\t\t" + cb.getName() + "\t\t" + cb.getPrice());
	}

	public void addCart() {
		System.out.println("请点餐：（编号）：");
		int code = sc.nextInt();
		cb = fs.searchByType(code);

		System.out.println("请输入要购买份数：");
		int buyCount = sc.nextInt();

		// 封装购物车对象
		cartItem.setCookBook(cb);
		cartItem.setBuyCount(buyCount);
		// 如果第一次添加
		if (cartItems.isEmpty()) {
			cartItems.add(cartItem);
		} else {
			// 购物车中已有商品,判断是否重复
			for (CartItems ci : cartItems) {
				// 重复购买,更改数量
				if (ci.getCookBook().equals(cartItem.getCookBook())) {
					ci.setBuyCount(ci.getBuyCount() + buyCount);
				}
			}
			// 未重复
			cartItems.add(cartItem);
		}
	}

	public void showCart() {
		double allPrice = 0;
		if (cartItems.isEmpty()) {
			System.out.println("啥都没有，快去点菜吧。。。");
		}
		System.out.println("已点菜品：");
		System.out.println("编号\t\t 菜名\t\t价格");
		for (CartItems ci : cartItems) {
			System.out.println(ci.getCookBook().getId() + "\t\t" + ci.getCookBook().getName() + "\t\t"
					+ ci.getCookBook().getPrice());
			allPrice = ci.getBuyCount() * ci.getCookBook().getPrice();
		}
		cartItem.setAllPrice(allPrice);
		System.out.println("总计" + allPrice + "元");

		/*
		 * System.out.println("是否确定订单？y/n"); String flag = sc.next(); if
		 * (flag.equals("y")) { createOrder(); }
		 */
	}

	public void createOrder() {
		Order o = new Order();
		// 封装订单详情
		o.setOrderNum(System.currentTimeMillis() + "~");
		o.setOrderTime(DataUtil.formatDate(new Date()));
		// o.setAllPrice(cartItem.getAllPrice());
		o.setOrderItems(cartItems);

		// 给所有商品添加订单信息
		for (CartItems ci : cartItems) {
			ci.setOrder(o);
		}
		// 订单集合
		orders.add(o);

		
		// 将整合的订单信息存入
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(ordersFile));
			for (Order oo : orders) {
				oos.writeObject(oo);
			}
			oos.writeObject(null);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (!oos.equals(null)) {
				try {
					oos.flush();
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("下单成功~！");
		// 清空购物车
		cartItems.removeAll(cartItems);

	}

	public void showOrder() {
		Order o = null;
		if (!ordersFile.exists()) {
			System.out.println("没有订单。。。。");
		} else {
			// 读取订单文件
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream(ordersFile));
				o = (Order) ois.readObject();
				while (o != null) {
					orders.add(o);
					o = (Order) ois.readObject();
				}
				ois.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Order oo : orders) {
				System.out.println("编号\t\t\t\t下单时间\t\t\t下单人");
				System.out.println(oo.getOrderNum() + "\t\t\t\t" + oo.getOrderTime() + "\t\t\t" + "帅气的老板");
				
				System.out.println("编号\t\t 菜名\t\t价格");
				double allprice = 0;
				for (CartItems ci : oo.getOrderItems()) {
					System.out.println(ci.getCookBook().getId() + "\t\t" + ci.getCookBook().getName() + "\t\t"
							+ ci.getCookBook().getPrice());
					allprice = ci.getAllPrice();
				}
				System.out.println("总计" + allprice + "元");
				System.out.println("--------------------------------------");
			}
		}
	}

	public void systemExit() {
		System.out.println("欢迎下次光临~");
		System.exit(0);
	}

	public List<Cookbook> getCbooks() {
		return cbooks;
	}

}
