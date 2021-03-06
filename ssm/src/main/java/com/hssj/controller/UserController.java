package com.hssj.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hssj.pojo.ResultInfo;
import com.hssj.pojo.User;
import com.hssj.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户激活
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/activeUser", produces = "text/html;charset=utf-8")
	public ResultInfo activeUser(String code) {
		ResultInfo resultInfo = new ResultInfo();
		if (code != null) {
			// 激活并返回判断
			boolean flag = userService.active(code);
			resultInfo.setFlag(flag);
			if (flag) {
				resultInfo.setData("激活成功，请<a href='login.html'>登录</a>");
				return resultInfo;
			}
		}
		resultInfo.setData("激活失败，请联系管理员");
		return resultInfo;
	}

	/**
	 * 验证码
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "checkCode")
	public void checkCode(HttpServletResponse response, HttpSession session) throws IOException {
		int width = 100;
		int height = 50;

		BufferedImage imge = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = imge.getGraphics();
		Random random = new Random();
		// 填充背景色
		Color color1 = new Color(255, 255, 224);
		Color color2 = new Color(135, 206, 250);
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(color1);
		colors.add(color2);
		int colori = random.nextInt(colors.size());
		g.setColor(colors.get(colori));
		g.fillRect(0, 0, width, height);

		// 填写验证码
		String str = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
		StringBuilder code = new StringBuilder();
		Color color3 = new Color(138, 132, 70);
		Color color4 = new Color(43, 88, 143);
		Color color5 = new Color(155, 118, 85);
		ArrayList<Color> colorss = new ArrayList<Color>();
		colorss.add(color3);
		colorss.add(color4);
		colorss.add(color5);
		int colorj = random.nextInt(colorss.size());
		g.setColor(colorss.get(colorj));
		g.setFont(new Font("", Font.BOLD, 18));
		for (int i = 0; i < 4; i++) {
			int r_width = random.nextInt(18);
			int nextInt = random.nextInt(str.length());
			String charAt = String.valueOf(str.charAt(nextInt));
			code.append(charAt);
			g.drawString(charAt, r_width + i * 25, 20 + r_width);
		}

		// 将验证码存入session
		String checkcodeString = code.toString();
		session.setAttribute("checkcode_session", checkcodeString);

		// 画干扰线
		for (int i = 0; i < 4; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);

			g.drawLine(x1, y1, x2, y2);
		}
		ImageIO.write(imge, "jpg", response.getOutputStream());
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "exit")
	public String exit(HttpSession session) throws IOException {
		session.removeAttribute("user");
		return "redirect:/";
	}

	/**
	 * 登录状态查找用户名
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "findUsername", produces = "application/json;charset=utf-8")
	public ResultInfo findUsername(HttpSession session) {
		ResultInfo resultInfo = new ResultInfo();
		User user = null;
		try {
			user = (User) session.getAttribute("user");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (user != null) {
			resultInfo.setFlag(true);
			resultInfo.setData(
					"欢迎回来，" + user.getUsername() + "<a href=\"user/exit\" style=\"color: red;\">&nbsp;退出登录</a>");
			return resultInfo;
		} else {
			resultInfo.setFlag(false);
			resultInfo.setData("<a href=\"toLogin\">您好请登录</a>");
			return resultInfo;
		}
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws JsonProcessingException
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "login", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public ResultInfo login(HttpSession session, String checkcode, User user) {
		// 1.1验证码校验
		String code = (String) session.getAttribute("checkcode_session");
		session.removeAttribute("checkcode_session");// 保证验证码只能使用一次

		// 比较
		if (code == null || !code.equalsIgnoreCase(checkcode)) {
			// 验证码错误
			ResultInfo info = new ResultInfo();
			info.setFlag(false);
			info.setErrorMsg("验证码输入错误，登录失败！");
			return info;
		}

		User u = userService.login(user);
		ResultInfo resultInfo = new ResultInfo();
		// 判断返回数据
		if (u == null) {
			// 登录失败，用户名不存在
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("您输入的密码错误");
		}
		if (u != null && !"Y".equals(u.getStatus())) {
			// 登录失败，用户未被激活
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("用户未被激活,请前往激活");
		}
		if (u != null && "Y".equals(u.getStatus())) {
			// 登录成功
			resultInfo.setFlag(true);
			session.setAttribute("user", u);
		}

		// 响应json数据
		return resultInfo;
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @throws SendFailedException
	 * @throws JsonProcessingException
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "register", produces = "application/json;charset=utf-8")
	public ResultInfo register(User user, HttpSession session, HttpServletResponse response,
			@RequestParam(value = "checkcode") String code) {
		// 1.1验证码校验

		String checkcode = (String) session.getAttribute("checkcode_session");
		session.removeAttribute("checkcode_session");// 保证验证码只能使用一次

		// 比较
		if (checkcode == null || !checkcode.equalsIgnoreCase(code)) {
			// 验证码错误
			ResultInfo info = new ResultInfo();
			info.setFlag(false);
			info.setErrorMsg("验证码输入错误，注册失败！");
			return info;
		}
		// 3调用service完成注册
		boolean flag = userService.register(user);
		ResultInfo info = new ResultInfo();
		if (flag) {
			info.setFlag(true);// 注册成功
		} else {
			info.setFlag(false);
			info.setErrorMsg("该用户名已存在，注册失败！");
		}
		// 4返回响应结果
		return info;
	}

}
