package com.util;

import java.sql.Timestamp;

public class ViewUtil {
	public static String test(Timestamp t){
		String str = "";
		long m = System.currentTimeMillis()-t.getTime();
		m /= 1000;
		if(m<60)str=m+"秒前";
		else if(m<60*60){
			str = m/60+"分钟前";
		}
		else if(m<60*60*24){
			str = m/3600+"小时前";
		}
		else{
			str = m/3600/24+"天前";
		}
		
		return str;
	}
}
