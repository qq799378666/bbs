package com.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyArrays {
	//按id排序
	public static <T> List<T> sort(List<T> list) {
		Comparator comparator = new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				try {
					Field[] fs = o1.getClass().getDeclaredFields();
					Field f = null;
					for(Field ff:fs){
						if(ff.getName().equals("id")){
							f = ff;
							break;
						}
					}
					if(f==null)throw new Exception("异常:排序的对象里面没有id属性");
					f.setAccessible(true);
					int n1 = (Integer)f.get(o1);
					int n2 = (Integer)f.get(o2);
					return n1-n2;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		};
	    Collections.sort(list,comparator);
	    return list;
	}
}
