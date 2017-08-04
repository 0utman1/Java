
package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import util.DataUtil;
import util.GenerateCode;
import dao.BookDaoIpml;
import entity.Book;
import entity.CartItems;
import entity.Order;

/**
 * @author chen
 *
 */
public class BookService {

	BookDaoIpml bd = new BookDaoIpml();
	Scanner sc = new Scanner(System.in);

	Map<Integer, CartItems> cartItems = new HashMap<Integer, CartItems>();

	List<Order> orders = new ArrayList<>();
	File orderFile = new File("orders.dat");

	public void showMenu() {
		System.out.println("=====================================");

		System.out.println("1.查看所有图书");
		System.out.println("2.添加新的图书");
		System.out.println("3.删除某一个图书");
		System.out.println("4.添加购物车");
		System.out.println("5.查看购物车");
		System.out.println("6.查看购物车，并下订单");
		System.out.println("7.显示订单");
		System.out.println("8.退出");

		System.out.println("=====================================");

	}

	public String getOperator() {
		System.out.println("请选择：");
		String operator = sc.next();
		return operator;
	}

	public void addBook() {
		System.out.println("请输入图书名字：");
		String name = sc.next();
		System.out.println("请输入作者：");
		String author = sc.next();
		System.out.println("请输入价格:");
		double price = sc.nextDouble();
		System.out.println("请输入库存:");
		int storeCount = sc.nextInt();

		Book b = new Book(GenerateCode.getInstance().getCode(), name, author, price, storeCount);
		bd.insertBook(b);
		System.out.println("新增成功！！");
	}

	public void showAllBook() {

		List<Book> books = bd.selectAllBook();

		if (!books.isEmpty()) {
			System.out.println("编号\t\t 书名\t\t作者\t\t价格\t\t库存");
			for (Book b : books) {
				System.out.println(b.getBookCode() + "\t\t" + b.getBookName() + "\t\t" + b.getAuthor() + "\t\t"
						+ b.getPrice() + "\t\t" + b.getStoreCount());

			}
		} else {
			System.out.println("没有库存了。。");
		}
	}

	public void removeBook() {
		System.out.println("请输入要删除的书籍编号：");
		int code = sc.nextInt();
		bd.deleteBook(bd.selcetBookByCode(code));

	}

	public int addCart() throws FileNotFoundException, IOException {
		CartItems ci = new CartItems();
		Book b;

		System.out.println("请输入要购买商品的编号：");
		int buyCode = sc.nextInt();
		b = bd.selcetBookByCode(buyCode);

		// 验证库存
		if (buyCode > b.getStoreCount()) {
			System.out.println("库存不足，还剩" + b.getStoreCount() + "本！");
			return -1;
		}

		System.out.println("请输入要购买商品的数量：");
		int buyCount = sc.nextInt();

		// 封装商品明细对象
		ci.setBook(b);
		ci.setBuyCount(buyCount);

		// 添加商品信息到购物车
		boolean flag = true;
		if (cartItems.isEmpty()) {
			// 购物车为空
			cartItems.put(b.getBookCode(), ci);
		} else {
			// 购物车不为空，遍历添加
			for (Map.Entry<Integer, CartItems> me : cartItems.entrySet()) {
				// 商品重复添加
				if (ci.getBook().equals(me.getValue().getBook())) {
					// 更改购买数量
					me.getValue().setBuyCount(ci.getBuyCount() + me.getValue().getBuyCount());
					flag = false;
					break;
				}
			}
		}
		// 未重复
		if (flag) {
			cartItems.put(b.getBookCode(), ci);
		}

		// 减库存
		List<Book> books = bd.selectAllBook();
		for (Book bb : books) {
			bb.setStoreCount(b.getStoreCount() - buyCount);
		}

		return 1;
	}

