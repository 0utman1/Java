/**
 * 
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import impl.IBookDao;

/**
 * @author chen
 *
 */
public class BookDaoIpml implements IBookDao {
	List<Book> books;

	File bookStore = new File("book1Store.dat");

	public BookDaoIpml() {
		books = new ArrayList<>();
		// 检查数据文件是否已存在
		if (!bookStore.exists()) {
			try {
				bookStore.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			books.add(new Book(1, "西游记", "吴承恩", 23, 100));
			books.add(new Book(2, "红楼梦", "曹雪芹", 43, 10));
			books.add(new Book(3, "三国演义", "罗贯中", 33, 2));
			books.add(new Book(4, "水浒传", "施耐庵", 49, 10));

			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(bookStore));

				for (Book b : books) {
					oos.writeObject(b);
				}
				oos.writeObject(null);
				oos.flush();
				oos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 数据文件已存在
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream(bookStore));
				books = new ArrayList<>();
				Book b;
				try {
					b = (Book) ois.readObject();
					while (b != null) {
						books.add(b);
						b = (Book) ois.readObject();
						// System.out.println(b);
					}
					ois.close();
				} catch (ClassNotFoundException e) {

				}

			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				System.out.println("啥都没有~");

			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see impl.IBookDao#insertBook(entity.Book)
	 */
	@Override
	public void insertBook(Book book) {
		books.add(book);
		try {
			writeBooks();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see impl.IBookDao#selectAllBook()
	 */
	@Override
	public List<Book> selectAllBook() {
		return books;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see impl.IBookDao#deleteBook(entity.Book)
	 */
	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		books.remove(book);
		try {
			writeBooks();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see impl.IBookDao#selcetBookByCode(int)
	 */
	@Override
	public Book selcetBookByCode(int code) {
		for (Book b : books) {
			if (b.getBookCode() == code) {
				return b;
			}
		}
		return null;
	}

	public void writeBooks() throws FileNotFoundException, IOException, ClassNotFoundException {
		// books = readBooks();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bookStore));
		for (Book b : books) {
			oos.writeObject(b);
		}
		oos.writeObject(null);
		oos.flush();
		oos.close();
	}

	public List<Book> readBooks() throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bookStore));
		// books = new ArrayList<>();
		Book b = (Book) ois.readObject();
		while (books != null) {
			books.add(b);
			b = (Book) ois.readObject();
			System.out.println(b);

			ois.close();
		}
		return books;

	}
}
