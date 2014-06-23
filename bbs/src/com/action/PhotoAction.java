package com.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class PhotoAction extends ActionSupport implements SessionAware {
	private Map<String,Object> session;
	public InputStream inputStream;
	
	public String yzm() throws IOException {
		int weight = 80;
		int height = 38;

		BufferedImage image = new BufferedImage(weight, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, weight, height);
		g.setColor(Color.WHITE);

		// 绘制噪点
		Random rand = new Random();
		// 设置噪点颜色
		g.setColor(Color.GREEN);
		for (int i = 0; i < 4 * 6; i++) {
			int x = rand.nextInt(weight);
			int y = rand.nextInt(height);
			// 绘制1*1大小的矩形
			g.drawRect(x, y, 1, 1);
		}

		String yzm = "";
		Random rd = new Random();
		for (int i = 0; i < 4; i++) {
			yzm += (char) (rd.nextInt(26) + 97);
		}
		session.put("yzm", yzm);
		System.out.println("验证码为:"+yzm);
		
		// 绘制验证码
		// 设置字体颜色和样式
		g.setColor(Color.WHITE);
		int fontSize = 20;
		g.setFont(new Font("", Font.BOLD, fontSize));
		//g.drawString(yzm, 5, height/2);
	
		for(int i=0;i<yzm.length();i++){
			int w = i*(weight/yzm.length());
			int h = height/2+fontSize/2+rand.nextInt(10)-5;
			g.drawString(yzm.toCharArray()[i]+"", w,h);
			
		}
		
		
		
		g.dispose();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", outputStream);

		ByteArrayInputStream input = new ByteArrayInputStream(
				outputStream.toByteArray());
		this.inputStream = input;
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

}