	public void showCart() {

		if (cartItems.isEmpty()) {
			System.out.println("购物车为空。。。");
		} else {
			System.out.println("购物车商品如下：");
			System.out.println("编号\t\t书名\t\t价格\t\t数量\t\t小计");
			Book b;
			double allPrice = 0;
			for (Map.Entry<Integer, CartItems> entry : cartItems.entrySet()) {
				b = entry.getValue().getBook();
				System.out.println(b.getBookCode() + "\t\t" + b.getBookName() + "\t\t" + b.getPrice() + "\t\t"
						+ entry.getValue().getBuyCount() + "\t\t" + (entry.getValue().getBuyCount() * b.getPrice())
						+ "\t\t");
				allPrice += entry.getValue().getBuyCount() * b.getPrice();

			}

			System.out.println("总计" + allPrice + "元");
			// System.out.println("是否下订单? y/s");
			// String placeOrder = sc.nextLine();
		}

	}

	public void createOrder() throws FileNotFoundException, IOException, ClassNotFoundException {
		// 创建订单对象
		Order o = new Order();
		System.out.println("请输入送货地址：");
		String address = sc.next();
		o.setAdress(address);
		o.setOderDate(new Date());
		o.setOderNum("ooo~" + System.currentTimeMillis());
		o.setUserName("admin");

		// 获取购物车商品详情
		Set<CartItems> sets = new HashSet<>();
		for (CartItems ci : cartItems.values()) {
			sets.add(ci);
		}
		o.setOrderitems(sets);

		// 给所有商品添加订单信息
		for (Map.Entry<Integer, CartItems> me : cartItems.entrySet()) {
			me.getValue().setOrder(o);
		}
		// 订单集合
		orders.add(o);

		// 保存订单信息

		/*
		 * if (!orderFile.exists()) { orderFile.createNewFile(); } else { //
		 * 先获取文件中的内容 ObjectInputStream ois = new ObjectInputStream(new
		 * FileInputStream(orderFile)); List<Order> list = new ArrayList<>(); o
		 * = (Order) ois.readObject(); while (o != null) { list.add(o); o =
		 * (Order) ois.readObject(); } ois.close();
		 */

		// 将整合的订单信息存入
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(orderFile));
		for (Order oo : orders) {
			oos.writeObject(oo);
		}
		oos.writeObject(null);
		oos.flush();
		oos.close();

		// 清空购物车
		cartItems.clear();

	}

	public void showOrder() throws FileNotFoundException, IOException, ClassNotFoundException {

		if (!orderFile.exists()) {
			System.out.println("没有订单。。。。");
		} else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(orderFile));
			List<Order> list = new ArrayList<>();
			Order o = (Order) ois.readObject();
			while (o != null) {
				list.add(o);
				o = (Order) ois.readObject();
			}
			ois.close();

			for (Order oo : list) {
				System.out.println("编号\t\t\t下单时间\t\t\t下单人");
				System.out.println(oo.getOderNum() + "\t\t\t" + DataUtil.formatDate(oo.getOderDate()) + "\t\t\t"
						+ oo.getUserName());

				System.out.println("\t编号\t\t书名\t\t单价\t\t购买数量\t\t总价");
				for (CartItems oi : oo.getOrderitems()) {
					Book b = oi.getBook();
					System.out.println("\t" + b.getBookCode() + "\t\t" + b.getBookName() + "\t\t" + b.getPrice()
							+ "\t\t" + oi.getBuyCount() + "\t\t" + b.getPrice() * oi.getBuyCount());
				}

				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------------");
			}

			/*
			 * for (Order oo : this.orders) {
			 * System.out.println("编号\t\t\t下单时间\t\t\t下单人"); System.out.println(
			 * o.getOderNum() + "\t\t\t" + DataUtil.formatDate(o.getOderDate())
			 * + "\t\t\t" + o.getUserName());
			 * 
			 * System.out.println("\t编号\t\t书名\t\t单价\t\t购买数量\t\t总价"); for
			 * (CartItems oi : o.getOrderitems()) { Book b = oi.getBook();
			 * System.out.println("\t" + b.getBookCode() + "\t\t" +
			 * b.getBookName() + "\t\t" + b.getPrice() + "\t\t" +
			 * oi.getBuyCount() + "\t\t" + b.getPrice() * oi.getBuyCount()); }
			 * 
			 * System.out.println(
			 * "------------------------------------------------------------------------------------------------------------------------------------------"
			 * ); }
			 */
		}

	}

	public void systemExit() {
		System.out.println("欢迎下次光临!!!");
		System.exit(0);
	}

}
