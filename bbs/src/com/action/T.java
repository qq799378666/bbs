package com.action;

import java.sql.Timestamp;

public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timestamp t = new Timestamp(System.currentTimeMillis()-1000);
		String str = "";
		long m = System.currentTimeMillis()-t.getTime();
		if(m<60)str=m+"秒之前";
		else if(m<60*60){
			str = m/60+"分钟之前";
		}
		else if(m<60*60*24){
			str = m/3600+"小时之前";
		}
		else{
			str = m/3600/24+"天之前";
		}
		System.out.println(str);
	}

}
