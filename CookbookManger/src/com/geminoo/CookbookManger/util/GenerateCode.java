/**
 * 
 */
package com.geminoo.CookbookManger.util;

import java.util.List;

import com.geminoo.CookbookManger.entity.Cookbook;
import com.geminoo.CookbookManger.service.CookBookService;
import com.geminoo.CookbookManger.service.FileOperateService;

/**
 * @author chen
 *
 */
public class GenerateCode {
	private int count = 1;

	private static GenerateCode generateCode;

	public static synchronized GenerateCode getInstance() {

		if (generateCode == null) {
			generateCode = new GenerateCode();
		}
		return generateCode;
	}

	public int getId() {

		FileOperateService fs = new FileOperateService();
		List<Cookbook> list = fs.getAllCookbooks();

		if (list.size() == 0) {
			return count++;
		} else {
			count = list.get(list.size() - 1).getId() + 1;
		}

		return count++;
	}

}
