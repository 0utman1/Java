
package util;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import dao.BookDaoIpml;
import entity.Book;

/**
 * @author chen 懒汉式单例模式 在程序运行期间，只会创建一次，只有一个对象
 * 
 */
public class GenerateCode {
	private static GenerateCode generateCode;

	private GenerateCode() {
		write(5);
	}

	public static synchronized GenerateCode getInstance() {

		if (generateCode == null) {
			generateCode = new GenerateCode();
		}
		return generateCode;
	}

	public static int getCode() {

		int count = 1;
		BookDaoIpml bd = new BookDaoIpml();
		List<Book> list = bd.selectAllBook();
		if (list.size() == 0) {
			return count++;
		} else {
			count = list.get(list.size() - 1).getBookCode() + 1;
		}
		return count++;
	}

	public void read() {
		DataInputStream dis = null;
		int num;
		try {
			dis = new DataInputStream(new FileInputStream("id,dat"));
			num = dis.readInt();
			write(num++);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public int write(int num) {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream("id.dat"));
			dos.writeInt(num);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return num;

	}

}
