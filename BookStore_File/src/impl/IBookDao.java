/**
 * 
 */
package impl;

import java.util.List;

import entity.Book;

/**
 * @author chen
 *
 */
public interface IBookDao {
	public List<Book> selectAllBook();
	public void insertBook(Book book);
	public void deleteBook(Book book);
	public Book selcetBookByCode(int code);

}
