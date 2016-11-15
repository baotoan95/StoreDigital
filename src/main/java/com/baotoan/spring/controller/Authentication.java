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
import com.baotoan.spring.dao.UserDAOImpl;
import com.baotoan.spring.dao.WishDAO;
import com.baotoan.spring.dao.WishDAOImpl;
import com.baotoan.spring.entities.User;
import com.baotoan.spring.entities.Wish;
import com.baotoan.spring.service.Mailer;
import com.baotoan.spring.utils.GenerateCode;

@Controller
public class Authentication {
	private UserDAO userDAO = new UserDAOImpl();
	private WishDAO wishDAO = new WishDAOImpl();
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
					// NgÆ°á»�i dÃ¹ng yÃªu cáº§u nháº¯c máº­t kháº©u nhÆ°ng sau Ä‘Ã³ nhá»› láº¡i vÃ  Ä‘Äƒng nháº­p
					userAuth.setStatus("actived");
					userDAO.updateUser(userAuth);
					return "login";
				} else {
					model.addAttribute("message", "TÃ i khoáº£n chÆ°a Ä‘Æ°á»£c kÃ­ch hoáº¡t, vui lÃ²ng kiá»ƒm tra láº¡i!");
					return "login";
				}
			} else {
				model.addAttribute("message", "TÃ i khoáº£n hoáº·c máº­t kháº©u chÆ°a Ä‘Ãºng!");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "TÃ i khoáº£n hoáº·c máº­t kháº©u chÆ°a Ä‘Ãºng!");
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
			String message = "Cáº£m  Æ¡n báº¡n Ä‘Ã£ sá»­ dá»¥ng dá»‹ch vá»¥ cá»§a chÃºng tÃ´i!\nNháº¥n vÃ o link sau Ä‘á»ƒ kÃ­ch hoáº¡t tÃ i khoáº£n:\n"
					+ "http://localhost:8080/StoreDigital/ac?c=" + code;
			if(mailer.sender(new String[] {user.getMail()}, "KÃ­ch hoáº¡t tÃ i khoáº£n StoreDigital", message)) {
				model.addAttribute("message", "Ä�Äƒng kÃ½ thÃ nh cÃ´ng, vui lÃ²ng kiá»ƒm tra email Ä‘á»ƒ kÃ­ch hoáº¡t tÃ i khoáº£n");
				return "login";
			}
		}
		model.addAttribute("message", "Email Ä‘Ã£ cÃ³ ngÆ°á»�i sá»­ dá»¥ng, vui lÃ²ng kiá»ƒm tra láº¡i");
		return "registry";
	}
	
	@RequestMapping(value="/ac", method = RequestMethod.GET)
	public String activeAccount(@RequestParam(value="c", required = true) String code, ModelMap model) {
		if(userDAO.activeAccount(code)) {
			User user = new User();
			user.setName("Customer");
			user.setTel("0");
			model.addAttribute("user", user);
			model.addAttribute("message", "KÃ­ch hoáº¡t thÃ nh cÃ´ng, vui lÃ²ng Ä‘Äƒng nháº­p!");
			return "login";
		}
		model.addAttribute("message", "KhÃ´ng kÃ­ch hoáº¡t Ä‘Æ°á»£c, vui lÃ²ng liÃªn há»‡ vá»›i chÃºng tÃ´i!");
		return "message";
	}
}
