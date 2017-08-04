/**
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author chen
 *
 */
public class PersonManager {
	static PersonDao pd = new PersonDao();
	static Person person = new Person();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		mainMenu();
	}

	static void mainMenu() {
		while (true) {

			System.out.println("============欢迎使用手机通讯录============");
			System.out.println("1、查看全部");
			System.out.println("2、新增人员");
			System.out.println("3、修改信息");
			System.out.println("4、删除人员");
			System.out.println("5、查找人员");
			System.out.println("6、退出");
			System.out.println("=====================================");
			System.out.println("请输入序号选择[1-6]");
			String option = sc.nextLine();
			switch (option) {
			case "1":
				showAll();
				break;
			case "2":
				add();
				break;
			case "3":
				edit();
				break;
			case "4":
				delete();
				break;
			case "5":
				find();
				break;
			case "6":
				System.out.println("已退出。。");
				System.exit(0);
				break;

			default:
				break;
			}

		}

	}

	static void showAll() {
		pd.getAll();
		System.out.println("按任意键继续。。");
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
			mainMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void add() {

		System.out.println("增加通讯录。。。");
		System.out.println("请输入姓名：");
		person.setName(sc.nextLine());
		System.out.println("请输入手机号码:");
		person.setPhone(sc.nextLine());
		System.out.println("请输入QQ号码：");
		person.setQq(sc.nextLine());
		System.out.println("请输入地址：");
		person.setAddress(sc.nextLine());

		pd.add(person);

		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
			mainMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void edit() {
		System.out.println("请输入要更改的手机号：");
		String ph = sc.nextLine();

		System.out.println("该手机号对应的信息是：");
		System.out.println("姓名:" + pd.find(ph).getName());
		System.out.println("QQ号:" + pd.find(ph).getQq());
		System.out.println("地址：" + pd.find(ph).getAddress());

		Person newPerson = new Person();
		System.out.println("请输入新姓名：");
		newPerson.setName(sc.nextLine());
		System.out.println("请输入新手机号码:");
		newPerson.setPhone(sc.nextLine());
		System.out.println("请输入新QQ号码：");
		newPerson.setQq(sc.nextLine());
		System.out.println("请输入新地址：");
		newPerson.setAddress(sc.nextLine());

		pd.edit(ph, newPerson);
		
	}

	static void delete() {
		System.out.println("请输入要删除的手机号：");
		String ph = sc.nextLine();

		System.out.println("该手机号对应的信息是：");
		System.out.println("姓名:" + pd.find(ph).getName());
		System.out.println("QQ号:" + pd.find(ph).getQq());
		System.out.println("地址：" + pd.find(ph).getAddress());

		System.out.println("确认删除吗？y/n");
		String confirm = sc.nextLine();
		if (confirm.equals("y")) {
			pd.delete(ph);
			System.out.println("手机号码" + ph + "的用户已被删除！！");
		} else {
			System.out.println("输入有误，请重试！！(任意键继续)");

			try {
				new BufferedReader(new InputStreamReader(System.in)).readLine();
				delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static void find() {
		System.out.println("请输入要查找的手机号：");
		String ph = sc.nextLine();
		
		System.out.println("该手机号对应的信息是：");
		System.out.println("姓名:" + pd.find(ph).getName());
		System.out.println("QQ号:" + pd.find(ph).getQq());
		System.out.println("地址：" + pd.find(ph).getAddress());

		System.out.println("任意键继续。。");
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
			mainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
