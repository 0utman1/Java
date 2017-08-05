/**
 * 
 */
package com.geminoo.CookbookManger.entity;

import java.util.Scanner;

/**
 * @author chen
 *
 */
public class Menu {

	public void showMenu() {
		System.out.println("=============菜谱管理系统=============");
		System.out.println("1.增加");
		System.out.println("2.删除");
		System.out.println("3.修改");
		System.out.println("4.查询");
		System.out.println("5.浏览");
		System.out.println("6.加入购物车");
		System.out.println("7.查看购物车，并下订单");
		System.out.println("8.显示订单");
		System.out.println("9.退出");
		System.out.println("=====================================");
		System.out.println("请选择：");
	}

	public void SubMenu() {
		System.out.println("\t〖查询菜谱〗\n"
				+ "\t[1]按编号查询\n"
				+ "\t[2]按菜名查询\n"
				+ "\t[3]按价格查询\n"
				+ "\t[4]退出\n"
				+ "\t请选择查询的操作编号：");
	}

	public String getOperator() {
		Scanner sc = new Scanner(System.in);
		String operator = sc.next();
		return operator;
	}	
}
