package com.baotoan.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baotoan.spring.dao.UserDAO;
import com.baotoan.spring.dao.WishDAO;
import com.baotoan.spring.entities.User;
import com.baotoan.spring.entities.Wish;
import com.baotoan.spring.service.Mailer;
import com.baotoan.spring.utils.GenerateCode;

@Controller
public class Authentication {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private WishDAO wishDAO;
	@Autowired
	private Mailer mailer;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		User user = new User();
		user.setName("Customer");
		user.setTel("0");
		model.addAttribute("user", user);
		return "login";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/auth", method = RequestMethod.POST)
	public String loginAuth(@Valid User user, BindingResult result, ModelMap model, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "login";
		}
		try {
			User userAuth = userDAO.getUserByEmail(user.getMail()); 
			if(userAuth != null && userAuth.getPass().equals(user.getPass())) {
				if(userAuth.getStatus().equals("actived")) {
					session.setAttribute("user", userAuth);
					// Update list wish for user
					List<Wish> listWish = (List<Wish>)session.getAttribute("listWish");
					if(null != listWish) {
						for(int i = 0; i < listWish.size(); i++) {
							Wish wish = listWish.get(i);
							if(!wishDAO.isContains(user.getMail(), wish.getProductId())) {
								wishDAO.addWish(new Wish(0, user.getMail(), wish.getProductId()));
							}
						}
					}
					session.removeAttribute("listWish");
					return "redirect:index";
				} else if(userAuth.getStatus().indexOf("actived") != -1 && userAuth.getStatus().length() > 7) { 
					userAuth.setStatus("actived");
					userDAO.updateUser(userAuth);
					return "login";
				} else {
					model.addAttribute("message", "Tài khoản chưa được kích hoạt, vui lòng kiểm tra lại!");
					return "login";
				}
			} else {
				model.addAttribute("message", "Tài khoản hoặc mật khẩu chưa đúng!");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Tài khoản hoặc mật khẩu chưa đúng!");
			return "login";
		}
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:index";
	}
	
	@RequestMapping(value="/regis", method = RequestMethod.GET)
	public String registry(ModelMap model) {
		User user = new User();
		user.setStatus("notactive");
		user.setRole(1);
		user.setScore(0);
		user.setId(0);
		model.addAttribute("user", user);
		return "registry";
	}
	
	@RequestMapping(value="/auRegis", method = RequestMethod.POST)
	public String authRegistry(@Valid User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "registry";
		}
		String code = GenerateCode.generate(10);
		user.setStatus(code);
		if(userDAO.addUser(user)) {
			String message = "Cảm ơn bạn đã đăng kí sử dụng dịch vụ của chúng tôi!\nVui lòng nhấn vào link sau để kích hoạt tài khoản:\n"
					+ "http://localhost:8080/StoreDigital/ac?c=" + code;
			if(mailer.sender(new String[] {user.getMail()}, "Kích hoạt tài khoản StoreDigital", message)) {
				model.addAttribute("message", "Đăng kí thành công, vui lòng kiểm tra email và kích hoạt tài khoản");
				return "login";
			}
		}
		model.addAttribute("message", "Email đã được sử dụng, vui lòng kiểm tra lại!");
		return "registry";
	}
	
	@RequestMapping(value="/ac", method = RequestMethod.GET)
	public String activeAccount(@RequestParam(value="c", required = true) String code, ModelMap model) {
		if(userDAO.activeAccount(code)) {
			User user = new User();
			user.setName("Customer");
			user.setTel("0");
			model.addAttribute("user", user);
			model.addAttribute("message", "Kích hoạt thành công, bạn có thể đăng nhập ngay lúc này!");
			return "login";
		}
		model.addAttribute("message", "Kích hoạt chưa thành công, vui lòng kiểm tra lại hoặc liên hệ với chúng tôi!");
		return "message";
	}
}
