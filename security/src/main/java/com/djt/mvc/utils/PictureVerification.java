package com.djt.mvc.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class PictureVerification {

	// 图片的高度
	private int width = 120;
	// 图片宽度
	private int height = 40;
	// 字符个数
	private int codeCont = 4;
	// 字符大小
	private int fontSize = 25;
	// 干扰线条数
	private int lineCount = 5;
	// 干扰点个数
	private int pointCount = (int) (width * height * 0.02);
	// 随机数
	Random ran = new Random();
	// 随机字符资源
	private String code = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

	// sessio中的key
	private String sessionKey = "RandomCode";

	// 创建一个对象保存生成的随机字符串，方便后面对比验证码时进行取值
	private String checkVer = "";

	public void setCheckVer(String verCodes) {
		this.checkVer = verCodes;
	}

	public String getCheckVer() {
		return this.checkVer;
	}

	// 创建图片
	BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	// 获取画笔
	Graphics g = img.getGraphics();

	// 生成图片验证码
	public void createImage(HttpServletRequest request, HttpServletResponse response) {
		// 创建session
		HttpSession session = request.getSession();
		// 设置画笔颜色,设置为白色是为了将背景色设置为白色，方便后面生成的图片验证码的查看
		g.setColor(Color.WHITE);
		// 填充图片背景色
		g.fillRect(0, 0, width, height);
		// 创建一个对象保存每次生成的随机字符
		String verCode = "";
		// 创建一个对象保存图片验证码中的随机字符串
		String verCodes = "";
		// 在图片上画上生成的随机字符
		for (int i = 0; i < codeCont; i++) {
			// 设置画笔颜色
			g.setColor(getCorlor());
			// 设置字体
			g.setFont(new Font("黑体", Font.BOLD, fontSize));
			// 字符的位置
			int x = i * 20 + 30;
			int y = (int) (height * 0.5);
			verCode = getCode();
			verCodes += verCode;
			g.drawString(verCode, x, y);
		}
		// 将生成的随机字符串保存到session中
		session.setAttribute(sessionKey, verCodes);
		this.checkVer = verCodes;

		// 画干扰线
		drawline();
		// 画干扰点
		// drawPoint();

		// 输出图片验证码
		try {
			ImageIO.write(img, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取随机字符
	public String getCode() {
		String verCode = "" + code.charAt(ran.nextInt(code.length()));
		return verCode;
	}

	// 获取随机颜色
	public Color getCorlor() {
		Color color = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
		return color;
	}

	// 画干扰线
	public void drawline() {
		for (int i = 0; i < lineCount; i++) {
			// 设置画笔颜色
			g.setColor(getCorlor());
			// 设置干扰线的起始和结束位置
			int startX = ran.nextInt(width);
			int startY = ran.nextInt(height);
			int endX = ran.nextInt(width);
			int endY = ran.nextInt(height);
			g.drawLine(startX, startY, endX, endY);
		}
	}

	// 画干扰点
	public void drawPoint() {
		for (int i = 0; i < pointCount; i++) {
			// 设置画笔颜色
			g.setColor(getCorlor());
			// 设置干扰点的位置
			int x = ran.nextInt(width);
			int y = ran.nextInt(height);
			// 点是开始位置和结束位置相同的线
			g.drawLine(x, y, x, y);
		}
	}

}
