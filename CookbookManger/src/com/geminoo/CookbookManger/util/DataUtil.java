/**
 * 
 */
package com.geminoo.CookbookManger.util;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author chen
 *
 */
public class DataUtil {

	private static DataUtil dataUtil;

	private DataUtil() {
	}

	public static synchronized DataUtil getInstance() {

		if (dataUtil == null) {
			dataUtil = new DataUtil();
		}
		return dataUtil;
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}
}
