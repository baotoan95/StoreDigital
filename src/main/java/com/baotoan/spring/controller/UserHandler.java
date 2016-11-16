package com.baotoan.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.OrderDAO;
import com.baotoan.spring.dao.UserDAO;
import com.baotoan.spring.entities.Order;
import com.baotoan.spring.entities.User;
import com.baotoan.spring.service.Mailer;
import com.baotoan.spring.utils.GenerateCode;

@Controller
public class UserHandler {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private Mailer mailer;
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String profilePage(ModelMap model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(null != user) {
			List<Order> orders = orderDAO.getOrdersByUser(user.getMail());
			model.addAttribute("orders", orders);
			model.addAttribute("user", user);
			return "dashboard";
		} else {
			return "redirect:login";
		}
	}
	
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	public String updateUser(@Valid User user,BindingResult result , ModelMap model) {
		if(result.hasErrors()) {
			return "dashboard";
		}
		if(userDAO.updateUser(user)) {
			mailer.sender(new String[] {user.getMail()}, "StoreDigital", "Tài khoản của bạn đã được cập nhật!");
			model.addAttribute("message", "Cập nhật thành công");
			return "dashboard";
		}
		model.addAttribute("message", "Cập nhật chưa thành công, vui lòng kiểm tra lại.");
		return "dashboard";
	}
	
	@RequestMapping(value="/requestRemember", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> requestRememberPass(@RequestParam(value="email", required=true) String email) {
		System.out.println("olk");
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userDAO.getUserByEmail(email);
		if(null != user) {
			String code = GenerateCode.generate(10);
			user.setStatus("actived-" + code);
			if(userDAO.updateUser(user)) {
				mailer.sender(new String[] {email}, "StoreDigital remember", "Bạn vừa yêu cầu nhắc lại mật khẩu, vui lòng vào link dưới:\n"
						+ "http://localhost:8080/StoreDigital/changePass?e="+email+"&c="+code);
				result.put("status", "Yêu cầu nhắc lại password đã được chấp nhận, "
						+ "chúng tôi đã gửi một email tới bạn, vui lòng kiểm tra để xác minh");
				return result;
			} else {
				result.put("status", "Có lỗi");
				return result;
			}
		} else {
			result.put("status", "Tài khoản không tồn tại trong hệ thống, nếu bạn chưa có một tài khoản, vui lòng đăng ký");
			return result;
		}
	}
	
	@RequestMapping(value="/changePass", method = RequestMethod.GET)
	public String changePass(@RequestParam(value="e", required = true) String email,
			@RequestParam(value="c", required = true) String code, HttpSession session) {
		User user = userDAO.getUserByEmail(email);
		if(null != user) {
			if(user.getStatus().indexOf("actived") > -1 && user.getStatus().length() > 7) {
				user.setStatus("actived");
				user.setPass(code);
				if(userDAO.updateUser(user)) {
					session.setAttribute("user", user);
					return "redirect:profile";
				}
			}
		}
		return "404error";
	}
}
