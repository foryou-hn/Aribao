package com.example.pc.myapplication.utils;

import java.util.Collection;

/**
 * 基本工具类
 **/
public class BeanUtils {

	/**
	 * 判断数据是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		boolean flag = true;
		if (obj != null) {
			if (obj instanceof String) {
				flag = (obj.toString().trim().length() == 0);
			} else if (obj instanceof Collection<?>) {
				flag = ((Collection) obj).size() == 0;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 方法说明：<br>
	 * 判断字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyStr(String str) {
        return str == null || "null".equals(str.toLowerCase()) || str.trim().length() == 0;
    }

}
