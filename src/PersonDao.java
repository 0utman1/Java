
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author chen
 *
 */
public class PersonDao {
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	private static Connection conn = null;

	public static Connection getCon() {
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void add(Person p) {
		conn = getCon();
		String sql = "insert into contacts (name,phone,qq,address) values(?,?,?,?)";
		PreparedStatement pstmt;
		int flag = 0;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getPhone());
			pstmt.setString(3, p.getQq());
			pstmt.setString(4, p.getAddress());
			flag = pstmt.executeUpdate();
			if (flag == 1) {
				System.out.println("增加成功。。\n按任意键继续。。");
				pstmt.close();
				conn.close();
			} else {
				System.out.println("增加失败。。\n按任意键继续。。");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过手机号查找
	 * 
	 * @param phoneS
	 * @return
	 */
	public Person find(String phone) {
		Person p = new Person();
		conn = getCon();
		String sql = "select * from contacts where phone = '" + phone + "'";
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(2);
				p.setName(name);
				String qq = rs.getString(4);
				p.setQq(qq);
				String address = rs.getString(5);
				p.setAddress(address);
			}
			// 返回person对象
			pstmt.close();
			conn.close();
			return p;

		} catch (SQLException e) {
			System.out.println("此手机号不存在,请重新输入!");
		}
		return null;

	}

	/**
	 * 通过手机号查找并编辑
	 * 
	 * @param phone
	 * @param p
	 */
	public void edit(String phone, Person p) {
		conn = getCon();
		String sql = "update contacts set name=?,phone=?,qq=?,address=? where phone ='" + phone+ "'";
		PreparedStatement pstmt;
		boolean flag ;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getPhone());
			pstmt.setString(3, p.getQq());
			pstmt.setString(4, p.getAddress());

			flag = pstmt.execute();
			
			if (flag == true) {
				try {
					System.out.println("修改成功。。\n按任意键继续。。");
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("修改失败...");
		}

	}

	/**
	 * 通过手机号查找并删除
	 * 
	 * @param phone
	 */
	public void delete(String phone) {
		conn = getCon();
		String sql = "delete from contacts where phone='" + phone + "'";
		PreparedStatement pstmt;
		int flag = 0;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			flag = pstmt.executeUpdate();
			if (flag == 1) {
				System.out.println("删除成功。。\n按任意键继续。。");
				pstmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("删除失败。。\n请重试。。");
			delete(phone);
			e.printStackTrace();
		}
	}

	/**
	 * 查找所有人信息
	 */
	public void getAll() {
		conn = getCon();
		String sql = "select * from contacts";
		PreparedStatement pstmt;// 预编译SQL，减少sql执行时间

		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// 获取结果
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			System.out.println("=====================================");
			System.out.println("编号\t" + "姓名\t" + "\t    手机号\t" + "\t  QQ号\t" + "\t地址\t");

			while (rs.next()) {
				for (int i = 1; i <= col; i++) {
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 4)) {
						System.out.print("\t");
					}
				}
				System.out.println("");
			}
			System.out.println("=====================================");
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
