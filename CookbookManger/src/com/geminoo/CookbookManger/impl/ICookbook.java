
package com.geminoo.CookbookManger.impl;

import java.util.List;

import com.geminoo.CookbookManger.entity.Cookbook;

/**
 * @author chen
 *
 */
public interface ICookbook {
	void insert(Cookbook cookbook);

	void delete(Cookbook cookbook);

	void updata(Cookbook cookbook);

	Cookbook search(int sid);

	List<Cookbook> getAllCookbooks();


}
