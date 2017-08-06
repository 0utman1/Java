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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.geminoo.CookbookManger.entity.Cookbook;
import com.geminoo.CookbookManger.impl.ICookbook;
import com.geminoo.CookbookManger.util.GenerateCode;

/**
 * @author chen
 *
 */
public class FileOperateService implements ICookbook {
	List<Cookbook> list;// 菜品集合
	File cookBook = new File("info.txt");// 菜品信息存储文件
	Cookbook cb;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public FileOperateService() {
		cb = new Cookbook();
		list = new ArrayList<Cookbook>();
		if (!cookBook.exists()) {
			try {
				cookBook.createNewFile();
				list.add(new Cookbook(1, "鱼香肉丝", 22.0));
				list.add(new Cookbook(2, "红油手抄", 23.0));
				list.add(new Cookbook(3, "鱼香肉丝", 24.0));
				write(cookBook);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		list = read(cookBook);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.geminoo.CookbookManger.impl.ICookbook#insert()
	 */
	@Override
	public void insert(Cookbook cookbook) {
		list.add(cookbook);
		write(cookBook);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.geminoo.CookbookManger.impl.ICookbook#delete
	 */
	@Override
	public void delete(Cookbook cookbook) {
		// TODO Auto-generated method stub
		list.remove(cookbook);
		write(cookBook);
		System.out.println("删除成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.geminoo.CookbookManger.impl.ICookbook#updata()
	 */
	@Override
	public void updata(Cookbook newCb) {
		list.get(newCb.getId()-1).setId(newCb.getId());
		list.get(newCb.getId()-1).setName(newCb.getName());
		list.get(newCb.getId()-1).setPrice(newCb.getPrice());
		write(cookBook);
		System.out.println("修改成功！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.geminoo.CookbookManger.impl.ICookbook#search()
	 */
	@Override
	public Cookbook search(int sid) {
		for (Cookbook c : list) {
			if (c.getId() == sid) {
				this.cb = c;
				return cb;
			}
		}
		System.out.println("没找着，是不是输入错了？");
		return null;
	}

	public Cookbook searchByType(int sid) {
		read(cookBook);
		return search(sid);
	}

	public Cookbook searchByType(String name) {
		read(cookBook);
		for (Cookbook c : list) {
			if (c.getName().equals(name)) {
				this.cb = c;
				return cb;
			}
		}
		System.out.println("没找着，是不是输入错了？");
		return null;
	}

	public Cookbook searchByType(double price) {
		read(cookBook);
		for (Cookbook c : list) {
			if (c.getPrice() == price) {
				this.cb = c;
				return cb;
			}
		}
		System.out.println("没找着，是不是输入错了？");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.geminoo.CookbookManger.impl.ICookbook#search()
	 */
	@Override
	public List<Cookbook> getAllCookbooks() {
		// System.out.println("====================");
		return list;
	}

	public List<Cookbook> read(File file) {
		list = new ArrayList<Cookbook>();
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			cb = (Cookbook) ois.readObject();
			while (cb != null) {
				list.add(cb);
				// System.out.println(cb);
				cb = (Cookbook) ois.readObject();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!ois.equals(null)) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public void write(File file) {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			for (Cookbook b : list) {
				oos.writeObject(b);
			}
			oos.writeObject(null);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}

}
